package net.enablers.tools.model.api.admin.users;

public class Data {
    private String internalId;
    private String lastName;
    private String lastLogin;
    private String passwordChanged;
    private String status;
    private String email;
    private String created;
    private String statusChanged;
    private String lastUpdated;
    private String login;
    private String firstName;
    private UserRoles[] userRoles;

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getPasswordChanged() {
        return passwordChanged;
    }

    public void setPasswordChanged(String passwordChanged) {
        this.passwordChanged = passwordChanged;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getStatusChanged() {
        return statusChanged;
    }

    public void setStatusChanged(String statusChanged) {
        this.statusChanged = statusChanged;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserRoles[] getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(UserRoles[] userRoles) {
        this.userRoles = userRoles;
    }
}
