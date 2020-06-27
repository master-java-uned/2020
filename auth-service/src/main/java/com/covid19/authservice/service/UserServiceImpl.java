/**
 * Peter Fight
 *
 * (27/06/2020) All my comments and fucking variables translated
 * at Victor's good practice accomplishment request)
 *
 * Service for user management
 */

package com.covid19.authservice.service;

import com.covid19.authservice.dao.Permission.PermissionDao;
import com.covid19.authservice.dao.Role.RoleDao;
import com.covid19.authservice.dao.User.UserDao;
import com.covid19.authservice.model.Role;
import com.covid19.authservice.model.User;
import com.covid19.common.exception.DataAccessException;
import com.covid19.authservice.model.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PermissionDao permissionDao;

    @Autowired
    RoleDao roleDao;

    /**
     * Removed the find by username or password, at the end I search for one or the other
     * @param email
     * @return
     */
    @Override
    public User findByEmail(String email)  {
        try{
            return userDao.findByEmail(email);
        }catch (Exception e) {
            return null;
        }
    }

    /**
     * Removed the find by username or password, at the end I search for one or the other
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        try{
            return userDao.findByUsername(username);
        }catch (Exception e) {
            return null;
        }
    }


    /**
     * This method is NOT USEFUL for paging
     * @return
     * @throws DataAccessException
     */
    @Override
    public List<User> findUsers() throws DataAccessException {
        return (List<User>) userDao.findAll();

    }


    /**
     * Peter Fight,
     Save a user, have generic exception to debug,
     once debugged ... Any problem.
     *
     * @param user
     * @return
     */
    @Override
    public User registerUser(User user){
        try {
            userDao.save(user);
            return user;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Peter Fight
     *
     I throw this method when the application starts and it will create all the
     tables we need, for example: roles, permissions and stuff.
     *
     I DON'T KILL MYSELF VERY MUCH, THIS IS TO START IT, I DON'T THINK THAT IN
     PRODUCTION WE HAD THE EMPTY BBDD, AS MUCH WE WOULD HAVE IT EMPTY THE FIRST
     TIME AND THEN WE WOULD COMMENT THE CALL TO THIS METHOD. I said.
     */
    @Override
    public void initPrettyThingsDb(){
        Permission adminPermission = new Permission(Permission.AvailablePermissions.ADMIN);
        Permission userPermission = new Permission(Permission.AvailablePermissions.USER);
        Role adminRole = new Role(Role.availableRoles.ADMIN);
        Role userRole = new Role(Role.availableRoles.ANONYMOUS);
        Role superAdminRole = new Role(Role.availableRoles.SUPERADMIN);

        try {
            if(permissionDao.findByRefId(Permission.AvailablePermissions.ADMIN.getId()) == null)
            {
                this.permissionDao.save(adminPermission);
            }
            if(permissionDao.findByRefId(Permission.AvailablePermissions.USER.getId()) == null) {
                this.permissionDao.save(userPermission);
            }
            if(roleDao.findByRefId(Role.availableRoles.ADMIN.getId()) == null)
            {
                List<Permission> add = new ArrayList<Permission>();
                add.add(adminPermission);
                adminRole.setPermissions(add);
                roleDao.save(adminRole);
            }
            if(roleDao.findByRefId(Role.availableRoles.SUPERADMIN.getId()) == null)
            {
                List<Permission> add = new ArrayList<Permission>();
                add.add(adminPermission);
                superAdminRole.setPermissions(add);
                roleDao.save(superAdminRole);
            }
            if(roleDao.findByRefId(Role.availableRoles.ANONYMOUS.getId()) == null)
            {
                List<Permission> add = new ArrayList<Permission>();
                add.add(userPermission);
                userRole.setPermissions(add);
                roleDao.save(userRole);
            }

            if(userDao.findByUsername("admin@admin.com") == null) {
                /**
                 * I create a default superadmin
                 */
                User superadmin = new User();
                superadmin.setUsername("admin@admin.com");
                superadmin.setEmail("admin@admin.com");
                superadmin.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt(10)));
                superadmin.setRole(superAdminRole);
                userDao.save(superadmin);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public User updateUser(User user){
        userDao.save(user);
        /**
         And to run !!!
         Life can be wonderful ... Daimiel !!!
         */
        return user;
    }






    @Override
    public Page<User> getUsersPaged(int pageSize, int pageIndex){
        Page<User> users = userDao.findAll(PageRequest.of(pageIndex, pageSize));
        return users;
    }
}
