package demo.services.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PeopleCreateServiceModel {

    private String fullName;
    private String pin;

    public PeopleCreateServiceModel() {
    }

    @NotNull
    @Size(max = 90, message = "The field should be maximum 90 symbols")
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

}
