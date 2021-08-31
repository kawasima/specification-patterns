package net.unit8.example.trust.adapter.persistence;

import net.unit8.example.trust.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.randomNanoId;
import static net.unit8.example.trust.domain.MotivationGrade.NONE;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({UserMapper.class, DisclosureSpecificationMapper.class})
class UserRepositoryTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DisclosureSpecificationMapper disclosureSpecificationMapper;

    private String userId;

    @BeforeEach
    void setup() {
        initMotivationCategory();

        UserJpaEntity user1 = newUser("user1", UserRank.FREEMIUM, NONE, NONE);
        UserJpaEntity user2 = newUser("user2", UserRank.FREEMIUM, NONE, NONE);
        UserJpaEntity user3 = newUser("user3", UserRank.FREEMIUM, NONE, NONE);
        UserJpaEntity user4 = newUser("user4", UserRank.FREEMIUM, NONE, NONE);
        UserJpaEntity user5 = newUser("user5", UserRank.FREEMIUM, NONE, NONE);

        blockUser(user5, user1);
        friend(user1, user2);
        friend(user2, user3);
        friend(user2, user5);
        userId = user1.getId();
    }

    @Test
    void freemiumUserCanShowFriends() {
        RegularUser user = new RegularUser(new UserId(userId), UserRank.FREEMIUM, Set.of(), Set.of(), new SideJobMotivation(NONE), new JobChangeMotivation(NONE));
        DisclosureSpecification disclosureSpecification = new DisclosureSpecification(user);
        var spec = disclosureSpecificationMapper.specificationToPredicate(disclosureSpecification);
        Page<UserJpaEntity> users = userRepository.findAll(spec, PageRequest.of(0, 10));
        assertThat(users.getTotalElements()).isEqualTo(1L);
        Set<String> disclosureUsers = Set.of("user2");
        assertThat(users.stream()).allMatch(u -> disclosureUsers.contains(u.getName()));
    }

    @Test
    void paidUserCanShowFriendsOfFriends() {
        RegularUser user = new RegularUser(new UserId(userId), UserRank.PAID, Set.of(), Set.of(), new SideJobMotivation(NONE), new JobChangeMotivation(NONE));
        DisclosureSpecification disclosureSpecification = new DisclosureSpecification(user);
        var spec = disclosureSpecificationMapper.specificationToPredicate(disclosureSpecification);
        Page<UserJpaEntity> users = userRepository.findAll(spec, PageRequest.of(0, 10));
        assertThat(users.getTotalElements()).isEqualTo(2L);
        Set<String> disclosureUsers = Set.of("user2", "user3");
        assertThat(users.stream()).allMatch(u -> disclosureUsers.contains(u.getName()));
    }

    private void friend(UserJpaEntity u1, UserJpaEntity u2) {
        u1.getFriends().add(u2);
        em.merge(u1);
        u2.getFriends().add(u1);
        em.merge(u2);
    }

    private UserJpaEntity newUser(String name, UserRank rank, MotivationGrade jobChange, MotivationGrade sideJob) {
        UserJpaEntity user = new UserJpaEntity();
        user.setId(randomNanoId());
        user.setName(name);
        user.setRank(rank);

        MotivationJpaEntity jobChangeEntity = new MotivationJpaEntity();
        jobChangeEntity.setUser(user);
        jobChangeEntity.setMotivationCategory(em.find(MotivationCategoryJpaEntity.class, "JOB_CHANGE"));
        jobChangeEntity.setGrade(jobChange);

        MotivationJpaEntity sideJobEntity = new MotivationJpaEntity();
        sideJobEntity.setUser(user);
        sideJobEntity.setMotivationCategory(em.find(MotivationCategoryJpaEntity.class, "SIDE_JOB"));
        sideJobEntity.setGrade(sideJob);

        user.setMotivations(new HashSet<>(Arrays.asList(jobChangeEntity, sideJobEntity)));
        em.persist(user);

        return user;
    }

    private BlockUserJpaEntity blockUser(UserJpaEntity blocker, UserJpaEntity blocked) {
        BlockUserJpaEntity entity = new BlockUserJpaEntity();
        entity.setBlocker(blocker);
        entity.setBlocked(blocked);
        em.persist(entity);
        return entity;
    }
    private void initMotivationCategory() {
        BiFunction<String, String, MotivationCategoryJpaEntity> createMotivationCategory = (id, name) -> {
            MotivationCategoryJpaEntity motivationCategory = new MotivationCategoryJpaEntity();
            motivationCategory.setId(id);
            motivationCategory.setName(name);
            return motivationCategory;
        };

        em.persist(createMotivationCategory.apply("JOB_CHANGE", "転職意欲"));
        em.persist(createMotivationCategory.apply("SIDE_JOB", "副業意欲"));
    }
}