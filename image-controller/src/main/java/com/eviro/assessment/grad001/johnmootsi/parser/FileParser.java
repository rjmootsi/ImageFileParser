package com.eviro.assessment.grad001.johnmootsi.parser;

import java.io.File;
import java.net.URI;

public interface FileParser {
    void parseCSV(File csvFile);
    File convertCSVDataToImage(String base64ImageData);
    URI createImageLink(File fileImage);
}
