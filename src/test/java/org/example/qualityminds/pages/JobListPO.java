package org.example.qualityminds.pages;

import org.assertj.core.api.Assertions;
import org.example.qualityminds.base.BasePage;
import org.example.qualityminds.base.I18n;
import org.example.qualityminds.utils.StringLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JobListPO extends BasePage implements Loadable<JobListPO> {

    private static final String URL = "/de/karriere/stellenangebote/";
    private static final String TITLE = "QualityMinds | Stellenangebote";
    private static final String H1_TEXT = "Stellenangebote";
    private final By h1 = By.tagName("h1");
    private final By jobTile = By.cssSelector("div.awsm-job-listing-item");
    private final By moreSpan = By.cssSelector("span.awsm-job-more");

    public JobListPO(WebDriver driver) {
        super(driver);
    }

    @Override
    public JobListPO load() {
        load(URL);
        waitForPageToLoad();
        return this;
    }

    @Override
    public JobListPO waitForPageToLoad() {
        waitForPageTitle(TITLE);
        waitForPageUrl(URL);
        logPageLoaded();
        return this;
    }

    public JobListPO closeCookies() {
        acceptCookies();
        return this;
    }

    public JobListPO verifyAtLeastOneJobOfferExists() {
        Assertions.assertThat(getVisibleElements(jobTile).size()).isGreaterThan(0);
        return this;
    }

    public JobOfferPO clickOnFirstJobOffer() {
        getVisibleElements(moreSpan).get(0).click();
        return new JobOfferPO(driver);
    }

    private void verifyH1Text(I18n i18n) {
        Assertions.assertThat(getText(h1)).isEqualTo(StringLoader.getInstance().strings(i18n).get(H1_TEXT).asText());
    }
}
