package Mission6.policy;

import Mission6.domain.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}