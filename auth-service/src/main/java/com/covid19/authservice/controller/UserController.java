/**
 * Peter Fight
 *
 * Controlador al que llaman los métodos del front de angular.
 */

package com.covid19.authservice.controller;

import com.covid19.authservice.config.JwtTokenUtils;
import com.covid19.authservice.controller.controllerResponses.GenericResponse;
import com.covid19.authservice.dao.Role.RoleDao;
import com.covid19.authservice.model.Role;
import com.covid19.authservice.model.User;
import com.covid19.authservice.service.JwtUserDetailsService;
import com.covid19.authservice.service.UserService;
import com.covid19.authservice.service.UserServiceImpl;
import com.covid19.common.exception.DataAccessException;
import com.covid19.common.exception.UnAuthorizedAccessException;
import com.covid19.common.exception.ValidationException;
import com.covid19.common.validaciones.Errores;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


import com.jcabi.aspects.Cacheable;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtils jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private RoleDao roleDao;

    /**
     * Víctor -> TODO: documentame
     * amos... que devuelve un tokata con el user encriptado...
     * @param authenticationRequest
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        JSONObject accessToken = new JSONObject();
        accessToken.put("token", token);

        /**
         * Peter fight, le digo que devuelva el rolId para que lo gestione el front, si alguien hackear el
         * session storage del cliente, luego no le funcionarán los métodos del server, así que no problem.
         *
         */
        accessToken.put("rolId", userDetails.getAuthorities().stream().findFirst().get().getAuthority());//Y chinpún

        return ResponseEntity.ok(accessToken);
    }

    /**
     * Víctor -> TODO: documentar
     * ...amos, que llama al authenticationmanager
     * @param userName
     * @param password
     * @throws Exception
     */
    private void authenticate(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Esto no mola, cuando tengamos 100003293247938923 usuarios (pongamos al cabo de un mes), la app casca
     * fijo y tenemos a toda la comunidad de los batas-blancas en contra alzando auscultadores a modo de látigo
     * @return
     * @throws DataAccessException
     */
    @RequestMapping
    public List<User> getAll() throws DataAccessException {
        return userService.findUsers();
    }


    /**
     * Made by PeterFight
     *
     * Registro al user, le paso el user en JSON y a funcionar. Léanse los comentarios para conocer los detalles
     *
     * @param userToRegister
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse registerUser(@RequestBody User userToRegister) throws Exception {
        List<Integer> resultadoValidacion = userToRegister.validate(userService);
        try {
            if (resultadoValidacion.size() > 0) {
                /**
                 * ATENCIÓN! el usuario es tonto y puede hacernos quedar mal. A saco con él.
                 */
                throw new ValidationException(resultadoValidacion);
            } else {
                /**
                 * Validación ok, registro al usuario
                 * 3... -> Tres puntos suspensivos porque sí.
                 * 2.. -> Dos puntos porque es el dos.
                 * 1. -> Un punto porque es el uno,
                 * ...VAMOOOOS!!! --> Seguro que no funciona a la primera, palabrita.
                 */
                //por defecto lo meto con el rol anónimo
                userToRegister.setRole(roleDao.findByRefId(Role.rolesPosibles.ADMIN.getId()));
                //Establezco el username y el mail en minúsculas para normalizar
                userToRegister.setUsername(userToRegister.getUsername().toLowerCase());
                userToRegister.setEmail(userToRegister.getUsername().toLowerCase());
                User usuarioInsertado = userService.registerUser(userToRegister);
                if (usuarioInsertado == null) {
                    /**
                     * Si llega aquí el tonto soy yo, pero lo meto como validationException para echar
                     * balones fuera... Los eternos becarios semos asín. Queremos más dinero!!!!
                     */
                    throw new ValidationException(
                            new ArrayList<> //single line instanciación in Java style's
                                    (Arrays.asList(Errores.CODIGOS.ERROR_INSERTAR_USER.getId())));
                } else {
                    return getSalidaServicio(new GenericResponse(true, null));
                }
            }
        } catch (ValidationException e) {
            return getSalidaServicio(new GenericResponse(null, e.getLocalizedErrores()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return getSalidaServicio(new GenericResponse(
                    null,  null));
        }
        finally {

        }
    }


    /**
     *
     * Made by PeterFight
     *
     * Para probar los listados de usuarios y para dar más porte a la bbdd, creo este método que clona
     * el usuario en cuestión tantas veces como nos indique. Esto mola para cuando vayamos a jugar con
     * los listados de usuarios.
     *
     * @param tokata el tokata devuelto al logarse
     * @param cantidadOvejitas la cantidad de ovejitas a añadir
     * @return GenericResponse
     * @throws Exception no throws nada, y punto.
     */
    @RequestMapping(value = "/ovejitasDolly", method = RequestMethod.POST)
    public GenericResponse ovejitasDolly(@RequestBody int cantidadOvejitas) throws Exception {
        /**
         * Ni try ni leches, yo no fallo, soy una mente regida (y rigída) por las matemáticas discretas!!!
         */
        String usernamekata = getUsernamekataFromTokata("");
        //Saco el username del tokata y encuentro el user
        User ovejitaDolly = userService.findByUsername(usernamekata);
        //Para tantas veces como se desee, clono una ovejita
        for(int i = 0; i < cantidadOvejitas; i++)
        {
            User ovejita = ovejitaDolly.clone();
            ovejita.setUsername(String.format("%s_clonNumber%d",ovejitaDolly.getUsername(),i));
            userService.registerUser(ovejita);
        }
        return getSalidaServicio(new GenericResponse(true, null));
    }


    /**
     * Peter Fight
     *
     * Método para usar en aquellos endpoints que menejan tokata
     */
    private String getUsernamekataFromTokata(String tokata)
    {

        Object jwtUser = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if(jwtUser == null)
        {
            throw new UnAuthorizedAccessException();
            /**
             * Yo lanzo esto, si existe supongo que funciona, si no funciona... cabeza bajo el ala. No doy
             * soporte a código que no es mío cuando voy mal de tiempo. En cualquier caso controlo el error
             * a continuación.
             */
        }
        return ((UserDetails)jwtUser).getUsername();
    }




    /**
     * Peter Fight
     *
     * Pfua nen!! estoy picando más código que en el trabajo... Ahora resulta que necesitamos un método
     * que devuelva información del usuario... y todavía no he cobrado NADA! Óstia!!!!
     * @param tokata
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public GenericResponse getUser(@RequestBody String tokata) throws Exception {
        String usernamekata = getUsernamekataFromTokata(tokata);
        try {
            User usuarioEnLiza = userService.findByUsername(usernamekata);
            if (usuarioEnLiza == null) {
                /**
                 * Houston, tenemos un problema, deberíamos haber devuelto una excepción 401 pero sin embargo
                 * aquí estamos... con el Challenger despegado y expulsando fuego por la popa...
                 */
                throw new ValidationException(
                        new ArrayList<> //single line instanciación in Java style's. Qué poco me gusta JAVA.
                                (Arrays.asList(Errores.CODIGOS.ERROR_INEXPLICABLE.getId())));
            }

            /**
             * Vale, vamos guay, he sacado el user.
             * devuelvo everything, password incluido... Vamos a ver, neurótico, si el password está
             * hashcodeado, te reto a que lo desencriptes... Y todavía más, a que desencriptes el password
             * que nadie te ha devuelto porque no te has logado. Qué rabia me da la gente insegura de las
             * ñapas... CUANDO EL MUNDO EN SÍ MISMO ES <<LA ÑAPA UNIVERSAL>>... he dicho. Y no me hagas
             * picar más código sin sentido, leñe... Bueno, lo borro de la respuesta porque soy un esclavo
             * del imperio.
             */
            usuarioEnLiza.setPassword("PASSWORDS_EN_OFERTA -> passwordSuperSecretoHechoPúblico!");
            return getSalidaServicio(new GenericResponse(usuarioEnLiza, null));
            //y a funcionar!!
        }
        catch (ValidationException e) {
            return getSalidaServicio(new GenericResponse(null, e.getLocalizedErrores()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return getSalidaServicio(new GenericResponse(
                    null,  null));
        }
        finally {

        }
    }







    /**
     * Made by PeterFight
     *
     * Método para modificar un user. Le paso el tokata y el user a modificar... a partir de ahí, si el
     * user es válido y el user a modificar es el mismo del tokata:
     *      Modifico al user.
     *      Si el campo password me viene lleno, modifico el password.
     *
     * @param tokata
     * @param userToModify
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modificaUser", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse modificaUser(@RequestBody User userToModify) throws Exception {
        String usernamekata = getUsernamekataFromTokata("");


        List<Integer> resultadoValidacion = userToModify.validateForModify(userService);
        try {
            if (resultadoValidacion.size() > 0) {
                /**
                 * ATENCIÓN! el usuario es tonto y puede hacernos quedar mal. A saco con él.
                 */
                throw new ValidationException(resultadoValidacion);
            } else {
                /**
                 * Validación ok, modifico el usuario
                 */
                User usuarioEnLiza = userService.findByUsername(usernamekata);
                if (usuarioEnLiza == null) {
                    /**
                     * Houston, tenemos un problema, deberíamos haber devuelto una excepción 401 pero sin embargo
                     * aquí estamos... con el Challenger despegado y expulsando fuego por la popa...
                     */
                    throw new ValidationException(
                            new ArrayList<> //single line instanciación in Java style's. Qué poco me gusta JAVA.
                                    (Arrays.asList(Errores.CODIGOS.ERROR_INEXPLICABLE.getId())));
                }
                /**
                 * Mapeo los campos que quiero modificar
                 */
                usuarioEnLiza.setImageBase64(userToModify.getImageBase64());
                usuarioEnLiza.setEmail(userToModify.getEmail());
                usuarioEnLiza.setFirstName(userToModify.getFirstName());
                usuarioEnLiza.setLastName(userToModify.getLastName());

                if(userToModify.getPassword().length() > 0)
                {
                    //Si el campo password me viene lleno lo modifico
                    usuarioEnLiza.setPassword(userToModify.getPassword());
                }

                User usuarioModificado = userService.updateUser(usuarioEnLiza);
                if (usuarioModificado == null) {
                    /**
                     * Si llega aquí el tonto soy yo, pero lo meto como validationException para echar
                     * balones fuera... Los eternos becarios semos asín. Queremos más dinero!!!!
                     */
                    throw new ValidationException(
                            new ArrayList<> //single line instanciación in Java style's
                                    (Arrays.asList(Errores.CODIGOS.ERROR_MODIFICAR_USER.getId())));
                } else {
                    return getSalidaServicio(new GenericResponse(usuarioModificado, null));
                }
            }
        } catch (ValidationException e) {
            return getSalidaServicio(new GenericResponse(null, e.getLocalizedErrores()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return getSalidaServicio(new GenericResponse(
                    null,  null));
        }
        finally {

        }
    }









    @RequestMapping(value = "/getUsersPaged", method = RequestMethod.GET
//            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse getUsersPaged(@RequestParam int pageSize, int indicePage) throws Exception {
        String usernamekata = getUsernamekataFromTokata("");
        try{
            Page<User> users = userService.getUsersPaged(pageSize,indicePage);
            return getSalidaServicio(new GenericResponse(
                    users,  null));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return getSalidaServicio(new GenericResponse(
                    null,  null));
        }
    }






    @RequestMapping(value = "/getJson", method = RequestMethod.GET
//            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * Meter la variable en cache para ahorrar tiempo.
     *
     * USO JCABI por facilidad de uso (recargo cada 1 hora). Funcionar funciona pero sigue tardando
     * más que antes, supongo que por el tamaño del JSON que hay que descargarlo.
     */
    @Cacheable(lifetime = 1, unit = TimeUnit.HOURS)
    public String getJson() throws Exception {
        URL url = new URL("https://opendata.ecdc.europa.eu/covid19/casedistribution/json/");
        InputStreamReader reader = new InputStreamReader(url.openStream());
        String respuesta = new BufferedReader(reader)
                .lines()
                .collect(Collectors.joining(""));
        return respuesta;
    }







    /**
     * Made by Peter Fight
     *
     * Este método lo creo por si tenemos que meter logs en un futuro. No, per favooore. Eso es propio
     * de empresas millonarias, de la casta... no de cárnicas como Diós manda. Aquí hemos venido a sufrir!!!
     *
     * @param res la respuesta (aunque te sorprenda)
     * @return GenericResponse -> Contiene un objeto Object muy molón porque admite lo que le echemos,
     * es una mula de carga muy bonachona. También devuelve el listado de errores con las traducciones
     * a todos los idiomas con los que trabajamos... si la variable errores viene lleno no mola :(
     */
    private GenericResponse getSalidaServicio(GenericResponse res) {
        //Aquí loggeo lo que haga falta
        return res;
    }
}
