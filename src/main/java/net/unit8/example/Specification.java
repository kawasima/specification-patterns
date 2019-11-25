package net.unit8.example;

import net.unit8.example.entity.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

public class Specification {
    private final Predicate<SearchCondition> predicate;
    private final Selector selector;
    private Specification(Predicate predicate, Selector selector) {
        this.predicate = predicate;
        this.selector = selector;
    }

    public static Specification of(Predicate predicate, Selector selector) {
        return new Specification(predicate, selector);
    }

    public boolean test(SearchCondition cond) {
        return predicate.test(cond);
    }

    public javax.persistence.criteria.Predicate getCriteria(CriteriaBuilder cb, Root<Customer> customer, SearchCondition cond) {
        return selector.getCriteria(cb, customer, cond);
    }

}
