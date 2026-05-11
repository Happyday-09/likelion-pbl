package Mission4.role;

import Mission4.policy.SubmissionPolicy;

public abstract class Role {
    protected String name;
    protected String major;
    protected int cohort;
    protected String part;
    protected SubmissionPolicy submissionPolicy;

    public Role(String name, String major, int cohort, String part, SubmissionPolicy submissionPolicy) {
        this.name = name;
        this.major = major;
        this.cohort = cohort;
        this.part = part;
        this.submissionPolicy = submissionPolicy;
    }

    public String getName() {
        return name;
    }

    public String getPart() {
        return part;
    }

    public int getCohort() {
        return cohort;
    }

    public SubmissionPolicy getPolicy() {
        return submissionPolicy;
    }

    public abstract String getRoleName();

    public void printDetails() {
        System.out.println("✨ [검색 결과]");
        System.out.println("🎭 역할: " + getRoleName());
        System.out.println("이름: " + name + " | 전공: " + major + " | 기수: " + cohort + " | 파트: " + part);
    }
}