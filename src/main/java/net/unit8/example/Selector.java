package net.unit8.example;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

/**
 * セレクタのインタフェース.
 *
 * @param <T> 検索条件の型
 * @param <S> 対象エンティティの型
 */
public interface Selector<T, S> {
    javax.persistence.criteria.Predicate getCriteria(CriteriaBuilder cb, Root<S> root, T condition);
}
