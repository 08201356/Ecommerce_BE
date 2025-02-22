package base.ecommerce.repository;

import base.ecommerce.model.Role;
import base.ecommerce.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleEnum roleEnum);
}
