package net.enablers.tools.pages;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class HomePage extends PageObject {

    @FindBy(xpath = "//p[@class]")
    WebElementFacade tvStackClientText;

    @FindBy(xpath = "//button")
    WebElement loginButton;

    @FindBy(id = "okta-signin-username")
    WebElement oktaSigninUsername;

    @FindBy(id = "okta-signin-password")
    WebElement oktaSigninPassword;

    @FindBy(id = "okta-signin-submit")
    WebElement oktaSigninSubmit;

    @FindBy(xpath = "//span[@class='Polaris-Button__Content']")
    WebElement logoutButton;

    //This method to get Okta Token for API calls
    public String signIntoClientAndGetOktaTokenStorage(String planner) {

        tvStackClientText.shouldBePresent();
        loginButton.click();
        waitABit(2000);
        oktaSigninUsername.sendKeys(planner);
        oktaSigninPassword.sendKeys("Great123");
        oktaSigninSubmit.click();
        waitABit(5000);

        return (String) evaluateJavascript(String.format("return window.localStorage.getItem('%s');", "okta-token-storage"));
    }

    public void logOut() {
        logoutButton.click();
        waitFor(tvStackClientText);
    }
}
