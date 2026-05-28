package com.example.pbl.role;

import com.example.pbl.policy.StaffSubmissionPolicy;

// ✅ Mission5 class5/role/Staff.java 그대로 이전
public class Staff extends Role {
    public Staff(String name, String major, int cohort, String part, String studentId) {
        super(name, major, cohort, part, studentId, new StaffSubmissionPolicy());
    }

    @Override
    public String getRoleName() {
        return "운영진";
    }
}
