package org.example.qualityminds.pages;

import org.example.qualityminds.base.BasePage;

public interface Loadable<T extends BasePage> {

    T load();
    T waitForPageToLoad();
}
