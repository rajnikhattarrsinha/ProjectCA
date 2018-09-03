package net.enablers.tools.services;

import com.jayway.restassured.response.Response;
import net.enablers.tools.helpers.ApiHelper;
import net.enablers.tools.model.api.admin.roles.Data;
import net.enablers.tools.utilities.RandomGenerator;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class RoleService extends ApiHelper {

    private static Data roleDuplicateData;

    public static Response getRolesDefinitions(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles");
    }

    public static Response getRoleDefinitionForUser(String oktaAccessToken, String roleId) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles/" + roleId);
    }

    public static Response getRolesDefinitionsForDomain(String oktaAccessToken, String domainName) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles?domain=" + domainName);
    }

    public static Response getRolesDefinitionsForName(String oktaAccessToken, String name) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles?name=" + name);
    }

    public static Response getRolesDefinitionsForCode(String oktaAccessToken, String code) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles?code=" + code);
    }


    public static Response getRolesDefinitionsForTypeAndCode(String oktaAccessToken, String type, String code) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles?type=" + type + "&code=" + code);
    }

    public static Response getRolesDefinitionsForTypeAndLevel(String oktaAccessToken, String type, String level) {
        return getRoleManagerConfig(oktaAccessToken).when().get("/api/roles?type=" + type + "&level=" + level);
    }

    public static Response createNewRole(String oktaAccessToken, List<Data> rolesDataModelsList, String parent, String parentId) {
        Response createRoleResponse = null;

        for (Data roleData : rolesDataModelsList) {
            roleData.setName(roleData.getName() + RandomGenerator.randomAlphanumeric(2));
            roleData.setCode(roleData.getCode() + RandomGenerator.randomAlphanumeric(2));
            roleData.setType(roleData.getType() + RandomGenerator.randomAlphanumeric(2));
            roleData.setLevel(roleData.getLevel() + RandomGenerator.randomAlphanumeric(2));
            roleData.setDomain(roleData.getDomain() + RandomGenerator.randomAlphanumeric(2));

            if (parent.equals("invalid parent id")) {
                roleData.setParent(roleData.getParent() + RandomGenerator.randomAlphanumeric(2));
            }

            if (parent.equals("valid parent id")) {
                roleData.setParent(parentId);
            }

            roleDuplicateData = roleData;

            createRoleResponse = getRoleManagerConfig(oktaAccessToken).body(gson().toJson(roleData)).post("/api/roles");
        }
        return createRoleResponse;
    }

    public static Response createNewRoleWithBlankValues(String oktaAccessToken, List<Data> rolesDataModelsList) {
        Response createRoleResponse = null;

        for (Data roleData : rolesDataModelsList) {

            roleData.setName(roleData.getName() + RandomGenerator.randomAlphanumeric(2));

            if (StringUtils.isNotEmpty(roleData.getCode())) {
                roleData.setCode(roleData.getCode() + RandomGenerator.randomAlphanumeric(2));
            }

            if (StringUtils.isNotEmpty(roleData.getType())) {
                roleData.setType(roleData.getType() + RandomGenerator.randomAlphanumeric(2));
            }

            if (StringUtils.isNotEmpty(roleData.getLevel())) {
                roleData.setLevel(roleData.getLevel() + RandomGenerator.randomAlphanumeric(2));
            }

            if (StringUtils.isNotEmpty(roleData.getDomain())) {
                roleData.setDomain(roleData.getDomain() + RandomGenerator.randomAlphanumeric(2));
            }

            createRoleResponse = getRoleManagerConfig(oktaAccessToken).body(gson().toJson(roleData)).post("/api/roles");
        }
        return createRoleResponse;
    }

    public static Response createDuplicateRole(String oktaAccessToken) {
        return getRoleManagerConfig(oktaAccessToken).body(gson().toJson(roleDuplicateData)).post("/api/roles");
    }

    public static Response createDuplicateRoleWithSameNameDifferentCode(String oktaAccessToken) {

        roleDuplicateData.setCode("DupCode" + RandomGenerator.randomAlphanumeric(2));

        return getRoleManagerConfig(oktaAccessToken).body(gson().toJson(roleDuplicateData)).post("/api/roles");

    }

    public static Response createDuplicateRoleWithDifferentNameSameCode(String oktaAccessToken) {

        roleDuplicateData.setName("DupName" + RandomGenerator.randomAlphanumeric(2));

        return getRoleManagerConfig(oktaAccessToken).body(gson().toJson(roleDuplicateData)).post("/api/roles");
    }

    public static Response deleteRole(String oktaAccessToken, String id) {
        return getRoleManagerConfig(oktaAccessToken).delete("/api/roles/" + id);
    }
}
