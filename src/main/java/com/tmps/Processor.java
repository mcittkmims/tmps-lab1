package com.tmps;

import com.tmps.algorithm.WordGrouper;
import com.tmps.io.Readable;
import com.tmps.io.Writeable;

import java.util.Collection;
import java.util.List;

public class Processor {

    public void execute(Readable readable, Writeable writeable, WordGrouper<?> wordGrouper){
        List<String> words = readable.read();
        Collection<List<String>> groupedWords = wordGrouper.group(words);

        for(List<String> wordGroup: groupedWords){
            writeable.write(wordGroup);
        }
    }
}
