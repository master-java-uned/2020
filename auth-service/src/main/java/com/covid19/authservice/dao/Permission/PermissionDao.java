/**
 * Peter Fight
 *
 * (27/06/2020) All my comments and fucking variables translated
 * at Victor's good practice accomplishment request)
 *
 If users have permissions and roles, it will be necessary to create their respective data or to create them ...
 Come on, I say.
 */

package com.covid19.authservice.dao.Permission;


import com.covid19.authservice.model.Permission;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface PermissionDao extends Neo4jRepository<Permission, Long> {

    Permission findByRefId(Long refId);
}
