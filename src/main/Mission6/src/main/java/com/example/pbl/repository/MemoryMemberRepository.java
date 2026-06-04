package com.example.pbl.repository;

import com.example.pbl.role.Role;
import org.springframework.stereotype.Repository;

import java.util.*;

// ✅ Mission5 package2/MemoryMemberRepository.java → @Repository로 자동 등록
@Repository
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
