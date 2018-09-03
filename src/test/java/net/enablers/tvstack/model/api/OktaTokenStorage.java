package net.enablers.tools.model.api;


public class OktaTokenStorage {
    private IdToken idToken;
    private AccessToken accessToken;

    public IdToken getIdToken() {
        return idToken;
    }

    public void setIdToken(IdToken idToken) {
        this.idToken = idToken;
    }

    public AccessToken getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
