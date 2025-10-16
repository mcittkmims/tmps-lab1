package com.tmps.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console implements Writeable, Readable{
    private final Scanner scanner;

    public Console() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public List<String> read() {
        List<String> words = new ArrayList<>();
        System.out.println("Enter words (empty line to finish):");
        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) break;
            words.add(line);
        }
        return words;
    }

    @Override
    public void write(List<String> words) {
        for (String word : words) {
            System.out.print(word + " ");
        }
        System.out.println();
    }
}
