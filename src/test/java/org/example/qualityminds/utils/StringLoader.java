package org.example.qualityminds.utils;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.example.qualityminds.base.AutomationException;
import org.example.qualityminds.base.I18n;

import java.io.IOException;

@Slf4j
public final class StringLoader {

    private static final String FILE_NAME = "strings.json";
    private final JsonNode strings;

    private StringLoader() throws IOException {
        this.strings = Parser.toJsonNode(FILE_NAME);
    }

    public static StringLoader getInstance() {
        return Holder.INSTANCE;
    }

    public JsonNode strings(I18n i18n) {
        return strings.get(i18n.name().toLowerCase());
    }

    private static final class Holder {

        static final StringLoader INSTANCE; // non private for optimization
        private static final String ERR_MSG = "Error while reading json files.";

        static {
            try {
                INSTANCE = new StringLoader();
            } catch (IOException e) {
                log.error(ERR_MSG, e);
                throw new AutomationException(ERR_MSG, e);
            }
        }
    }
}
