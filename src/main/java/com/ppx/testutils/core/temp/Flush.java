package com.ppx.testutils.core.temp;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * @author Pratyay
 **/

public class Flush {
    private FileCreator fileCreator;
    private static final Logger LOGGER = Logger.getLogger(Flush.class.getName());

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
            LOGGER.warning(e.getMessage());
        }
    }
}
