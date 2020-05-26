/**
 * Peter Fight
 *
 * Creo la clase abstracta para poder implementar sólo el método de la interfaz que me interesa y no todos
 * los métodos que sería una liada gorda.
 *
 */

package com.covid19.authservice.dao.Role;

import com.covid19.authservice.model.Role;

import java.util.List;
import java.util.stream.Collectors;

public abstract class RoleDaoImpl implements RoleDao {
    /**
     * Este find sirve para los permisos porque son pocos. Si tuviese que lanzar consultas Cypher con
     * where no serviría porque primero cargo todos los datos y luego filtro. Las consultas a saco paco
     * están hechas en el proyecto "made by me" que no fructificó y que están en la rama del máster que murió
     *
     * @return
     */
    @Override
    public Role findByRefId(Long refId) {
        Role role = ((List<Role>)this.findAll()).stream()
                .filter(x -> ((Role)x).refId == refId).collect(Collectors.toList()).get(0);
        return role;
    }
}
