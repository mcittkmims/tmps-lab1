package com.tmps.algorithm;

import com.tmps.model.LetterSet;

public class LetterSetWordGrouper extends WordGrouper<LetterSet> {
    @Override
    protected LetterSet createKey(String word) {
        return new LetterSet(word);
    }
}
