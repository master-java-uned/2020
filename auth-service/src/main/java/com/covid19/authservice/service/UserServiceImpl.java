package com.covid19.authservice.service;

import com.covid19.authservice.model.User;
import com.covid19.common.exception.DataAccessException;
import com.covid19.common.model.Permission;
import com.covid19.authservice.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User findByEmail(String email) throws DataAccessException {
        try{
            return userDao.findByEmail(email);
        }catch (DataAccessException e) {
            return (User) e;
        }
    }

    @Override
    public List<Permission> findUserPermissions() throws DataAccessException {
        return null;
    }

    @Override
    public User findByUsernameOrEmail(String username, String email) throws DataAccessException {
        return null;
    }

    @Override
    public Iterable<User> findUsers() throws DataAccessException {
        return userDao.findAll();
    }


}
