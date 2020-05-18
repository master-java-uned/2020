/**
 * Víctor
 *
 * Modified by Peter Fight, para el tema de los métodos de validación e incluir la imagen
 */

package com.covid19.authservice.model;

import com.covid19.authservice.service.UserService;
import com.covid19.common.exception.DataAccessException;
import com.covid19.common.validaciones.Errores;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.*;
import java.util.regex.Pattern;


@NodeEntity
public class User extends DataAccessException  implements Cloneable {

    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private Date lastLoggedOn;

    private Date registeredOn;

    private Integer attempts;

    private Role role;

    private String imageBase64;

    private List<Role> roles = new ArrayList<>();


    private List<Permission> permissions = new ArrayList<>();


    private Date lastPasswordResetDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Date getLastLoggedOn() {
        return lastLoggedOn;
    }

    public void setLastLoggedOn(Date lastLoggedOn) {
        this.lastLoggedOn = lastLoggedOn;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Integer getAttempts() {
        return attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }


    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }


    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }


    /**
     * Le paso el userService en el validate para comprobar que el username no esté pillado por nadie.
     * Estaría guay que el username fuese el mail o el dni... pero somos tan guais que no lo facemos.
     * @param userService
     * @return
     */
    public List<Integer> validate(UserService userService) throws DataAccessException {
        /**
         * capturar luego el código de error con esto
         */
       List<Integer> respuestaValidacion = new ArrayList<Integer>();
        if(this.getFirstName() == null || this.getFirstName().length() == 0)
        {
            respuestaValidacion.add(Errores.CODIGOS.NOMBRE_INCORRECTO.getId());//Nombre incorrecto
        }
        if(this.getLastName() == null || this.getLastName().length() == 0)
        {
            respuestaValidacion.add(Errores.CODIGOS.APELLIDO_INCORRECTO.getId());
        }
        if(this.getPassword() == null || this.getPassword().length() == 0)
        {
            respuestaValidacion.add(Errores.CODIGOS.FALTA_PASSWORD.getId());
        }
        if(this.getEmail() == null || this.getEmail().length() == 0){
            respuestaValidacion.add(Errores.CODIGOS.FALTA_EMAIL.getId());
        }
        else {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            if(!pat.matcher(email).matches()){
                //No es un email válido
                respuestaValidacion.add(Errores.CODIGOS.EMAIL_INCORRECTO.getId());
            }
        }
        /**
         * Compruebo que el username no esté pillado
         */
        User usuario = userService.findByUsername(this.getUsername().toLowerCase());
        if(usuario != null)
        {
            //Catapuuumba, el username está pillado. Fuck you!
            respuestaValidacion.add(Errores.CODIGOS.USERNAME_YA_PILLADO.getId());
        }

        return respuestaValidacion;
    }



    /**
     * Validación para cuando se trata de modificar un usuario
     *
     * No compruebo ni que el username esté pillado (obviamente lo está si estamos modificando)
     * ni compruebo que lleve el password. Si no lo está modificando el campo no vendrá relleno.
     * @param userService
     * @return
     */
    public List<Integer> validateForModify(UserService userService) throws DataAccessException {
        /**
         * capturar luego el código de error con esto
         */
        List<Integer> respuestaValidacion = new ArrayList<Integer>();
        if(this.getFirstName() == null || this.getFirstName().length() == 0)
        {
            respuestaValidacion.add(Errores.CODIGOS.NOMBRE_INCORRECTO.getId());//Nombre incorrecto
        }
        if(this.getLastName() == null || this.getLastName().length() == 0)
        {
            respuestaValidacion.add(Errores.CODIGOS.APELLIDO_INCORRECTO.getId());
        }
        if(this.getEmail() == null || this.getEmail().length() == 0){
            respuestaValidacion.add(Errores.CODIGOS.FALTA_EMAIL.getId());
        }
        else {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            if(!pat.matcher(email).matches()){
                //No es un email válido
                respuestaValidacion.add(Errores.CODIGOS.EMAIL_INCORRECTO.getId());
            }
        }

        return respuestaValidacion;
    }




    public User clone() throws
            CloneNotSupportedException
    {
        User ovejitaClonada = (User)super.clone();
        //No vayamos a liarla en la base de datos.
        ovejitaClonada.setId(null);
        return ovejitaClonada;
    }

}
