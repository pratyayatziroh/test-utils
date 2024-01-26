package com.ppx.testutils.core;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

/**
 * @author Pratyay
 **/
public class FileViewer {

    private final FileCreator fileCreator;

    public FileViewer(FileCreator fileCreator) {
        this.fileCreator = fileCreator;
    }

    public CompletableFuture<Void> view() {
        return CompletableFuture.runAsync(()->{
            try {
                Path absPath = Paths.get(fileCreator.getPath()).toAbsolutePath();
                String path = absPath.toString().replace("/.", "");
                String [] command = {"gedit", path};
                System.out.println("opening file!");
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                Process process = processBuilder.start();
                process.waitFor();
            }
            catch (Exception e){
                System.out.println("could not view file!");
            }
        });
    }
}
