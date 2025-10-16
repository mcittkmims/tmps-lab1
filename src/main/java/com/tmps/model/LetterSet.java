package com.tmps.model;

import java.util.HashSet;
import java.util.Set;

public class LetterSet {
    private final Set<Character> letters;

    public LetterSet(String word){
        letters = new HashSet<>();
        for (char c : word.toCharArray()) {
            letters.add(c);
        }
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof LetterSet letterSet)) return false;
        return letters.equals(letterSet.letters);
    }

    @Override
    public int hashCode(){
        return letters.hashCode();
    }
}
