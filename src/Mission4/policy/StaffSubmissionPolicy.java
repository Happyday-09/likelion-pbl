package Mission4.policy;

public class StaffSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean checkSubmission() {
        // 운영진은 과제 제출 불필요
        return false;
    }
}