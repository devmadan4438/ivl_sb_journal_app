package com.learning.journal.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CityResponse {
    private Data data;

    @Setter
    @Getter
    public static class Data {
        private int count;
        private List<?> rows = new ArrayList<>();

    }
}

