package net.unit8.example.specification;

import net.unit8.example.SearchCondition;
import net.unit8.example.Selector;
import net.unit8.example.entity.Address;
import net.unit8.example.entity.Customer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

/**
 * 住所で絞り込むためのセレクタ.
 */
public class AddressSelector implements Selector<SearchCondition, Customer> {
    @Override
    public javax.persistence.criteria.Predicate getCriteria(CriteriaBuilder cb, Root<Customer> customerRoot, SearchCondition condition) {
        final Join<Customer, Address> address = customerRoot.join("address");
        return cb.equal(address.get("prefectureCd"), condition.getPrefectureCd());
    }
}
