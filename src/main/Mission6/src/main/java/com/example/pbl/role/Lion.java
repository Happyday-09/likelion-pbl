package com.example.pbl.role;

import com.example.pbl.policy.LionSubmissionPolicy;

// ✅ Mission5 class5/role/Lion.java 그대로 이전
public class Lion extends Role {
    public Lion(String name, String major, int cohort, String part, String studentId) {
        super(name, major, cohort, part, studentId, new LionSubmissionPolicy());
    }

    @Override
    public String getRoleName() {
        return "아기사자";
    }
}