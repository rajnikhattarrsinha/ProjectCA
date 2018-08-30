package net.enablers.tools.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;

public class AdminHomePage extends PageObject {

    @FindBy(xpath = "//p[@class]")
    WebElementFacade administrationText;

    @FindBy(xpath = "//button")
    WebElement loginButton;

    @FindBy(xpath = "//header//a[@class = 'header__signout']")
    WebElement logoutButton;

    @FindBy(id = "okta-signin-username")
    WebElement oktaSigninUsername;

    @FindBy(id = "okta-signin-password")
    WebElement oktaSigninPassword;

    @FindBy(id = "okta-signin-submit")
    WebElement oktaSigninSubmit;

    //This method to get Okta Token for API calls
    public String signInAndGetOktaTokenStorage(String user) {
        administrationText.shouldBePresent();
        loginButton.click();
        waitABit(2000);
        oktaSigninUsername.sendKeys(user);
        oktaSigninPassword.sendKeys("m!ghtyM33ty");
        oktaSigninSubmit.click();
        waitABit(2000);

        return (String) evaluateJavascript(String.format("return window.localStorage.getItem('%s');", "okta-token-storage"));
    }

    public void logOut() {
        logoutButton.click();
        waitFor(administrationText);
    }

}

