package com.tmps.algorithm;

import java.util.*;

public abstract class WordGrouper<K> {

    public Collection<List<String>> group(Collection<String> words) {
        Map<K, List<String>> map = new HashMap<>();
        for (String word : words) {
            K key = createKey(word);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(word);
        }
        return map.values();
    }

    protected abstract K createKey(String word);
}
