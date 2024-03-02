package com.ppx.testutils.core.temp;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * @author Pratyay
 **/
public class FileCreator {
    private static final Logger LOGGER = Logger.getLogger(FileCreator.class.getName());
    private String path;

    public String getPath() {
        return path;
    }

    public FileCreator(){
        this.path = "./".concat("test-output");

    }
    
    public CompletableFuture<Void> newFile(){
        return CompletableFuture.runAsync(()->{
            create(path).thenAccept(v -> System.out.println("test file created!"));
        });
    }

    private CompletableFuture<Void> create(String path){
        return CompletableFuture.runAsync(()->{
            try {
                Files.createFile(Paths.get(path));
            }
            catch (Exception e){
                LOGGER.warning(e.getMessage());
            }
        });
    }
}
