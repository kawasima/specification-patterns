package net.unit8.example;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

public interface Selector<T, S> {
    javax.persistence.criteria.Predicate getCriteria(CriteriaBuilder cb, Root<S> root, T condition);
}
