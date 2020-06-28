/**
 * Peter Fight
 *
 * (27/06/2020) All my comments and variables translated
 * at Victor's good practice accomplishment request)
 *
 Interface to force me to implement the findByRef (not the id id that
 invents the neo, but the that I impose by force of arms.
 */

package com.covid19.authservice.dao.Role;

import com.covid19.authservice.model.Permission;
import com.covid19.authservice.model.Role;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RoleDao extends Neo4jRepository<Role, Long> {
    Role findByRefId(Long refId);
}
