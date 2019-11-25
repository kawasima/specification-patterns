package net.unit8.example;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchCondition {
    private String prefectureCd;
}
