package com.ppx.testutils.core.temp;

import com.ppx.testutils.common.DesktopMachine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

/**
 * @author Pratyay
 **/
public class FileViewer {
    private static final Logger LOGGER = Logger.getLogger(FileViewer.class.getName());
    private final FileCreator fileCreator;

    public FileViewer(FileCreator fileCreator) {
        this.fileCreator = fileCreator;
    }

    public CompletableFuture<Void> view(DesktopMachine desktopMachine) {
        return CompletableFuture.runAsync(()->{
            Path absPath = Paths.get(fileCreator.getPath()).toAbsolutePath();
            String path = absPath.toString().replace("/.", "");
            System.out.println("opening file!");
            switch (desktopMachine){
                case LINUX_DESKTOP:
                    try {
                        String[] command = {"gedit", path};
                        ProcessBuilder processBuilder = new ProcessBuilder(command);
                        Process process = processBuilder.start();
                        process.waitFor();
                    }
                    catch (Exception e){
                        LOGGER.warning(e.getMessage());
                    }

                case WINDOWS:
                    try {
                        String[] command = {"notepad", path};
                        ProcessBuilder processBuilder = new ProcessBuilder(command);
                        Process process = processBuilder.start();
                        process.waitFor();
                    }
                    catch (Exception e){
                        LOGGER.warning(e.getMessage());
                    }

                case MAC:
                    try {
                        String[] command = {"open", "-e", path};
                        ProcessBuilder processBuilder = new ProcessBuilder(command);
                        Process process = processBuilder.start();
                        process.waitFor();
                    }
                    catch (Exception e){
                        LOGGER.warning(e.getMessage());
                    }
            }
        });
    }
}
