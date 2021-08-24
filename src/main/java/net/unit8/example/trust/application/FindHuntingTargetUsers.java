package net.unit8.example.trust.application;

import net.unit8.example.trust.domain.RegularUser;
import net.unit8.example.trust.domain.UserId;
import net.unit8.example.trust.domain.UserRank;
import org.springframework.data.domain.Page;

public interface FindHuntingTargetUsers {
    Page<RegularUser> findHuntingTargetUsers(UserId userId, UserRank rank);
}
