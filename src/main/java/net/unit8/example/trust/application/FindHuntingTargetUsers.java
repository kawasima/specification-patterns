package net.unit8.example.trust.application;

import net.unit8.example.trust.domain.DisclosureSpecification;
import net.unit8.example.trust.domain.User;
import net.unit8.example.trust.domain.UserId;
import net.unit8.example.trust.domain.UserRank;
import org.springframework.data.domain.Page;

public interface FindHuntingTargetUsers {
    Page<User> findHuntingTargetUsers(UserId userId, UserRank rank, DisclosureSpecification specification);
}
