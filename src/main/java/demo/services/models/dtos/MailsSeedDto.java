package demo.services.models.dtos;

import com.google.gson.annotations.Expose;
import demo.constants.GlobalConstants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MailsSeedDto {

    @Expose
    private String emailType;

    @Expose
    private String email;

    @Expose
    private Long people;

    public MailsSeedDto() {
    }

    @Size(max = 5, message = "The field should be maximum 5 symbols")
    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    @Size(max = 40, message = "The field should be maximum 40 symbols")
    @Pattern(regexp = GlobalConstants.EMAIL_VALIDATE_REGEX)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull
    public Long getPeople() {
        return people;
    }

    public void setPeople(Long people) {
        this.people = people;
    }
}
