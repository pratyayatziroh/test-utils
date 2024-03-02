package com.ppx.testutils.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ppx.testutils.common.DesktopMachine;
import com.ppx.testutils.core.temp.FileCreator;
import com.ppx.testutils.core.temp.FileViewer;
import com.ppx.testutils.core.temp.FileWriter;
import com.ppx.testutils.core.temp.Flush;
import org.junit.jupiter.api.Test;

/**
 * @author Pratyay
 **/
class FlowTest {
    @Test
    void flow() throws JsonProcessingException {
        FileCreator fileCreator = new FileCreator();
        FileWriter fileWriter = new FileWriter(fileCreator);
        FileViewer fileViewer = new FileViewer(fileCreator);
        fileCreator.newFile().join();
        fileWriter.write(new Person("Jacob", 27, true, true)).join();
        fileViewer.view(DesktopMachine.LINUX_DESKTOP).join();
        new Flush(fileCreator);
    }
}
