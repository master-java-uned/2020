/**
 * Peter Fight
 *
 * (27/06/2020) All my comments and variables translated
 * at Victor's good practice accomplishment request)
 *
 I create the abstract class so that I can implement only the interface method
 that interests me and not all the methods that would be a fat bundle.
 */

package com.covid19.authservice.dao.Permission;

import com.covid19.authservice.model.Permission;

import java.util.List;
import java.util.stream.Collectors;


public abstract class PermissionDaoImpl  implements PermissionDao {

    /**
     This find is used for permissions because they are few. If I had to launch
     Cypher queries with where would not work because first I load all the data
     and then filter. Consultations to saco paco they are made in the "made by me"
     project that did not bear fruit and are in the branch of the master who died
      @return
     */
    @Override
    public Permission findByRefId(Long refId) {
        Permission perm = ((List<Permission>)this.findAll()).stream()
                .filter(x -> ((Permission)x).refId == refId).collect(Collectors.toList()).get(0);
        return perm;
    }
}
