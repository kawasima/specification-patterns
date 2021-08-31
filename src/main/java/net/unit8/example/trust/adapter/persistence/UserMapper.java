package net.unit8.example.trust.adapter.persistence;

import net.unit8.example.trust.domain.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User entityToDomain(UserJpaEntity entity) {
        return null;
    }

    public UserJpaEntity domainToEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId().getValue());
        return entity;
    }
}
