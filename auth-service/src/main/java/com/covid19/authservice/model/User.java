/**
 * Víctor
 *
 * Modified by Peter Fight
 *
 * (27/06/2020) All my comments and variables translated
 * at Victor's good practice accomplishment request)
 *
 */

package com.covid19.authservice.model;

import com.covid19.authservice.service.UserService;
import com.covid19.common.exception.DataAccessException;
import com.covid19.common.peterFightValidations.peterFightErrors;
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
     I pass the userService in the validate to verify that the username is
     not caught by anyone. It would be cool if the username was the mail
     or the ID ... but we are so cool that we do not face it.
     * @param userService
     * @return
     */
    public List<Integer> validate(UserService userService) throws DataAccessException {
        /**
         * then catch the error code with this
         */
       List<Integer> validationResponse = new ArrayList<Integer>();
        if(this.getFirstName() == null || this.getFirstName().length() == 0)
        {
            validationResponse.add(peterFightErrors.CODES.INCORRECT_NAME.getId());//Nombre incorrecto
        }
        if(this.getLastName() == null || this.getLastName().length() == 0)
        {
            validationResponse.add(peterFightErrors.CODES.INCORRECT_SURNAME.getId());
        }
        if(this.getPassword() == null || this.getPassword().length() == 0)
        {
            validationResponse.add(peterFightErrors.CODES.PASSWORD_MISSING.getId());
        }
        if(this.getEmail() == null || this.getEmail().length() == 0){
            validationResponse.add(peterFightErrors.CODES.EMAIL_MISSING.getId());
        }
        else {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            if(!pat.matcher(email).matches()){
                //No es un email válido
                validationResponse.add(peterFightErrors.CODES.INCORRECT_EMAIL.getId());
            }
        }
        /**
         * I check that the username is not caught
         */
        User usuario = userService.findByUsername(this.getUsername().toLowerCase());
        if(usuario != null)
        {
            //Catapuuumba, el username está pillado. Fuck you!
            validationResponse.add(peterFightErrors.CODES.USERNAME_ALREADY_TAKEN.getId());
        }

        return validationResponse;
    }



    /**
     Validation for when it comes to modifying a user

     I do not even check that the username is caught (obviously it is if we are modifying)
     nor do I verify that it carries the password. If you are not modifying it the field will not come filled.
     * @param userService
     * @return
     */
    public List<Integer> validateForModify(UserService userService) throws DataAccessException {
        /**
         * Then catch the error code with this
         */
        List<Integer> validationResponse = new ArrayList<Integer>();
        if(this.getFirstName() == null || this.getFirstName().length() == 0)
        {
            validationResponse.add(peterFightErrors.CODES.INCORRECT_NAME.getId());//Nombre incorrecto
        }
        if(this.getLastName() == null || this.getLastName().length() == 0)
        {
            validationResponse.add(peterFightErrors.CODES.INCORRECT_SURNAME.getId());
        }
        if(this.getEmail() == null || this.getEmail().length() == 0){
            validationResponse.add(peterFightErrors.CODES.EMAIL_MISSING.getId());
        }
        else {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            if(!pat.matcher(email).matches()){
                //It's not a valid email
                validationResponse.add(peterFightErrors.CODES.INCORRECT_EMAIL.getId());
            }
        }

        return validationResponse;
    }




    public User clone() throws
            CloneNotSupportedException
    {
        User clonedSheep = (User)super.clone();
        // Let's not mess it up in the database.
        clonedSheep.setId(null);
        return clonedSheep;
    }

}
