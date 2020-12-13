package demo.services.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserValidationServiceModel {

    private String fullName;
    private String pin;
    private String emailType;
    private String email;
    private String addressType;
    private String addressInfo;

    public UserValidationServiceModel() {
    }

    @NotNull
    @Size(min = 1, max = 90, message = "The field should`t be empty or more 90 characters")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Size(min = 10, max = 10, message = "The field should be exactly 10 digits")
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @NotNull
    @Size(min = 1, max = 5, message = "The field should`t be empty or more 5 characters")
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

    @NotNull
    @Size(min = 1, max = 5, message = "The field should`t be empty or more 5 characters")
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Size(max = 300, message = "The field should be maximum 300 symbols")
    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }
}
