/**
 * Peter Fight
 *
 * Creo la clase abstracta para poder implementar sólo el método de la interfaz que me interesa y no todos
 * los métodos que sería una liada gorda.
 */

package com.covid19.authservice.dao.Permission;

import com.covid19.authservice.model.Permission;

import java.util.List;
import java.util.stream.Collectors;


public abstract class PermissionDaoImpl  implements PermissionDao {

    /**
     * Este find sirve para los permisos porque son pocos. Si tuviese que lanzar consultas Cypher con
     * where no serviría porque primero cargo todos los datos y luego filtro. Las consultas a saco paco
     * están hechas en el proyecto "made by me" que no fructificó y que están en la rama del máster que murió
     * @return
     */
    @Override
    public Permission findByRefId(Long refId) {
        Permission perm = ((List<Permission>)this.findAll()).stream()
                .filter(x -> ((Permission)x).refId == refId).collect(Collectors.toList()).get(0);
        return perm;
    }
}
