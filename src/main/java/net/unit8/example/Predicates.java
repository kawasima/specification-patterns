package net.unit8.example;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Predicates {
    public static final Predicate<SearchCondition> ALL = cond -> true;
    public static final Predicate<SearchCondition> nonNull(Function<SearchCondition, Object> func) {
        return new Predicate<>() {
            @Override
            public boolean test(SearchCondition condition) {
                return Objects.nonNull(func.apply(condition));
            }
        };
    }
}
