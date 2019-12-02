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
            // 都道府県コードが入力されていれば、都道府県で絞り込む
            Specification.of(nonNull(SearchCondition::getPrefectureCd),  new AddressSelector()),
            // 有効な顧客のみ
            Specification.of(ALL, CustomerClassificationSelector.VALID)
    );

    /**
     * 顧客の検索.
     *
     * @param condition 検索条件(検索フォームに入力した値が格納されているイメージ)
     * @param em JPAのエンティティマネージャ
     * @return 条件に合致する顧客
     */
    public List<Customer> search(SearchCondition condition, EntityManager em) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Customer> query = cb.createQuery(Customer.class);
        final Root<Customer> customerRoot = query.from(Customer.class);

        final Predicate[] predicates = SELECTORS.stream()
                // 条件に合致するSpecificationのみ適用する
                .filter(specification -> specification.isSatisfiedBy(condition))
                // SpecificationからJPAのPredicateを生成
                .map(specification -> specification.getCriteria(cb, customerRoot, condition))
                .toArray(Predicate[]::new);
        query.where(predicates);

        return em.createQuery(query)
                .getResultList();
    }
}
