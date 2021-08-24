package net.unit8.example.trust.domain;

public interface Specification<T> {
    boolean isSatisfiedBy(T entity);
}
