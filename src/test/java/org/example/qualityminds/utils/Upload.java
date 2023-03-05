package org.example.qualityminds.utils;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@UtilityClass
public class Upload {

    public String parseFilename(String filename) {
        return decodeQuery(getFile(filename).getPath());
    }

    private String decodeQuery(String query) {
        return URLDecoder.decode(query, StandardCharsets.UTF_8);
    }

    private File getFile(String filename) {
        return new File(Objects.requireNonNull(Upload.class.getClassLoader().getResource(filename)).getFile());
    }
}
