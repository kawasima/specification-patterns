package net.unit8.example.trust.adapter.persistence;

import net.unit8.example.trust.domain.DisclosureSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;

@Component
public class DisclosureSpecificationMapper {
    public Specification<UserJpaEntity> specificationToPredicate(DisclosureSpecification specification) {
        return (root, query, cb) -> {
            Join<UserJpaEntity, UserJpaEntity> friends = root.join("friends", JoinType.LEFT);
            return friends;
        };
    }
}
