package com.ppx.testutils.core.temp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * @author Pratyay
 **/

public class FileWriter<T> {
    private static final Logger LOGGER = Logger.getLogger(FileWriter.class.getName());
    private final FileCreator fileCreator;

    public FileWriter(FileCreator fileCreator) {
        this.fileCreator = fileCreator;
    }

    public CompletableFuture<Void> write(T data) {
        return CompletableFuture.runAsync(() -> {
            try {
                String path = fileCreator.getPath();
                ObjectMapper objectMapper = new ObjectMapper();
                var encodedData = objectMapper.writeValueAsString(data);
                String prettified = prettify(encodedData);
                Files.write(Paths.get(path), prettified.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
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
