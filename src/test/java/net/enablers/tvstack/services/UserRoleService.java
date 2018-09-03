package net.enablers.tools.services;

import com.jayway.restassured.response.Response;
import net.enablers.tools.helpers.ApiHelper;
import net.enablers.tools.model.api.admin.userroles.UserRoleCreateModel;


public class UserRoleService extends ApiHelper {

    public static Response createRoleToUser(String oktaAccessToken, UserRoleCreateModel userRolesCreateModel) {
        return getRoleManagerConfig(oktaAccessToken).body(gson().toJson(userRolesCreateModel)).post("/api/user-roles");
    }

    public static Response deleteRoleFromUser(String oktaAccessToken, String id) {
        return getRoleManagerConfig(oktaAccessToken).delete("/api/user-roles/" + id);
    }
}
