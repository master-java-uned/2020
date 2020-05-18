/**
 * Peter Fight
 *
 * Si los users tienen permisos y roles habrá que crear sus respectivos daos ni que sea para crearlos...
 * vamos, digo yo.
 */

package com.covid19.authservice.dao.Permission;


import com.covid19.authservice.model.Permission;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PermissionDao extends Neo4jRepository<Permission, Long> {

    Permission findByRefId(Long refId);
}
