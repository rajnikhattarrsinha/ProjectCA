package net.enablers.tools.services;

import com.jayway.restassured.response.Response;
import net.enablers.tools.helpers.ApiHelper;

public class UserService extends ApiHelper {


    public static Response getActiveDirectoryUsers(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/users");
    }

    public static Response getActiveDirectoryQueryForUsers(String oktaAccessToken, String email) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/users?query=" + email);
    }

    public static Response usersDetailsForGivenId(String oktaAccessToken, String userId) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/users/" + userId);
    }

    public static Response usersDetailsForLoggedInUserIncludingRoles(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/users/me");
    }
}
