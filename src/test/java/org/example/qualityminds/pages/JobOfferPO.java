package org.example.qualityminds.pages;

import org.assertj.core.api.Assertions;
import org.example.qualityminds.base.BasePage;
import org.example.qualityminds.utils.Upload;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.stream.Collectors;

public class JobOfferPO extends BasePage implements Loadable<JobOfferPO> {

    private static final String H1_TEXT = "Frontend Developer (m/w/d)";
    private static final String URL = "https://qualityminds.com/de/stellenangebote/frontend-developer-m-w-d/";
    private static final String TITLE = "Frontend Developer (m/w/d) | QualityMinds";
    private static final String STD_ERR_MSG = "Dies ist ein Pflichtfeld.";
    private static final String INVALID_ERR_MSG = "Bitte gebe eine gültige E-Mail-Adresse ein.";
    private final By h1 = By.cssSelector("h1.et_pb_module_header");
    private final By applyBtn = By.name("form_sub");
    private final By nameErr = By.id("awsm-applicant-name-error");
    private final By emailErr = By.id("awsm-applicant-email-error");
    private final By phoneErr = By.id("awsm-applicant-phone-error");
    private final By coverLetterErr = By.id("awsm-cover-letter-error");
    private final By nameFld = By.id("awsm-applicant-name");
    private final By emailFld = By.id("awsm-applicant-email");
    private final By phoneFld = By.id("awsm-applicant-phone");
    private final By coverLetterFld = By.id("awsm-cover-letter");
    private final By cvFld = By.id("awsm-application-file");
    private final By checkbox = By.id("awsm_form_privacy_policy");
    private final By paragraph = By.cssSelector("p.has-medium-font-size:first-child");
    private final By uploadBtnText = By.cssSelector("div.custom-input");

    protected JobOfferPO(WebDriver driver) {
        super(driver);
    }

    @Override
    public JobOfferPO load() {
        load(URL);
        waitForPageToLoad();
        return this;
    }

    @Override
    public JobOfferPO waitForPageToLoad() {
        waitForPageTitle(TITLE);
        waitForPageUrl(URL);
        verifyH1Text();
        logPageLoaded();
        return this;
    }

    public JobOfferPO closeCookies() {
        acceptCookies();
        return this;
    }

    public JobOfferPO clickApplyBtn() {
        click(applyBtn);
        return this;
    }

    public JobOfferPO verifyErrMsg() {
        Assertions.assertThat(getVisibleElement(nameErr).isDisplayed()).isTrue();
        Assertions.assertThat(getVisibleElement(emailErr).isDisplayed()).isTrue();
        Assertions.assertThat(getVisibleElement(phoneErr).isDisplayed()).isTrue();
        Assertions.assertThat(getVisibleElement(coverLetterErr).isDisplayed()).isTrue();
        return this;
    }

    public JobOfferPO typeName(String fullName) {
        type(nameFld, fullName);
        return this;
    }

    public JobOfferPO verifyErrMsgAfterNameInput() {
        Assertions.assertThat(getExistingElement(nameErr).isDisplayed()).isFalse();
        Assertions.assertThat(getVisibleElement(emailErr).isDisplayed()).isTrue();
        Assertions.assertThat(getVisibleElement(phoneErr).isDisplayed()).isTrue();
        Assertions.assertThat(getVisibleElement(coverLetterErr).isDisplayed()).isTrue();
        return this;
    }

    public JobOfferPO typeEmailEmoji() {
        String jsCode = """
                        let input = arguments[0], txt = arguments[1];
                        input.value += txt;
                        input.dispatchEvent(new Event('change'));
                        """;
        String text = "\uD83D\uDE0A";
        js.executeScript(jsCode, getExistingElement(emailFld), text);
        type(emailFld, " ", Keys.BACK_SPACE);
        return this;
    }

    public JobOfferPO clearEmail() {
        clear(emailFld);
        return this;
    }

    public JobOfferPO verifyEmailErrMsgBefore() {
        verifyEmailErrMsg(STD_ERR_MSG);
        return this;
    }

    public JobOfferPO verifyEmailErrMsgAfter() {
        verifyEmailErrMsg(INVALID_ERR_MSG);
        return this;
    }

    public JobOfferPO verifyEmailErrMsgNotExists() {
        verifyErrMsgNotExists(emailErr);
        return this;
    }

    public JobOfferPO verifyCoverLetterErrMsgNotExists() {
        verifyErrMsgNotExists(coverLetterErr);
        return this;
    }

    public JobOfferPO typeCoverLetter() {
        type(coverLetterFld, get10WordsFromJobDescription());
        return this;
    }

    public JobOfferPO uploadFile(String filename) {
        getExistingElement(cvFld).sendKeys(Upload.parseFilename(filename));
        return this;
    }

    public JobOfferPO verifyUploadBtnText(String filename) {
        Assertions.assertThat(getText(uploadBtnText)).isEqualTo(filename);
        return this;
    }

    public JobOfferPO tickCheckbox() {
        jsClick(checkbox);
        return this;
    }

    public JobOfferPO verifyCheckboxIsTicked() {
        Assertions.assertThat(getVisibleElement(checkbox).getAttribute("aria-invalid")).isEqualTo("false");
        return this;
    }

    private String get10WordsFromJobDescription() {
        String[] strings = getText(paragraph).split(" ");
        return Arrays.stream(strings).limit(10).collect(Collectors.joining(" "));
    }

    private void verifyH1Text() {
        Assertions.assertThat(getText(h1)).isEqualTo(H1_TEXT);
    }

    private void verifyEmailErrMsg(String expected) {
        Assertions.assertThat(getText(emailErr)).isEqualTo(expected);
    }

    private void verifyErrMsgNotExists(By locator) {
        Assertions.assertThat(getExistingElement(locator).isDisplayed()).isFalse();
    }
}
