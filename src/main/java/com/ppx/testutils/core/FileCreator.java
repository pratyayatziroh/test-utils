package com.ppx.testutils.core;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

/**
 * @author Pratyay
 **/
public class FileCreator {
    private String path;

    public String getPath() {
        return path;
    }

    public FileCreator(String path){
        this.path = path;
    }

    public FileCreator(){

    }

    public CompletableFuture<Void> newFile(){
        return CompletableFuture.runAsync(()->{
            if(path == null) {
                this.path = "./".concat("test-output");
                create(path).thenAccept(v -> System.out.println("test file created!"));
            }
            else
                create(path).thenAccept(v-> System.out.println("test file created!"));
        });
    }

    private CompletableFuture<Void> create(String path){
        return CompletableFuture.runAsync(()->{
            try {
                Files.createFile(Paths.get(path));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
    }
}
