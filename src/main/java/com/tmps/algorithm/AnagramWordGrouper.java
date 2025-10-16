package com.tmps.algorithm;

import com.tmps.model.Histogram;

public class AnagramWordGrouper extends WordGrouper<Histogram> {
    @Override
    protected Histogram createKey(String word) {
        return new Histogram(word);
    }
}
