package com.ppx.testutils.core;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;

/**
 * @author Pratyay
 **/

public class FileWriter {
    private final FileCreator fileCreator;

    public FileWriter(FileCreator fileCreator) {
        this.fileCreator = fileCreator;
    }

    public CompletableFuture<Void> write(String data) {
        return CompletableFuture.runAsync(() -> {
            try {
                String path = fileCreator.getPath();
                String prettified = prettify(data);
                Files.write(Paths.get(path), prettified.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            } catch (Exception e) {
                System.out.println("could not write inside file!");
            }
        });
    }

    private String prettify(String data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(data);
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            return objectWriter.writeValueAsString(jsonNode);
        } catch (Exception e) {
            throw new RuntimeException("could not prettify");
        }
    }
}
