package Mission4.role;

import Mission4.policy.StaffSubmissionPolicy;

public class Staff extends Role {
    public Staff(String name, String major, int cohort, String part) {
        super(name, major, cohort, part, new StaffSubmissionPolicy());
    }

    @Override
    public String getRoleName() {
        return "운영진";
    }
}