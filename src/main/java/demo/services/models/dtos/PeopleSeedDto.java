package demo.services.models.dtos;

import com.google.gson.annotations.Expose;
import demo.constants.GlobalConstants;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PeopleSeedDto {

    @Expose
    private String fullName;

    @Expose
    private String pin;

    public PeopleSeedDto() {
    }

    @NotNull
    @Pattern(regexp = GlobalConstants.NAME_VALIDATE_REGEX)
    @Size(max = 90, message = "The field should be maximum 90 symbols")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Pattern(regexp = GlobalConstants.NUMBER_VALIDATE_REGEX)
    @Size(min = 10, max = 10, message = "The field should be exactly 10 digits")
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
