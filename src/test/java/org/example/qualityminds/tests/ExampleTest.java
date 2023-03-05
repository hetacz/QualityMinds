package org.example.qualityminds.tests;

import org.example.qualityminds.base.BaseTest;
import org.example.qualityminds.base.I18n;
import org.example.qualityminds.pages.HomePagePO;
import org.example.qualityminds.pages.JobListPO;
import org.example.qualityminds.utils.Generate;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTest {

    private static final String DOCUMENT = "document.pdf";

    @Test
    public void testCase1() {
        HomePagePO homePagePO = new HomePagePO(driver());
        homePagePO
                .load()
                .closeCookies()
                .getTopBarPO()
                .hoverOverLanguageDrop()
                .clickGermanDrop();
        homePagePO.verifyUrl(I18n.GERMAN);
        homePagePO
                .getTopBarPO()
                .hoverOverPortfolioMenu()
                .clickAtAutomationTestsMenu()
                .waitForPageToLoad(I18n.GERMAN)
                .verifyContactUsBtn();
        homePagePO
                .load()
                .getTopBarPO()
                .verifyCurrentLanguageIconIsEnglish();
    }

    @Test
    public void testCase2() {
        new HomePagePO(driver())
                .load()
                .closeCookies()
                .getTopBarPO()
                .verifyCurrentLanguageIconIsEnglish()
                .hoverOverAboutUsMenu()
                .clickAtEventsMenu()
                .waitForPageToLoad()
                .searchFor("2021")
                .verifyNoResults()
                .verifyDatepickerBtnTextBefore()
                .openDatepicker()
                .selectDate()
                .verifyDatepickerBtnTextAfter()
                .verifySingleResult()
                .verifyResultTitle()
                .verifyResultStartDate();
    }

    @Test
    public void testCase3() {
        new JobListPO(driver())
                .load()
                .closeCookies()
                .verifyAtLeastOneJobOfferExists()
                .clickOnFirstJobOffer()
                .waitForPageToLoad()
                .clickApplyBtn()
                .verifyErrMsg()
                .typeName(Generate.name())
                .verifyErrMsgAfterNameInput()
                .verifyEmailErrMsgBefore()
                .typeEmailEmoji()
                .verifyEmailErrMsgAfter()
                .typeCoverLetter()
                .verifyCoverLetterErrMsgNotExists()
                .uploadFile(DOCUMENT)
                .verifyUploadBtnText(DOCUMENT)
                .tickCheckbox()
                .verifyCheckboxIsTicked();
    }
}
