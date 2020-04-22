package com.covid19.authservice.dao;

import com.covid19.authservice.model.User;
import com.covid19.common.exception.DataAccessException;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserDao extends Neo4jRepository<User, Long> {

    User findByEmail(String email) throws DataAccessException;

//    List<Permission> findUserPermissions() throws DataAccessException;
//
//    User findByUsernameOrEmail(String username, String email) throws DataAccessException;

}
