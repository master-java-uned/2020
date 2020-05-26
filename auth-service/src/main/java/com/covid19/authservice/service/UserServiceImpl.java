/**
 * Peter Fight
 *
 * Servicio para la gestión de usuarios
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
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
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
     * QUitado el find by username or password, al final busco por uno o por el otro
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
     * QUitado el find by username or password, al final busco por uno o por el otro
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
     * TODO: Este método NO SIRVE para paginar
     * @return
     * @throws DataAccessException
     */
    @Override
    public List<User> findUsers() throws DataAccessException {
        return (List<User>) userDao.findAll();

    }


    /**
     * Peter Fight, guarda un usuario, tiene la excepción genérica para debuggear, una vez debuggeado... Cap problema.
     *
     * @param usuario
     * @return
     */
    @Override
    public User registerUser(User usuario){
        try {
            userDao.save(usuario);
            return usuario;
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
     * Este método lo lanzo cuando arranca la aplicación y me creará todas las tablas que necesitamos,
     * for example: los roles, los permisos y esas cosas.
     *
     * NO ME MATO MUCHO, ESTO ES PARA QUE ARRANQUE, NO CREO QUE EN PRODUCCIÓN TUVIESEMOS LA BBDD VACÍA,
     * COMO MUCHO LA TENDRÍAMOS VACÍA LA PRIMERA VEZ Y LUEGO COMENTARÍAMOS LA LLAMADA A ESTE MÉTODO. he dicho.
     */
    @Override
    public void initCosicasDb(){
        Permission permisoAdmin = new Permission(Permission.permisosPosibles.ADMIN);
        Permission permisoUser = new Permission(Permission.permisosPosibles.USER);
        Role roleAdmin = new Role(Role.rolesPosibles.ADMIN);
        Role roleUser = new Role(Role.rolesPosibles.ANONIMO);
        Role roleSuperAdmin = new Role(Role.rolesPosibles.SUPERADMIN);

        try {
            if(permissionDao.findByRefId(Permission.permisosPosibles.ADMIN.getId()) == null)
            {
                this.permissionDao.save(permisoAdmin);
            }
            if(permissionDao.findByRefId(Permission.permisosPosibles.USER.getId()) == null) {
                this.permissionDao.save(permisoUser);
            }
            if(roleDao.findByRefId(Role.rolesPosibles.ADMIN.getId()) == null)
            {
                List<Permission> add = new ArrayList<Permission>();
                add.add(permisoAdmin);
                roleAdmin.setPermissions(add);
                roleDao.save(roleAdmin);
            }
            if(roleDao.findByRefId(Role.rolesPosibles.SUPERADMIN.getId()) == null)
            {
                List<Permission> add = new ArrayList<Permission>();
                add.add(permisoAdmin);
                roleSuperAdmin.setPermissions(add);
                roleDao.save(roleSuperAdmin);
            }
            if(roleDao.findByRefId(Role.rolesPosibles.ANONIMO.getId()) == null)
            {
                List<Permission> add = new ArrayList<Permission>();
                add.add(permisoUser);
                roleUser.setPermissions(add);
                roleDao.save(roleUser);
            }

            if(userDao.findByUsername("admin@admin.com") == null) {
                /**
                 * creo un superadmin por defecto
                 */
                User superadmin = new User();
                superadmin.setUsername("admin@admin.com");
                superadmin.setEmail("admin@admin.com");
                superadmin.setPassword(BCrypt.hashpw("123456", BCrypt.gensalt(10)));
                superadmin.setRole(roleSuperAdmin);
                userDao.save(superadmin);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public User updateUser(User usuario){
        userDao.save(usuario);
        /**
         * Y a correr!!!
         * La vida puede ser maravillosa... Daimiel!!!
         */
        return usuario;
    }






    @Override
    public Page<User> getUsersPaged(int pageSize, int indicePage){
        Page<User> users = userDao.findAll(PageRequest.of(indicePage, pageSize));
        return users;
    }
}
