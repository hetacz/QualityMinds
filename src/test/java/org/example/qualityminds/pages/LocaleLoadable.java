package org.example.qualityminds.pages;

import org.example.qualityminds.base.BasePage;
import org.example.qualityminds.base.I18n;

public interface LocaleLoadable<T extends BasePage> {

    T load(I18n i18n);
    T waitForPageToLoad(I18n i18n);
}
