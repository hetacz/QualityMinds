package org.example.qualityminds.pages;

import org.assertj.core.api.Assertions;
import org.example.qualityminds.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EventsPO extends BasePage implements Loadable<EventsPO> {

    private static final String URL = "https://qualityminds.com/events/";
    private static final String TITLE = "Upcoming Events – QualityMinds";
    private static final String H1_TEXT = "Events";
    private static final String NO_RESULT_MSG = "There were no results found for";
    private static final String NOW_ONWARDS = "Now onwards";
    private static final String DATE_ONWARDS = "December 31, 2021 onwards";
    private static final String ARTICLE_TITLE = "ICSTTP 2021";
    private static final String ARTICLE_START_DATE = "January 4, 2022";
    private final By h1 = By.tagName("h1");
    private final By searchFld = By.id("tribe-events-events-bar-keyword");
    private final By findBtn = By.name("submit-bar");
    private final By noResults = By.cssSelector("ul.tribe-events-c-messages__message-list > li");
    private final By calendarDrop = By.cssSelector("time.tribe-events-c-top-bar__datepicker-time");
    private final By calendarDropSpan =
            By.cssSelector("time.tribe-events-c-top-bar__datepicker-time > span:last-child");
    private final By datepicker = By.cssSelector("div.datepicker");
    private final By article = By.tagName("article");
    private final By articleTitle = By.cssSelector("article h3 > a");
    private final By articleStartDate = By.cssSelector("article time > span.tribe-event-date-start");

    public EventsPO(WebDriver driver) {
        super(driver);
    }

    @Override
    public EventsPO load() {
        load(URL);
        waitForPageToLoad();
        return this;
    }

    @Override
    public EventsPO waitForPageToLoad() {
        waitForPageTitle(TITLE);
        waitForPageUrl(URL);
        verifyH1Text();
        logPageLoaded();
        return this;
    }

    public EventsPO searchFor(String input) {
        type(searchFld, input);
        click(findBtn);
        return this;
    }

    public EventsPO verifyNoResults() {
        Assertions.assertThat(getText(noResults).strip()).contains(NO_RESULT_MSG);
        return this;
    }

    public EventsPO verifyDatepickerBtnTextBefore() {
        verifyDatepickerBtnText(NOW_ONWARDS);
        return this;
    }

    public EventsPO verifyDatepickerBtnTextAfter() {
        verifyDatepickerBtnText(DATE_ONWARDS);
        return this;
    }

    public DatepickerPO openDatepicker() {
        click(calendarDrop);
        getVisibleElement(datepicker);
        return new DatepickerPO(driver);
    }

    public EventsPO verifySingleResult() {
        Assertions.assertThat(getVisibleElements(article).size()).isEqualTo(1);
        return this;
    }

    public EventsPO verifyResultTitle() {
        Assertions.assertThat(getText(articleTitle)).contains(ARTICLE_TITLE);
        return this;
    }

    public EventsPO verifyResultStartDate() {
        Assertions.assertThat(getText(articleStartDate)).contains(ARTICLE_START_DATE);
        return this;
    }

    private void verifyH1Text() {
        Assertions.assertThat(getText(h1)).isEqualTo(H1_TEXT);
    }

    private void verifyDatepickerBtnText(String expected) {
        Assertions.assertThat(getText(calendarDropSpan).strip()).contains(expected);
    }
}
