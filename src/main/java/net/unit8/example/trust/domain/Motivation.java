package net.unit8.example.trust.domain;

public abstract class Motivation {
    private MotivationGrade grade;

    protected Motivation(MotivationGrade grade) {
        this.grade = grade;
    }
}
