package net.unit8.example.trust.domain;

import java.util.Set;

public class UnrevealedUser extends User {
    private UnrevealedUser(UserId id, UserRank rank, Set<Company> companies, Set<User> friends) {
        super(id, rank, companies, friends);
    }

    public static UnrevealedUser unrevealed(User user) {
        if (user instanceof UnrevealedUser) {
            return (UnrevealedUser) user;
        } else {
            return new UnrevealedUser(user.getId(), user.getRank(), user.getCompanies(), user.friends);
        }
    }
}
