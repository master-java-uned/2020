/**
 * Peter Fight
 *
 * Interface para obligarme a implementar el findByRef (no el id chorras que se inventa el neo, sino el
 * que impongo yo por la fuerza de las armas.
 */

package com.covid19.authservice.dao.Role;

import com.covid19.authservice.model.Permission;
import com.covid19.authservice.model.Role;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface RoleDao extends Neo4jRepository<Role, Long> {
    Role findByRefId(Long refId);
}
