package net.enablers.tools.services;

import com.jayway.restassured.response.Response;
import net.enablers.tools.helpers.ApiHelper;

import net.enablers.tools.model.api.admin.notification.EmailRequestModel;

import java.util.List;

public class NotificationService extends ApiHelper {

    public static Response sendEmail(String oktaAccessToken) {
        return getNotificationManagerConfig(oktaAccessToken).when().get("/api/email/send");

    }

    public static Response SendNotification(String oktaAccessToken, List<EmailRequestModel> emailRequestModels) {
        Response SendNotification = null;
        SendNotification = getNotificationManagerConfig(oktaAccessToken).body(gson().toJson(emailRequestModels)).post("/api/email/send");
        return SendNotification;

    }

}
