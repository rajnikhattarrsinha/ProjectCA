package net.enablers.tools.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tools.helpers.ApiHelper;
import net.enablers.tools.model.api.admin.roles.RoleDefinitionsResponseModel;
import net.enablers.tools.model.api.admin.userroles.UserRoleCreateModel;
import net.enablers.tools.model.api.admin.userroles.UserRolesResponseModel;
import net.enablers.tools.services.RoleService;
import net.enablers.tools.services.UserRoleService;
import net.enablers.tools.model.api.admin.userroles.Data;
import net.enablers.tools.utilities.RandomGenerator;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserRolesSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(UserRolesSteps.class);

    private String oktaAccessToken;
    private Response userRolesCreateResponse, userRoleDeleteResponse, roleDefinitionResponse;
    private UserRolesResponseModel userRolesResponseModel;
    private RoleDefinitionsResponseModel roleDefinitionsResponseModel;

    @When("^User requests to add role to the user$")
    public void userRequestsToDeletesUserRolesMatchingWithUniqueIdentifier() throws Throwable {
        String roleId = null;
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        roleDefinitionResponse = RoleService.getRolesDefinitions(oktaAccessToken);
        Assert.assertTrue(roleDefinitionResponse.getStatusCode() == 200);

        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
        net.enablers.tools.model.api.admin.roles.Data[] rolesData = roleDefinitionsResponseModel.getData();
        for (int count = 0; count <= rolesData.length; count++) {
            if (rolesData[count].getLevel().equalsIgnoreCase("client") && rolesData[count].getType().equalsIgnoreCase("admin")) {
                roleId = rolesData[count].getId();
                break;
            }
        }

        if (roleId != null) {
            UserRoleCreateModel userRoleCreateModel = new UserRoleCreateModel();
            userRoleCreateModel.setUserId("Subhani.Shaik@dentsuaegis.com");
            userRoleCreateModel.setRoleId(roleId);
            userRoleCreateModel.setTargetId("XX" + RandomGenerator.randomAlphanumeric(3));
            //userRoleCreateModel.setApplicationId("gdp");
            userRoleCreateModel.setApplicationId("tv-stack");
            userRolesCreateResponse = UserRoleService.createRoleToUser(oktaAccessToken, userRoleCreateModel);
            Assert.assertTrue(userRolesCreateResponse.getStatusCode() == 200);
            log.info("Role Created to User");

            userRolesResponseModel = gson().fromJson(userRolesCreateResponse.body().prettyPrint(), UserRolesResponseModel.class);
            Data userRoleIdData[] = userRolesResponseModel.getData();
            String userRoleId = userRoleIdData[0].getId();

            userRoleDeleteResponse = UserRoleService.deleteRoleFromUser(oktaAccessToken, userRoleId);
            Assert.assertTrue(userRoleDeleteResponse.getStatusCode() == 200);
            log.info("Role Deleted to User");

        } else {
            log.info("Role not found");
        }
    }

    @Then("^Role is added to the user into response$")
    public void roleIsAddedToTheUserIntoResponse() throws Throwable {
        userRolesResponseModel = gson().fromJson(userRolesCreateResponse.body().prettyPrint(), UserRolesResponseModel.class);
        Data userRoleIdData[] = userRolesResponseModel.getData();
    }


}
