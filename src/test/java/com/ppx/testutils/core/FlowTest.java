package com.ppx.testutils.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper objectMapper = new ObjectMapper();
        fileWriter.write(new Person("Jacob", 27, true, true)).join();
        fileViewer.view().join();
        new Flush(fileCreator);
    }
}
