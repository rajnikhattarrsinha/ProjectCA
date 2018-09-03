package net.enablers.tools.model.api.admin.notification;

public class Data {
    private String response;

    private String[] accepted;



    private String[] rejected;

    private String messageId;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String[] getAccepted() {
        return accepted;
    }

    public void setAccepted(String[] accepted) {
        this.accepted = accepted;
    }

    public String[] getRejected() {
        return rejected;
    }

    public void setRejected(String[] rejected) {
        this.rejected = rejected;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
