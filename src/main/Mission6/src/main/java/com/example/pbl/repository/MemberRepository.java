package com.example.pbl.repository;

import com.example.pbl.role.Role;
import java.util.List;

// ✅ Mission5 package2/MemberRepository.java (인터페이스) 그대로 이전
public interface MemberRepository {
    void save(Role role);
    Role findByName(String name);
    List<Role> findAll();
    boolean existsByName(String name);
}