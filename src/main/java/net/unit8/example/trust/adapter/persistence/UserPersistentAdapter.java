package net.unit8.example.trust.adapter.persistence;

import net.unit8.example.trust.application.FindHuntingTargetUsers;
import net.unit8.example.trust.domain.DisclosureSpecification;
import net.unit8.example.trust.domain.User;
import net.unit8.example.trust.domain.UserId;
import net.unit8.example.trust.domain.UserRank;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class UserPersistentAdapter implements FindHuntingTargetUsers {
    private final UserRepository userRepository;

    public UserPersistentAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findHuntingTargetUsers(UserId userId, UserRank rank, DisclosureSpecification specification) {
        return null;
    }
}
