package Mission5.package1;

import Mission5.role.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {
    private final Map<String, Role> store = new HashMap<>();

    public void save(Role role) {
        store.put(role.getName(), role);
    }

    public Role findByName(String name) {
        return store.get(name);
    }

    public List<Role> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean existsByName(String name) {
        return store.containsKey(name);
    }
}