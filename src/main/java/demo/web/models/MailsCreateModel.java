package demo.web.models;

public class MailsCreateModel {

    private String emailType;
    private String email;

    public MailsCreateModel() {
    }

    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
