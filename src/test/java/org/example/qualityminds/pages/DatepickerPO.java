package org.example.qualityminds.pages;

import org.example.qualityminds.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DatepickerPO extends BasePage {

    private final By monthSelector = By.cssSelector("div.datepicker > div.datepicker-days th.datepicker-switch");
    private final By yearSelector = By.cssSelector("div.datepicker > div.datepicker-months th.datepicker-switch");
    private final By year2021 =
            By.xpath("//div[contains(@class,'datepicker')]/div[@class='datepicker-years']//span[text()='2021']");
    private final By monthDec =
            By.xpath("//div[contains(@class,'datepicker')]/div[@class='datepicker-months']//span[text()='Dec']");
    private final By day31 =
            By.xpath("//div[contains(@class,'datepicker')]/div[@class='datepicker-days']//td[text()='31']");
    private final By dots = By.cssSelector("div.tribe-events-view-loader__dots");

    protected DatepickerPO(WebDriver driver) {
        super(driver);
    }

    public EventsPO selectDate() {
        click(monthSelector);
        click(yearSelector);
        click(year2021);
        click(monthDec);
        click(day31);
        getVisibleElement(dots);
        waitForElementToDisappear(dots);
        return new EventsPO(driver);
    }
}
