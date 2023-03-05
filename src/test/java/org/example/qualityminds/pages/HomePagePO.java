package org.example.qualityminds.pages;

import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.example.qualityminds.base.BasePage;
import org.example.qualityminds.base.I18n;
import org.example.qualityminds.utils.StringLoader;
import org.openqa.selenium.WebDriver;

public class HomePagePO extends BasePage implements Loadable<HomePagePO> {

    private static final String TITLE = "QualityMinds | Homepage";
    private static final String HOME_URL = "home_url";

    @Getter
    private final TopBarPO topBarPO;

    public HomePagePO(WebDriver driver) {
        super(driver);
        this.topBarPO = new TopBarPO(driver);
    }

    @Override
    public HomePagePO load() {
        home();
        waitForPageToLoad();
        return this;
    }

    @Override
    public HomePagePO waitForPageToLoad() {
        waitForPageTitle(TITLE);
        waitForPageUrl(StringLoader.getInstance().strings(I18n.ENGLISH).get(HOME_URL).asText());
        logPageLoaded();
        return this;
    }

    public HomePagePO closeCookies() {
        acceptCookies();
        return this;
    }

    public HomePagePO verifyUrl(I18n i18n) {
        Assertions
                .assertThat(driver.getCurrentUrl())
                .isEqualTo(StringLoader.getInstance().strings(i18n).get(HOME_URL).asText());
        return this;
    }
}
