package org.example.qualityminds.pages;

import org.assertj.core.api.Assertions;
import org.example.qualityminds.base.AutomationException;
import org.example.qualityminds.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TopBarPO extends BasePage {

    private final By portfolioDrop = By.cssSelector("ul#top-menu > li");
    private final By portfolioSubmenu = By.cssSelector("ul#top-menu > li > ul.sub-menu");
    private final By aboutUsDrop = By.cssSelector("ul#top-menu > li:nth-child(4)");
    private final By aboutUsSubmenu = By.cssSelector("ul#top-menu > li:nth-child(4) > ul.sub-menu");
    private final By eventsMenu = By.cssSelector("ul#top-menu > li:nth-child(4) > ul.sub-menu > li:last-child");
    private final By atMenu = By.cssSelector("ul#top-menu > li > ul.sub-menu > li > ul.sub-menu > li:nth-child(3)");
    private final By languageDrop = By.cssSelector("ul#top-menu > li:last-child");
    private final By languageSubmenu = By.cssSelector("ul#top-menu > li:last-child > ul.sub-menu");
    private final By languageDropCurrentIcon = By.cssSelector("ul#top-menu > li:last-child > a > img");
    private final By germanDrop =
            By.cssSelector("ul#top-menu > li > ul.sub-menu > li > a[href='https://qualityminds.com/de/']");
    private final By polishDrop =
            By.cssSelector("ul#top-menu > li > ul.sub-menu > li > a[href='https://qualityminds.com/pl/']");
    private final By englishDrop =
            By.cssSelector("ul#top-menu > li > ul.sub-menu > li > a[href='https://qualityminds.com']");

    protected TopBarPO(WebDriver driver) {
        super(driver);
    }

    public TopBarPO hoverOverLanguageDrop() {
        hoverOver(languageDrop);
        getVisibleElement(languageSubmenu);
        return this;
    }

    public TopBarPO hoverOverPortfolioMenu() {
        hoverOver(portfolioDrop);
        getVisibleElement(portfolioSubmenu);
        return this;
    }

    public TopBarPO hoverOverAboutUsMenu() {
        hoverOver(aboutUsDrop);
        getVisibleElement(aboutUsSubmenu);
        return this;
    }

    public EventsPO clickAtEventsMenu() {
        throwIfNotVisible(eventsMenu);
        click(eventsMenu);
        return new EventsPO(driver);
    }

    public AutoTestPO clickAtAutomationTestsMenu() {
        throwIfNotVisible(atMenu);
        click(atMenu);
        return new AutoTestPO(driver);
    }

    public TopBarPO clickGermanDrop() {
        throwIfNotVisible(germanDrop);
        click(germanDrop);
        return this;
    }

    public TopBarPO clickPolishDrop() {
        throwIfNotVisible(polishDrop);
        click(polishDrop);
        return this;
    }

    public TopBarPO clickEnglishDrop() {
        throwIfNotVisible(englishDrop);
        click(englishDrop);
        return this;
    }

    public TopBarPO verifyCurrentLanguageIconIsEnglish() {
        Assertions.assertThat(getVisibleElement(languageDropCurrentIcon).getAttribute("src")).contains("en.png");
        return this;
    }

    private void throwIfNotVisible(By locator) {
        if (!driver.findElement(locator).isDisplayed()) {
            throw new AutomationException("Element is not visible");
        }
    }
}
