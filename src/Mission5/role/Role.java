package Mission5.role;

import Mission5.policy.SubmissionPolicy;

public abstract class Role {
    private String name;
    private String major;
    private int cohort;
    private String part;
    private String studentId;
    private SubmissionPolicy submissionPolicy;

    public Role(String name, String major, int cohort, String part, String studentId, SubmissionPolicy submissionPolicy) {
        this.name = name;
        this.major = major;
        this.cohort = cohort;
        this.part = part;
        this.studentId = studentId;
        this.submissionPolicy = submissionPolicy;
    }

    public String getName() {
        return name;
    }

    public abstract String getRoleName();

    public void printInfo() {
        System.out.println("👤 역할: " + getRoleName());
        System.out.println("🚀 이름: " + name + " | 🎓 전공: " + major + " | 🔢 기수: " + cohort + " | 💻 파트: " + part);
        System.out.println("🆔 학번: " + studentId);
        String submitStatus = submissionPolicy.canSubmit() ? "✅ 가능" : "❌ 불가능";
        System.out.println("📝 과제 제출 가능: " + submitStatus);
    }
}
