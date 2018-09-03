package net.enablers.tvstack.pages.web;
import org.openqa.selenium.WebElement;
import net.serenitybdd.core.Serenity;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import  net.enablers.tvstack.helpers.*;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import static org.assertj.core.api.Assertions.assertThat;
public class LandingPage extends PageObject
{

    genericFunction gen = new genericFunction();
    @FindBy(xpath = "//header//*[@class = 'header__signout']")
    WebElement logoutButton;

    @FindBy(id = "okta-signin-username")
    WebElement oktaSigninUsername;

    @FindBy(id = "okta-signin-password")
    WebElement oktaSigninPassword;

    @FindBy(id = "okta-signin-submit")
    WebElement oktaSigninSubmit;

    @FindBy(xpath = "//h1")
    WebElement h1Tag;


    public By buttonName(String ButtonText)
    {
        return By.xpath("//button//span[contains(text(),'"+ButtonText+"')]");
    }
    public void loginToApplication(String username,String Password)
    {
        gen.fnActionOnBrowser("navigate",gen.dictConfig.get(gen.dictConfig.get("Environment")));
        if(element(buttonName("Login")).isDisplayed())
        {
            element(buttonName("Login")).click();
            gen.WaitForPageLoad(120);
        }
        oktaSigninUsername.sendKeys(username);
        oktaSigninPassword.sendKeys(Password);
        oktaSigninSubmit.click();
        gen.WaitForPageLoad(120);
    }

    public void verifyUserHomePage()
    {
        assertThat(logoutButton.isDisplayed()).isTrue();
        verifyHeaderText("Welcome");
    }

    public void verifyHeaderText(String texttomatch)
    {
        assertThat(h1Tag.getText().toLowerCase().contains(texttomatch.toLowerCase())).isTrue();
    }

    public void verifySignInFailed()
    {
        assertThat(logoutButton.isDisplayed()).isFalse();
    }

}
