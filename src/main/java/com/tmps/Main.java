package com.tmps;

import com.tmps.algorithm.AnagramWordGrouper;
import com.tmps.algorithm.WordGrouper;
import com.tmps.io.Console;
import com.tmps.io.FileReader;
import com.tmps.io.Readable;
import com.tmps.io.Writeable;

public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor();

        Readable readable = new FileReader("sample.txt");
        Writeable writeable = new Console();
        WordGrouper<?> wordGrouper = new AnagramWordGrouper();

        processor.execute(readable, writeable, wordGrouper);

    }
}