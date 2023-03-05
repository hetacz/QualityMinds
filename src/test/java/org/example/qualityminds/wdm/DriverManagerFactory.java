package org.example.qualityminds.wdm;

import org.example.qualityminds.base.BrowserType;
import org.example.qualityminds.wdm.driver.ChromeDriverManager;
import org.example.qualityminds.wdm.driver.FirefoxDriverManager;

public final class DriverManagerFactory {

    public static DriverManager getManager(BrowserType browserType) {
        return switch (browserType) {
            case CHROME -> new ChromeDriverManager();
            case FIREFOX -> new FirefoxDriverManager();
        };
    }
}
