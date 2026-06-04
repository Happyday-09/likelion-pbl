package Mission3;

public abstract class Member {
    protected String name;
    protected String major;
    protected int generation;
    protected String part;

    public Member(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public abstract AssignmentPolicy getAssignmentPolicy();
    public abstract String getRoleName();
    public abstract String getRoleIcon();
    public abstract String getRoleDetail();

    public boolean canSubmitAssignment() {
        return getAssignmentPolicy().canSubmit();
    }

    public void printInfo() {
        System.out.println(getRoleIcon() + " 역할: " + getRoleName());
        System.out.println("👤 이름: " + name + " | 🎓 전공: " + major + " | 📌 기수: " + generation + " | 💻 파트: " + part);
        System.out.println(getRoleDetail());

        String submissionStatus = canSubmitAssignment() ? "✅ 가능" : "❌ 불가능";
        System.out.println("📝 과제 제출 가능 여부: " + submissionStatus);
    }
}