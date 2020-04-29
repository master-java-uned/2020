package com.covid19.authservice.service;


import com.covid19.authservice.dao.UserDao;
import com.covid19.authservice.model.User;
import com.covid19.common.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDao.findByUsername(s);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }
     // if (user.getStatus() != 2) {
       List<GrantedAuthority> authorities = new ArrayList<>();
       
           authorities.add(new SimpleGrantedAuthority(user.getRole()));
          
        //GrantedAuthority authorities= new SimpleGrantedAuthority(user.getRole().getRole());
        
        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUsername() , user.getPassword(), authorities);

        return userDetails ;
     /* } else {
          try {
              throw new  IllegalAccessException("blocked");
          } catch (IllegalAccessException e) {
              e.printStackTrace();
          }
      }


        return null;*/
    }
}