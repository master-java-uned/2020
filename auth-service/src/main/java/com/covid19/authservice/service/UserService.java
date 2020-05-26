/**
 * Peter Fight
 *
 * Interfaz del servicio para la gestión de usuarios
 */

package com.covid19.authservice.service;

import com.covid19.authservice.model.User;
import com.covid19.common.exception.DataAccessException;
import com.covid19.authservice.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
//    public DataAccessException findByEmail(String email) throws DataAccessException;
//    public List<Permission> findUserPermissions() throws DataAccessException;
//    public User findByUsernameOrEmail(String username, String email) throws DataAccessException;
    public List<User> findUsers() throws DataAccessException;

    /**
     * Este método lo quito, es una guarrada, o le paso mail o username...
     * @param email
     * @return
     * @throws DataAccessException
     */
    //User findByUsernameOrEmail(String usernameme, String email) throws DataAccessException;

    User findByEmail(String email);
    User findByUsername(String username);
    User registerUser(User usuario);


    void initCosicasDb();


    User updateUser(User usuario);



    Page<User> getUsersPaged(int pageSize, int indicePage);
}
