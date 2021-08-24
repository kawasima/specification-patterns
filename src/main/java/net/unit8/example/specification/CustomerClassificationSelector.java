package net.unit8.example.specification;

import net.unit8.example.entity.CustomerClassification;
import net.unit8.example.SearchCondition;
import net.unit8.example.Selector;
import net.unit8.example.entity.Address;
import net.unit8.example.entity.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.EnumSet;
import java.util.function.Predicate;

import static net.unit8.example.entity.CustomerClassification.*;

/**
 * 顧客のクラスで絞り込むためのセレクタ.
 */
public class CustomerClassificationSelector implements Selector<SearchCondition, Customer> {
    private final EnumSet<CustomerClassification> classifications;

    public static final CustomerClassificationSelector GOLD_MEMBERS = new CustomerClassificationSelector(
            PLATINUM, GOLD
    );

    public static final CustomerClassificationSelector VALID = new CustomerClassificationSelector(
            PLATINUM, GOLD, SILVER, BRONZE
    );


    private CustomerClassificationSelector(CustomerClassification first, CustomerClassification... rest) {
        classifications = EnumSet.of(first, rest);
    }

    @Override
    public javax.persistence.criteria.Predicate getCriteria(CriteriaBuilder cb, Root<Customer> customerRoot, SearchCondition condition) {
        return customerRoot.get("classification").in(classifications);
    }
}
