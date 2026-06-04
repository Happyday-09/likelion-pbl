package Mission5.role;

import Mission5.policy.LionSubmissionPolicy;

public class Lion extends Role {
    public Lion(String name, String major, int cohort, String part, String studentId) {
        super(name, major, cohort, part, studentId, new LionSubmissionPolicy());
    }

    @Override
    public String getRoleName() {
        return "아기사자";
    }
}
