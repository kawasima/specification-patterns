package net.unit8.example.trust.domain;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class User {
    UserId id;

    public UserId getId() {
        return id;
    }

    public Set<Company> getCompanies() {
        return companies;
    }

    UserRank rank;
    Set<Company> companies;
    Set<User> friends;

    public User(UserId id, UserRank rank, Set<Company> companies, Set<User> friends) {
        this.id = id;
        this.rank = rank;
        this.companies = companies;
        this.friends = friends;
    }

    public boolean isFriend(User user) {
        return friends.contains(user);
    }

    public UserRank getRank() {
        return rank;
    }

    public Set<User> friends(DisclosureSpecification specification) {
        return friends.stream().map(u -> {
            if (specification.isSatisfiedBy(u)) {
                return u;
            } else {
                return UnrevealedUser.unrevealed(u);
            }
        }).collect(Collectors.toSet());
    }
}
