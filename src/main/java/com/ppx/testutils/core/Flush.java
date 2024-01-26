package com.ppx.testutils.core;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Pratyay
 **/
public class Flush {
    private FileCreator fileCreator;

    public Flush(FileCreator fileCreator) {
        try {
            this.fileCreator = fileCreator;
            Path absPath = Paths.get(fileCreator.getPath()).toAbsolutePath();
            String path = absPath.toString().replace("/.", "");
            String[] command = {"rm", "-f", path};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println(path.concat(" flushed!"));
        }
        catch (Exception e){
            System.out.println("flush failed!");
        }
    }
}
