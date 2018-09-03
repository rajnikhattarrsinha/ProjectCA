package net.enablers.tvstack.helpers;

import com.jayway.restassured.response.Response;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
;

public class Hooks {

    private static final Logger log = LoggerFactory.getLogger(Hooks.class);
    private Object projectId;
    private Response deletePlanResponse;
    private String oktaAccessToken;

    @Before
    public void before(Scenario scenario)
    {
        genericFunction gen = new genericFunction();
        gen.fnReadConfigvalue();
        gen.fetchTestDatafromExcelandPassargument(scenario);

    }
    @After
    public void tearDown()
    {
      Serenity.getWebdriverManager().getWebdriver().quit();
    }


}
