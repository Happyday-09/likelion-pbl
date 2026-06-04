package Mission7.repository;

import Mission7.domain.role.Role;
import java.util.Optional;

public interface MemberRepository {

    void save(Role member);

    Optional<Role> findByName(String name);

    void updateByName(String name, Role member);

    boolean deleteByName(String name);

    boolean existsByName(String name);
}