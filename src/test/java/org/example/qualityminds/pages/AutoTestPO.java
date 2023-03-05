package org.example.qualityminds.pages;

import org.assertj.core.api.Assertions;
import org.example.qualityminds.base.BasePage;
import org.example.qualityminds.base.I18n;
import org.example.qualityminds.utils.StringLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AutoTestPO extends BasePage implements LocaleLoadable<AutoTestPO> {

    private static final String EMAIL = "testing@qualityminds.de";
    private static final String AUTOMATION_URL = "automation_url";
    private static final String AUTOMATION_TITLE = "automation_title";
    private static final String H1_TEXT = "h1text";
    private final By h1 = By.cssSelector("h1 > span");
    private final By contactBtn = By.cssSelector("a.et_pb_button");

    public AutoTestPO(WebDriver driver) {
        super(driver);
    }

    @Override
    public AutoTestPO load(I18n i18n) {
        load(StringLoader.getInstance().strings(i18n).get(AUTOMATION_URL).asText());
        waitForPageToLoad(i18n);
        return this;
    }

    @Override
    public AutoTestPO waitForPageToLoad(I18n i18n) {
        waitForPageTitle(StringLoader.getInstance().strings(i18n).get(AUTOMATION_TITLE).asText());
        waitForPageUrl(StringLoader.getInstance().strings(i18n).get(AUTOMATION_URL).asText());
        verifyH1Text(i18n);
        logPageLoaded();
        return this;
    }

    public AutoTestPO verifyContactUsBtn() {
        scrollDown();
        Assertions.assertThat(getVisibleElement(contactBtn).getAttribute("href")).contains(EMAIL);
        return this;
    }

    private void verifyH1Text(I18n i18n) {
        Assertions.assertThat(getText(h1)).isEqualTo(StringLoader.getInstance().strings(i18n).get(H1_TEXT).asText());
    }
}
