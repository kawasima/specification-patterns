package net.unit8.example.trust.domain;

import lombok.Value;

import java.util.Set;

@Value
public class RegularUser extends User{
    SideJobMotivation sideJobMotivation;
    JobChangeMotivation jobChangeMotivation;

    public RegularUser(UserId id, UserRank rank, Set<Company> companies, Set<User> friends, SideJobMotivation sideJobMotivation, JobChangeMotivation jobChangeMotivation) {
        super(id, rank, companies, friends);
        this.sideJobMotivation = sideJobMotivation;
        this.jobChangeMotivation = jobChangeMotivation;
    }

    public SideJobMotivation getSideJobMotivation() {
        return sideJobMotivation;
    }

    public JobChangeMotivation getJobChangeMotivation() {
        return jobChangeMotivation;
    }
}
