package com.covid19.authservice.service;

import com.covid19.authservice.model.User;
import com.covid19.common.exception.DataAccessException;
import com.covid19.common.model.Permission;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    public DataAccessException findByEmail(String email) throws DataAccessException;
    public List<Permission> findUserPermissions() throws DataAccessException;
    public User findByUsernameOrEmail(String username, String email) throws DataAccessException;
    public Iterable<User> findUsers() throws DataAccessException;
}
