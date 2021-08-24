package net.unit8.example.trust.adapter.persistence;

import net.unit8.example.trust.domain.RegularUser;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public RegularUser entityToDomain(UserJpaEntity entity) {

    }

    public UserJpaEntity domainToEntity(RegularUser user) {

    }
}
