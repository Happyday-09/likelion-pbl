package Mission7.repository;

import Mission7.domain.role.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private final List<Role> store = new ArrayList<>();

    @Override
    public void save(Role member) {
        store.add(member);
    }

    @Override
    public Optional<Role> findByName(String name) {
        return store.stream()
                .filter(member -> member.getName().equals(name))
                .findFirst();
    }

    @Override
    public void updateByName(String name, Role member) {
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getName().equals(name)) {
                store.set(i, member);
                return;
            }
        }
    }

    @Override
    public boolean deleteByName(String name) {
        return store.removeIf(member -> member.getName().equals(name));
    }

    @Override
    public boolean existsByName(String name) {
        return store.stream()
                .anyMatch(member -> member.getName().equals(name));
    }
}