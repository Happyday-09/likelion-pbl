package Mission3;

public class Staff extends Member {
    private String position;

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }

    @Override
    public AssignmentPolicy getAssignmentPolicy() {
        return new StaffAssignmentPolicy();
    }

    @Override
    public String getRoleName() {
        return "운영진";
    }

    @Override
    public String getRoleIcon() {
        return "👨‍💼";
    }

    @Override
    public String getRoleDetail() {
        return "⭐ 직책: " + position;
    }
}