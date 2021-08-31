package net.unit8.example.trust.domain;

/**
 * Motivation公開の仕様
 *
 * あなたの意欲が「公開」される場合
 *
 * ①非公開設定済みの所属を除く友達
 * ②友達の友達である公式リクルーター(採用担当者)
 * ③ ②の方と同じ所属で一緒に採用活動を行う公式リクルーター（採用担当者）
 *
 * あなたの意欲が「非公開」の場合
 * ①あなたが所属している企業 又は『意欲が"非公開"の所属』に登録されている会社に所属しているユーザー
 * ②あなたがブロックしているユーザー
 */
public class DisclosureSpecification implements Specification<User> {
    private final User user;

    public DisclosureSpecification(User user) {
        this.user = user;
    }

    @Override
    public boolean isSatisfiedBy(User other) {
        if (user.isFriend(other)) {
            return true;
        } else if (user.getRank() == UserRank.PAID && user.friends(this).stream().anyMatch(f -> f.isFriend(other))) {
            return true;
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public boolean canViewFriendsOfFriend() {
        return user.getRank() == UserRank.PAID;
    }
}
