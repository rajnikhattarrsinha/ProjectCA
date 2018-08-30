


package net.enablers.tools.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import net.enablers.tools.model.api.admin.notification.EmailRequestModel;
import net.enablers.tools.model.api.admin.notification.EmailResponseModel;
import net.enablers.tools.services.NotificationService;
import org.junit.Assert;

import java.util.List;

import static net.enablers.tools.helpers.ApiHelper.gson;

public class NotificationSteps{
    private String oktaAccessToken;
    private int stauscode;
    private EmailResponseModel emailResponseModel;
    private Response sendEmailResponse;

    @When("^User request to send notification$")
    public void userRequestToSendNotification(List<EmailRequestModel> emailRequestModels) {


        System.out.println(emailRequestModels);

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();
        System.out.println(emailRequestModels);
        sendEmailResponse = NotificationService.SendNotification(oktaAccessToken, emailRequestModels);

       System.out.println(sendEmailResponse.getStatusCode());
        stauscode = sendEmailResponse.getStatusCode();
        Assert.assertTrue(sendEmailResponse.getStatusCode() == 200);
    }

    @Then("^Notification sent$")
    public void sentNotificationIsRetrivedInResponse()  {
        emailResponseModel = gson().fromJson(sendEmailResponse.body().prettyPrint(), EmailResponseModel.class);

        Assert.assertTrue(emailResponseModel.getStatus() == "success");
        Assert.assertTrue(emailResponseModel.getMessage()== "Success");

    }



}
