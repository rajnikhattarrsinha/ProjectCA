package net.enablers.tools.steps.api;

import com.jayway.restassured.response.Response;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.enablers.tools.helpers.ApiHelper;
import net.enablers.tools.model.api.admin.roles.RoleResponseModel;
import net.enablers.tools.model.api.admin.roles.Data;
import net.enablers.tools.model.api.admin.roles.RoleDefinitionsResponseModel;
import net.enablers.tools.services.RoleService;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RolesSteps extends ApiHelper {

    private static final Logger log = LoggerFactory.getLogger(RolesSteps.class);

    private String oktaAccessToken;
    private Response roleDefinitionResponse, superUserRoleDefinitionResponse, createRoleResponse, deleteRoleResponse;
    private RoleDefinitionsResponseModel roleDefinitionsResponseModel;
    private RoleResponseModel roleResponseModel;
    private String parentId;
    private String id;

    @When("^User requests to Get roles definitions for Super User$")
    public void userRequestsToGetRolesDefinitionsForSuperUser() throws Throwable {
        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        String superUserId = rolesData[0].getId();

        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        superUserRoleDefinitionResponse = RoleService.getRoleDefinitionForUser(oktaAccessToken, superUserId);

        Assert.assertTrue(superUserRoleDefinitionResponse.getStatusCode() == 200);

    }

    @Then("^Super User Role definition including type and level are retrieved into response$")
    public void superUserRoleDefinitionIncludingTypeAndLevelAreRetrievedIntoResponse() throws Throwable {

        roleDefinitionsResponseModel = gson().fromJson(superUserRoleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        Assert.assertTrue(rolesData[0].getId() != null);
        Assert.assertEquals("Super User", rolesData[0].getName());
        Assert.assertEquals("superuser", rolesData[0].getCode());
        Assert.assertEquals("SuperAdmin", rolesData[0].getType());
        Assert.assertEquals("gdp", rolesData[0].getDomain());
    }

    @And("^gdp domain Roles definitions including type and level are retrieved into response$")
    public void gdpDomainRolesDefinitionsIncludingTypeAndLevelAreRetrievedIntoResponse(List<Data> rolesDataModelsList) throws Throwable {

        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);

        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Data[] actRolesData = roleDefinitionsResponseModel.getData();

        for (int count = 0; count < actRolesData.length; count++) {
            Assert.assertTrue(actRolesData[count].getId() != null);
            Assert.assertTrue(actRolesData[count].getName().equals(rolesDataModelsList.get(count).getName()));
            Assert.assertTrue(actRolesData[count].getType().equals(rolesDataModelsList.get(count).getType()));
            if ((!actRolesData[count].getName().equals("Super User"))) {
                Assert.assertTrue(actRolesData[count].getLevel().equals(rolesDataModelsList.get(count).getLevel()));
            }

            Assert.assertTrue(actRolesData[count].getCode().equals(rolesDataModelsList.get(count).getCode()));
            Assert.assertTrue(actRolesData[count].getDomain().equals(rolesDataModelsList.get(count).getDomain()));
        }

    }

    @When("^User requests to Get all roles definitions for \"([^\"]*)\" domain$")
    public void userRequestsToGetAllRolesDefinitionsForDomain(String domainName) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        roleDefinitionResponse = RoleService.getRolesDefinitionsForDomain(oktaAccessToken, domainName);

        Assert.assertTrue(roleDefinitionResponse.getStatusCode() == 200);
    }

    @When("^User requests to Get all roles definitions for \"([^\"]*)\" name$")
    public void userRequestsToGetAllRolesDefinitionsForName(String name) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        roleDefinitionResponse = RoleService.getRolesDefinitionsForName(oktaAccessToken, name);

        Assert.assertTrue(roleDefinitionResponse.getStatusCode() == 200);
    }

    @Then("^Platform Admin name Role definition including type and level are retrieved into response$")
    public void platformAdminNameRoleDefinitionIncludingTypeAndLevelAreRetrievedIntoResponse() throws Throwable {
        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        Assert.assertTrue(rolesData[0].getId() != null);
        Assert.assertEquals("Platform Admin", rolesData[0].getName());
        Assert.assertEquals("platfadmin", rolesData[0].getCode());
        Assert.assertEquals("admin", rolesData[0].getType());
        Assert.assertEquals("platform", rolesData[0].getLevel());
        Assert.assertEquals("gdp", rolesData[0].getDomain());
        Assert.assertTrue(rolesData[0].getParent() != null);
    }

    @When("^User requests to Get all roles definitions for \"([^\"]*)\" code$")
    public void userRequestsToGetAllRolesDefinitionsForCode(String code) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        roleDefinitionResponse = RoleService.getRolesDefinitionsForCode(oktaAccessToken, code);

        Assert.assertTrue(roleDefinitionResponse.getStatusCode() == 200);
    }

    @Then("^Storage and Visualisation User Admin code Role definition including type and level are retrieved into response$")
    public void storageAndVisualisationUserAdminCodeRoleDefinitionIncludingTypeAndLevelAreRetrievedIntoResponse() throws Throwable {
        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        Assert.assertTrue(rolesData[0].getId() != null);
        Assert.assertEquals("Storage and Visualisation User Admin", rolesData[0].getName());
        Assert.assertEquals("stovisadmin", rolesData[0].getCode());
        Assert.assertEquals("admin", rolesData[0].getType());
        Assert.assertEquals("storage", rolesData[0].getLevel());
        Assert.assertEquals("gdp", rolesData[0].getDomain());
        Assert.assertTrue(rolesData[0].getParent() != null);
    }

    @When("^User requests to Get all roles definitions for \"([^\"]*)\" type and \"([^\"]*)\" code$")
    public void userRequestsToGetAllRolesDefinitionsForTypeAndCode(String type, String code) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        roleDefinitionResponse = RoleService.getRolesDefinitionsForTypeAndCode(oktaAccessToken, type, code);

        Assert.assertTrue(roleDefinitionResponse.getStatusCode() == 200);
    }

    @Then("^Account Owner type and Code Role definition including type and level are retrieved into response$")
    public void accountOwnerTypeAndCodeRoleDefinitionIncludingTypeAndLevelAreRetrievedIntoResponse() throws Throwable {
        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        Assert.assertTrue(rolesData[0].getId() != null);
        Assert.assertEquals("Account Owner", rolesData[0].getName());
        Assert.assertEquals("acctowner", rolesData[0].getCode());
        Assert.assertEquals("owner", rolesData[0].getType());
        Assert.assertEquals("transformation", rolesData[0].getLevel());
        Assert.assertEquals("gdp", rolesData[0].getDomain());
        Assert.assertTrue(rolesData[0].getParent() != null);
    }

    @When("^User requests to Get all roles definitions for \"([^\"]*)\" type and \"([^\"]*)\" level$")
    public void userRequestsToGetAllRolesDefinitionsForTypeAndLevel(String type, String level) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        roleDefinitionResponse = RoleService.getRolesDefinitionsForTypeAndLevel(oktaAccessToken, type, level);

        Assert.assertTrue(roleDefinitionResponse.getStatusCode() == 200);
    }

    @Then("^Platform Account Owner type and level Role definition including type and level are retrieved into response$")
    public void platformAccountOwnerTypeAndLevelRoleDefinitionIncludingTypeAndLevelAreRetrievedIntoResponse() throws Throwable {
        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Data[] rolesData = roleDefinitionsResponseModel.getData();

        Assert.assertTrue(rolesData[0].getId() != null);
        Assert.assertEquals("Platform Account Owner", rolesData[0].getName());
        Assert.assertEquals("accntowner", rolesData[0].getCode());
        Assert.assertEquals("owner", rolesData[0].getType());
        Assert.assertEquals("account", rolesData[0].getLevel());
        Assert.assertEquals("gdp", rolesData[0].getDomain());
        Assert.assertTrue(rolesData[0].getParent() != null);
    }

    @Then("^Newly created Role definition retrieved into response$")
    public void newlyCreatedRoleDefinitionRetrievedIntoResponse() throws Throwable {
        roleResponseModel = gson().fromJson(createRoleResponse.body().prettyPrint(), RoleResponseModel.class);

        id = roleResponseModel.getData().getId();

        Assert.assertTrue(roleResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleResponseModel.getReturnCode() != null);

        Assert.assertTrue(roleResponseModel.getData().getId() != null);
        Assert.assertTrue(roleResponseModel.getData().getName().contains("Newrolename"));
        Assert.assertTrue(roleResponseModel.getData().getLevel().contains("newrolelevel"));
        Assert.assertTrue(roleResponseModel.getData().getCode().contains("NewCode"));
        Assert.assertTrue(roleResponseModel.getData().getType().contains("newroletype"));
        Assert.assertTrue(roleResponseModel.getData().getDomain().contains("snt"));
    }

    @Then("^Roles definitions returned empty for \"([^\"]*)\" domain into response$")
    public void rolesDefinitionsReturnedEmptyForDomainIntoResponse(String domain) throws Throwable {
        roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);

        Assert.assertTrue(roleDefinitionsResponseModel.getTimestamp() != null);
        Assert.assertTrue(roleDefinitionsResponseModel.getReturnCode() != null);

        Assert.assertTrue(roleDefinitionsResponseModel.getData().length == 0);
    }

    @When("^User requests to create new role with \"([^\"]*)\":$")
    public void userRequestsToCreateNewRoleWith(String roleType, List<Data> rolesDataModelsList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        if (roleType.contains("invalid parent")) {
            createRoleResponse = RoleService.createNewRole(oktaAccessToken, rolesDataModelsList, roleType, parentId);
            Assert.assertTrue(createRoleResponse.getStatusCode() == 400);
        } else if (roleType.contains("no parent")) {
            createRoleResponse = RoleService.createNewRole(oktaAccessToken, rolesDataModelsList, roleType, parentId);
            Assert.assertTrue(createRoleResponse.getStatusCode() == 200);
        } else {
            roleDefinitionsResponseModel = gson().fromJson(roleDefinitionResponse.body().prettyPrint(), RoleDefinitionsResponseModel.class);
            Data[] actRolesData = roleDefinitionsResponseModel.getData();
            for (int count = 0; count < actRolesData.length; count++) {
                if (actRolesData[count].getCode().equals("acctowner")) {
                    parentId = actRolesData[count].getParent();
                    break;
                }
            }
            createRoleResponse = RoleService.createNewRole(oktaAccessToken, rolesDataModelsList, roleType, parentId);
            Assert.assertTrue(createRoleResponse.getStatusCode() == 200);
        }

    }

    @Then("^error message \"([^\"]*)\" throws into response$")
    public void errorMessageThrowsIntoResponse(String errorMessage) throws Throwable {
        Assert.assertTrue(createRoleResponse.getBody().prettyPrint().toString().contains(errorMessage));
    }

    @When("^User requests to create new role with blank values to fields$")
    public void userRequestsToCreateNewRoleWithBlankValuesToFields(List<Data> rolesDataModelsList) throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        createRoleResponse = RoleService.createNewRoleWithBlankValues(oktaAccessToken, rolesDataModelsList);

        Assert.assertTrue(createRoleResponse.getStatusCode() == 400);
    }


    @When("^User requests to create duplicate role$")
    public void userRequestsToCreateDuplicateRole() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        createRoleResponse = RoleService.createDuplicateRole(oktaAccessToken);

        Assert.assertTrue(createRoleResponse.getStatusCode() == 400);
    }

    @When("^User requests to create duplicate role with same role name and different code$")
    public void userRequestsToCreateDuplicateRoleWithSameRoleNameAndDifferentCode() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        createRoleResponse = RoleService.createDuplicateRoleWithSameNameDifferentCode(oktaAccessToken);

        Assert.assertTrue(createRoleResponse.getStatusCode() == 400);
    }

    @When("^User requests to create duplicate role with different role name and same code$")
    public void userRequestsToCreateDuplicateRoleWithDifferentRoleNameAndSameCode() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        createRoleResponse = RoleService.createDuplicateRoleWithDifferentNameSameCode(oktaAccessToken);

        Assert.assertTrue(createRoleResponse.getStatusCode() == 400);
    }

    @When("^User requests to delete a role$")
    public void userRequestsToDeleteARole() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        deleteRoleResponse = RoleService.deleteRole(oktaAccessToken, id);

        Assert.assertTrue(deleteRoleResponse.getStatusCode() == 200);
    }


    @Then("^role is deleted from the system$")
    public void roleIsDeletedFromTheSystem() throws Throwable {
        roleResponseModel = gson().fromJson(deleteRoleResponse.body().prettyPrint(), RoleResponseModel.class);

        String deleteRoleid = roleResponseModel.getData().getId();
        Assert.assertTrue(deleteRoleid.equals(id));
    }

    @When("^User requests to search for deleted role$")
    public void userRequestsToSearchForDeletedRole() throws Throwable {
        oktaAccessToken = AccessTokenSteps.getOktaAccessToken();

        createRoleResponse = RoleService.getRoleDefinitionForUser(oktaAccessToken, id);

        Assert.assertTrue(createRoleResponse.getStatusCode() == 400);
    }

}
