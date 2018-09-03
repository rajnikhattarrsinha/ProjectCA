package net.enablers.tvstack.steps.web;
import cucumber.api.Transform;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import net.enablers.tvstack.pages.web.LandingPage;
import net.enablers.tvstack.helpers.testData;


public class LandingPageSteps
{
    LandingPage landingPage;

    @When("^I login as '(.*)' user with password '(.*)'$")
    public void iLoginToApplication(@Transform(testData.class) String userName, @Transform(testData.class) String password) throws Throwable
    {
        landingPage.loginToApplication(userName,password);
    }

    @Then("^I should see user's home page")
    public void IShouldSeeUserHomePage() throws Throwable
    {
        landingPage.verifyUserHomePage();
    }

    @Then("^I should see Sign in failed")
    public void IShouldSeeSigninFailed() throws Throwable
    {
        landingPage.verifySignInFailed();
    }

}
