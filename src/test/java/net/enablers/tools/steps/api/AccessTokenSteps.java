package net.enablers.tools.steps.api;


import com.typesafe.config.Config;
import cucumber.api.java.en.Given;
import net.enablers.tools.model.api.OktaTokenStorage;
import net.enablers.tools.pages.AdminHomePage;
import net.enablers.tools.pages.HomePage;
import net.enablers.tools.utilities.ConfigLoader;

import static net.enablers.tools.helpers.ApiHelper.gson;

public class AccessTokenSteps {

    AdminHomePage adminHomePage;
    OktaTokenStorage oktaTokenStorage;
    HomePage homePage;
    static Config conf = ConfigLoader.load();
    private static String oktaAccessToken;

    public static String getOktaAccessToken() {
        return oktaAccessToken;
    }

    public static void setOktaAccessToken(String oktaAccessToken) {
        AccessTokenSteps.oktaAccessToken = oktaAccessToken;
    }

    @Given("^Global Admin user log into Admin to get OktaToken$")
    public void globalAdminUserLogIntoAdminToGetOktaToken() {
        adminHomePage.openUrl(conf.getString("admin_url"));
        String oktaTokenStorageFromBrowser = adminHomePage.signInAndGetOktaTokenStorage("global.admin1@dentsuaegis.com");

        oktaTokenStorage = gson().fromJson(oktaTokenStorageFromBrowser, OktaTokenStorage.class);

        oktaAccessToken = oktaTokenStorage.getAccessToken().getAccessToken();

        System.out.println("Okta Access Token : " + oktaTokenStorage.getAccessToken().getAccessToken());

        adminHomePage.logOut();
    }


    @Given("^Market Admin user log into Admin to get OktaToken$")
    public void marketAdminUserLogIntoAdminToGetOktaToken() throws Throwable {
        adminHomePage.openUrl(conf.getString("admin_url"));

        String oktaTokenStorageFromBrowser = adminHomePage.signInAndGetOktaTokenStorage("Market.admin1@dentsuaegis.com");

        oktaTokenStorage = gson().fromJson(oktaTokenStorageFromBrowser, OktaTokenStorage.class);

        oktaAccessToken = oktaTokenStorage.getAccessToken().getAccessToken();

        System.out.println("Okta Access Token : " + oktaTokenStorage.getAccessToken().getAccessToken());

        adminHomePage.logOut();
    }

    @Given("^Client Admin user log into Admin to get OktaToken$")
    public void clientAdminUserLogIntoAdminToGetOktaToken() throws Throwable {
        adminHomePage.openUrl(conf.getString("admin_url"));

        String oktaTokenStorageFromBrowser = adminHomePage.signInAndGetOktaTokenStorage("client.admin1@dentsuaegis.com");

        oktaTokenStorage = gson().fromJson(oktaTokenStorageFromBrowser, OktaTokenStorage.class);

        oktaAccessToken = oktaTokenStorage.getAccessToken().getAccessToken();

        adminHomePage.logOut();
    }

    @Given("^Planner user log into Client to get OktaToken$")
    public void plannerUserLogIntoClientToGetOktaToken() throws Throwable {
        homePage.openUrl(conf.getString("webdriver.base.url"));

        String oktaTokenStorageFromBrowser = homePage.signIntoClientAndGetOktaTokenStorage("Tvstack.user1@dentsuaegis.com");

        oktaTokenStorage = gson().fromJson(oktaTokenStorageFromBrowser, OktaTokenStorage.class);

        oktaAccessToken = oktaTokenStorage.getAccessToken().getAccessToken();

        homePage.logOut();
    }

    @Given("^Market Admin user \"([^\"]*)\" log into Admin to get OktaToken$")
    public void marketAdminUserLogIntoAdminToGetOktaToken(String admin) throws Throwable {
        adminHomePage.openUrl(conf.getString("admin_url"));

        String oktaTokenStorageFromBrowser = adminHomePage.signInAndGetOktaTokenStorage(admin);

        oktaTokenStorage = gson().fromJson(oktaTokenStorageFromBrowser, OktaTokenStorage.class);

        oktaAccessToken = oktaTokenStorage.getAccessToken().getAccessToken();

        System.out.println("Okta Access Token : " + oktaTokenStorage.getAccessToken().getAccessToken());

        adminHomePage.logOut();

    }
}
