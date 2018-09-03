package net.enablers.tools.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;
import com.typesafe.config.Config;
import net.enablers.tools.utilities.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

import static com.jayway.restassured.RestAssured.given;

public class ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(ApiHelper.class);

    static Config conf = ConfigLoader.load();

    static String roleManagerApiUrl = conf.getString("roleManagerApiUrl");
    static String notificationApiUrl = conf.getString("notificationApiUrl");

   // static String roleManagerApiUrl = "http://ec2-18-130-69-200.eu-west-2.compute.amazonaws.com:3010";

    public static Gson gson;

    protected static RequestSpecification getRoleManagerConfig(String adminToken) {
        RestAssured.baseURI = URI.create(roleManagerApiUrl).toString();
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Authorization", "Bearer " + adminToken);
    }

    protected static RequestSpecification getNotificationManagerConfig(String adminToken) {
        RestAssured.baseURI = URI.create(notificationApiUrl).toString();
        return given()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("Authorization", "Bearer " + adminToken);
    }


    //Specify all one time default Gson config
    public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gson(gsonBuilder);
        return gson;
    }

    //Custom Gson config to override Default Gson  configuration
    public static Gson gson(GsonBuilder gsonBuilder) {
        gson = gsonBuilder.create();
        return gson;
    }
}
