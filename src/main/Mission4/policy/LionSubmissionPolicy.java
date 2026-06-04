package Mission4.policy;

public class LionSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean checkSubmission() {
        // 아기사자는 과제 제출 가능
        return true;
    }
}