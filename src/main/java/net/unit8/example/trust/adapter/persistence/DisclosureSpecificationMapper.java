package net.unit8.example.trust.adapter.persistence;

import net.unit8.example.trust.domain.DisclosureSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 公開仕様からクエリに変換する。
 *
 * - 有料ユーザは友達の友達まで参照できる。
 * - 無料ユーザは友達まで参照できる。
 * - ブロックされていたら参照できない。
 */
@Component
public class DisclosureSpecificationMapper {
    private final UserMapper userMapper;

    public DisclosureSpecificationMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Specification<UserJpaEntity> specificationToPredicate(DisclosureSpecification specification) {
        UserJpaEntity userEntity = userMapper.domainToEntity(specification.getUser());

        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = new ArrayList<>();
            // 自分自身は除外する
            predicates.add(cb.notEqual(root.get("id"), userEntity.getId()));
            Join<UserJpaEntity, UserJpaEntity> friendsJoin = root.join("friends", JoinType.LEFT);
            if (specification.canViewFriendsOfFriend()) {
                // 友達の友達まで参照できる場合は、さらにfriendsをJOINする
                Join<UserJpaEntity, UserJpaEntity> friendsOfFriendsJoin = friendsJoin.join("friends", JoinType.LEFT);
                predicates.add(cb.or(cb.equal(friendsJoin.get("id"), userEntity.getId()),
                        cb.equal(friendsOfFriendsJoin.get("id"), userEntity.getId())));
            } else {
                // 友達まで参照できる場合
                predicates.add(cb.equal(friendsJoin.get("id"), userEntity.getId()));
            }

            // ブロックされているユーザは含めない
            Subquery<BlockUserJpaEntity> blockQuery = query.subquery(BlockUserJpaEntity.class);
            Root<BlockUserJpaEntity> blockRoot = blockQuery.from(BlockUserJpaEntity.class);
            blockQuery.select(blockRoot)
                    .where(cb.equal(blockRoot.get("blocker"), root.get("id")),
                            cb.equal(blockRoot.get("blocked"), userEntity));
            predicates.add(cb.not(cb.exists(blockQuery)));
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }
}
