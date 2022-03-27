package com.api.ingenio.prueba.repository;

import com.api.ingenio.prueba.model.ERole;
import com.api.ingenio.prueba.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** Role Data acces.
 * @author Ricardo Flamenco
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(ERole name);
}
