/**
 * Peter Fight
 *
 * (27/06/2020) All my comments and variables translated
 * at Victor's good practice accomplishment request)
 *
 * Controller that is called by the angular front methods.
 *
 */

package com.covid19.authservice.controller;

import com.covid19.authservice.config.JwtTokenUtils;
import com.covid19.authservice.controller.controllerResponses.GenericResponse;
import com.covid19.authservice.dao.Role.RoleDao;
import com.covid19.authservice.model.Role;
import com.covid19.authservice.model.User;
import com.covid19.authservice.service.JwtUserDetailsService;
import com.covid19.authservice.service.UserService;
import com.covid19.common.exception.DataAccessException;
import com.covid19.common.exception.UnAuthorizedAccessException;
import com.covid19.common.exception.PeterFightValidationException;
import com.covid19.common.peterFightValidations.peterFightErrors;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
     * ... it returns a tokata with the encrypted user ...
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
         I tell it to return the rolId to be managed by the front, if someone hack the
         session storage of the client, then the server methods will not work, so no problem.
         *
         */
        accessToken.put("rolId", userDetails.getAuthorities().stream().findFirst().get().getAuthority());
        //And chinpún

        return ResponseEntity.ok(accessToken);
    }

    /**
     * Víctor -> TODO: documentar
     * ...amos, calls the authenticationmanager
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
     This is not cool, when we have 100003293247938923 users (say after a month),
     the casca app fixed and we have the whole community of the white coats
     against raising auscultators as a whip
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
     *
     I register the user, I pass the user in JSON and it will work.
     Read comments for details
     *
     * @param userToRegister
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse registerUser(@RequestBody User userToRegister) throws Exception {
        List<Integer> validationResult = userToRegister.validate(userService);
        try {
            if (validationResult.size() > 0) {
                /**
                 * ATTENTION! the user is stupid and can make us look bad. A sack with him.
                 */
                throw new PeterFightValidationException(validationResult);
            } else {
                /**
                 Validation ok, user registration
                 3 ... -> Three ellipsis for yes.
                 2 .. -> Two points because it is two.
                 1. -> A point because it is the one,
                 ... VAMOOOOS !!! -> It sure doesn't work the first time, little word.
                 */
                //by default I put it with the anonymous role
                userToRegister.setRole(roleDao.findByRefId(Role.availableRoles.ADMIN.getId()));
                //I set username and mail to lowercase to normalize
                userToRegister.setUsername(userToRegister.getUsername().toLowerCase());
                userToRegister.setEmail(userToRegister.getUsername().toLowerCase());
                User insertedUser = userService.registerUser(userToRegister);
                if (insertedUser == null) {
                    /**
                     If the fool arrives here it is me, but I put it as a validationException
                     to throw balls out ... The eternal fellows are asin. We want more money !!!!
                     */
                    throw new PeterFightValidationException(
                            new ArrayList<> //single line instantiation in Java style's
                                    (Arrays.asList(peterFightErrors.CODES.ERROR_INSERTING_USER.getId())));
                } else {
                    return getServiceOutput(new GenericResponse(true, null));
                }
            }
        } catch (PeterFightValidationException e) {
            return getServiceOutput(new GenericResponse(null, e.getLocalizedErrors()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return getServiceOutput(new GenericResponse(
                    null,  null));
        }
        finally {

        }
    }


    /**
     *
     * Made by PeterFight
     *
     To test the user lists and to give more weight to the bbdd, I create this
     method that clones the user in question as many times as indicated.
     çThis is cool when we go play with user listings.
     *
     * @param sheepNumber the amount of sheep to add
     * @return GenericResponse
     * @throws Exception throws anything, and dot.
     */
    @RequestMapping(value = "/ovejitasDolly", method = RequestMethod.POST)
    public GenericResponse dollySheeps(@RequestBody int sheepNumber) throws Exception {
        /**
         * Neither try nor milk, I do not fail, I am a mind governed (and rigid)
         * by discrete mathematics !!!
         */
        String usernamekata = getUsernamekataFromTokata("");
        //I remove the username from the tokata and find the user
        User dollySheeps = userService.findByUsername(usernamekata);
        //Para tantas veces como se desee, clono una ovejita
        for(int i = 0; i < sheepNumber; i++)
        {
            User sheep = dollySheeps.clone();
            sheep.setUsername(String.format("%s_clonNumber%d",dollySheeps.getUsername(),i));
            userService.registerUser(sheep);
        }
        return getServiceOutput(new GenericResponse(true, null));
    }


    /**
     * Peter Fight
     *
     * Method to use in those endpoints that handle tokata
     *
     */
    private String getUsernamekataFromTokata(String tokata)
    {

        Object jwtUser = SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        if(jwtUser == null)
        {
            throw new UnAuthorizedAccessException();
            /**
             I throw this, if it exists I suppose it works, if it doesn't work ...
             head under the wing. I do not give I support code that is not mine
             when I run out of time. In any case I control the error then.
             */
        }
        return ((UserDetails)jwtUser).getUsername();
    }




    /**
     * Peter Fight
     *
     Pfua nen !! I'm chopping more code than at work ... Now it turns out we need
     a method to return user information ... and I still haven't charged ANYTHING!
     Ostia !!!!
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public GenericResponse getUser(@RequestBody String tokata) throws Exception {
        String usernamekata = getUsernamekataFromTokata(tokata);
        try {
            User user = userService.findByUsername(usernamekata);
            if (user == null) {
                /**
                 Houston, we have a problem, we should have returned a 401
                 exception but nevertheless here we are ... with the Challenger
                 taking off and expelling fire from the stern ...
                 */
                throw new PeterFightValidationException(
                        new ArrayList<> //single line instantiation in Java style's.
                                // How little I like JAVA.
                                (Arrays.asList(peterFightErrors.CODES.UNEXPECTED_ERROR.getId())));
            }

            /**
             Ok, come on, cool, I got the user out.
             I return everything, password included ... Let's see, neurotic,
             if the password is hashcodeado, I challenge you to decrypt it ...
             And even more, to decrypt the password that nobody has returned you
             because you have not logged in. What rage do people unsure of
             ñapas ... WHEN THE WORLD ITSELF IS << THE UNIVERSAL MAP >> ...
             I said. And don't make me chop more nonsense code, read ...
             Well, I delete it from the answer because I'm a slave
             Empire.
             */
            user.setPassword("PASSWORDS_EN_OFERTA -> passwordSuperSecretoHechoPúblico!");
            return getServiceOutput(new GenericResponse(user, null));
            //and to work !!
        }
        catch (PeterFightValidationException e) {
            return getServiceOutput(new GenericResponse(null, e.getLocalizedErrors()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return getServiceOutput(new GenericResponse(
                    null,  null));
        }
        finally {

        }
    }







    /**
     * Made by PeterFight
     *
     Method to modify a user. I pass the tokata and the user to modify ... from there, if the
     user is valid and the user to modify is the same as the tokata:
     I modify the user.
     If the password field comes full, I modify the password.
     *
     * @param userToModify
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/modificaUser", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse modificaUser(@RequestBody User userToModify) throws Exception {
        String usernamekata = getUsernamekataFromTokata("");


        List<Integer> validationResult = userToModify.validateForModify(userService);
        try {
            if (validationResult.size() > 0) {
                /**
                 * ATTENTION! the user is stupid and can make us look bad. A sack with him.
                 */
                throw new PeterFightValidationException(validationResult);
            } else {
                /**
                 * Validation ok, I modify the user
                 */
                User user = userService.findByUsername(usernamekata);
                if (user == null) {
                    /**

                     Houston, we have a problem, we should have returned a
                     401 exception but nevertheless here we are ... with the
                     Challenger taking off and expelling fire from the stern ...
                     */
                    throw new PeterFightValidationException(
                            new ArrayList<> //single line instantiation in Java style's.
                                    // How little I like JAVA.
                                    (Arrays.asList(peterFightErrors.CODES.UNEXPECTED_ERROR.getId())));
                }
                /**
                 * I map the fields that I want to modify
                 */
                user.setImageBase64(userToModify.getImageBase64());
                user.setEmail(userToModify.getEmail());
                user.setFirstName(userToModify.getFirstName());
                user.setLastName(userToModify.getLastName());

                if(userToModify.getPassword().length() > 0)
                {
                    //If the password field comes full, I modify it
                    user.setPassword(userToModify.getPassword());
                }

                User modifiedUser = userService.updateUser(user);
                if (modifiedUser == null) {
                    /**
                     If the fool arrives here it is me, but I put it as a
                     validationException to throw balls out ... The eternal
                     fellows are asin. We want more money !!!!
                     */
                    throw new PeterFightValidationException(
                            new ArrayList<> //single line instantiation in Java style's
                                    (Arrays.asList(peterFightErrors.CODES.ERROR_MODIFYING_USER.getId())));
                } else {
                    return getServiceOutput(new GenericResponse(modifiedUser, null));
                }
            }
        } catch (PeterFightValidationException e) {
            return getServiceOutput(new GenericResponse(null, e.getLocalizedErrors()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return getServiceOutput(new GenericResponse(
                    null,  null));
        }
        finally {

        }
    }









    @RequestMapping(value = "/getUsersPaged", method = RequestMethod.GET
//            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public GenericResponse getUsersPaged(@RequestParam int pageSize, int pageIndex) throws Exception {
        String usernamekata = getUsernamekataFromTokata("");
        try{
            Page<User> users = userService.getUsersPaged(pageSize,pageIndex);
            return getServiceOutput(new GenericResponse(
                    users,  null));
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return getServiceOutput(new GenericResponse(
                    null,  null));
        }
    }






    @RequestMapping(value = "/getJson", method = RequestMethod.GET
            , produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     Caching the variable to save time.

     USE JCABI for ease of use (surcharge every 1 hour). Working works but still
     taking more than before, I suppose that due to the size of the JSON that
     must be downloaded.
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

     I create this method in case we have to enter logs in the future. No, please. That's own
     of millionaire companies, of the caste ... not of meat like God commands. Here we have come to suffer !!!

     * @param res the answer (even if it surprises you)
     * @return GenericResponse -> It contains a very cool Object because it
     * supports what we throw at it, It is a very good-natured pack mule.
     * It also returns the list of errors with translations to all the languages
     * ​​we work with ... if the error variable is full it doesn't cool :(
     */
    private GenericResponse getServiceOutput(GenericResponse res) {
        //Here I log what it takes
        return res;
    }
}
