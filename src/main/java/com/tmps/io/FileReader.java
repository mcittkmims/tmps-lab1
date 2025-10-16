package com.tmps.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader implements Readable {

    private final Path filePath;

    public FileReader(String filePath) {
        this.filePath = Path.of(filePath);
    }

    @Override
    public List<String> read() {
        try {
            return Files.readAllLines(filePath);
        } catch (IOException e) {
            return List.of();
        }
    }
}
