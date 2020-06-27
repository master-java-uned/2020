/**
 * Peter Fight
 *
 * (27/06/2020) All my comments and variables translated
 * at Victor's good practice accomplishment request)
 *
 * Service interface for user management
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
     * I remove this method, it is a dirty one, I send it mail or username ...
     * @param email
     * @return
     * @throws DataAccessException
     */
    //User findByUsernameOrEmail(String usernameme, String email) throws DataAccessException;

    User findByEmail(String email);
    User findByUsername(String username);
    User registerUser(User user);


    void initPrettyThingsDb();


    User updateUser(User user);



    Page<User> getUsersPaged(int pageSize, int pageIndex);
}
