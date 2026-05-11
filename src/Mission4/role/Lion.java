package Mission4.role;

import Mission4.policy.LionSubmissionPolicy;

public class Lion extends Role {
    private String studentId;

    public Lion(String name, String major, int cohort, String part, String studentId) {
        super(name, major, cohort, part, new LionSubmissionPolicy());
        this.studentId = studentId;
    }

    @Override
    public String getRoleName() {
        return "아기사자";
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("학번: " + studentId);
    }
}