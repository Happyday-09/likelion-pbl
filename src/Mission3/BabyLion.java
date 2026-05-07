package Mission3;

public class BabyLion extends Member {
    private String studentId;

    public BabyLion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    public AssignmentPolicy getAssignmentPolicy() {
        return new LionAssignmentPolicy();
    }

    @Override
    public String getRoleName() {
        return "아기사자";
    }

    @Override
    public String getRoleIcon() {
        return "🦁";
    }

    @Override
    public String getRoleDetail() {
        return "🆔 학번: " + studentId;
    }
}
