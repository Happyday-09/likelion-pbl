package Mission5.package2;

import Mission5.role.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {
    private final Map<String, Role> store = new HashMap<>();

    @Override
    public void save(Role role) {
        store.put(role.getName(), role);
    }

    @Override
    public Role findByName(String name) {
        return store.get(name);
    }

    @Override
    public List<Role> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean existsByName(String name) {
        return store.containsKey(name);
    }
}