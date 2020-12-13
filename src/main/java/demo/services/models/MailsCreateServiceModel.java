package demo.services.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class MailsCreateServiceModel {

    private String emailType;
    private String email;

    public MailsCreateServiceModel() {
    }

    @Size(max = 5, message = "The field should be maximum 5 symbols")
    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    @Size(max = 40, message = "The field should be maximum 40 symbols")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
