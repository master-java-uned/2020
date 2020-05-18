package com.covid19.authservice.dao.User;

import com.covid19.authservice.model.User;
import com.covid19.authservice.service.UserServiceImpl;
import com.covid19.common.exception.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import javax.xml.crypto.Data;
import java.awt.*;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserDao extends Neo4jRepository<User, Long> {

    User findByEmail(String email) throws DataAccessException;
    User findByUsername(String username) throws DataAccessException;
    @Override
    <S extends User> S save(S s, int depth);

    Page<User> findAll(Pageable paginacion);
}
