package Mission5.role;

import Mission5.policy.StaffSubmissionPolicy;

public class Staff extends Role {
    public Staff(String name, String major, int cohort, String part, String studentId) {
        super(name, major, cohort, part, studentId, new StaffSubmissionPolicy());
    }

    @Override
    public String getRoleName() {
        return "운영진";
    }
}
