package net.unit8.example.trust.domain;

import lombok.Value;

@Value
public class SideJobMotivation extends Motivation {
    public SideJobMotivation(MotivationGrade grade) {
        super(grade);
    }
}
