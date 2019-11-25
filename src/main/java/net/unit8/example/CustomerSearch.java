package net.unit8.example;

import net.unit8.example.entity.Customer;
import net.unit8.example.specification.AddressSelector;
import net.unit8.example.specification.CustomerClassificationSelector;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static net.unit8.example.Predicates.ALL;
import static net.unit8.example.Predicates.nonNull;

public class CustomerSearch {
    /*
     * 検索条件で都道府県を選択したら、その都道府県に在住のカスタマのみが対象になる。
     * 検索条件によらず、退会したカスタマは検索対象としない(有効なユーザのみ)
     */
    private static final List<Specification> SELECTORS = List.of(
            Specification.of(nonNull(SearchCondition::getPrefectureCd),  new AddressSelector()),
            Specification.of(ALL, CustomerClassificationSelector.VALID)
    );

    public List<Customer> search(SearchCondition condition, EntityManager em) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        final Root<Customer> customerRoot = query.from(Customer.class);

        final Predicate[] predicates = SELECTORS.stream()
                .filter(specification -> specification.test(condition))
                .map(specification -> specification.getCriteria(cb, customerRoot, condition))
                .toArray(Predicate[]::new);
        query.where(predicates);

        return em.createQuery(query)
                .getResultList();
    }
}
