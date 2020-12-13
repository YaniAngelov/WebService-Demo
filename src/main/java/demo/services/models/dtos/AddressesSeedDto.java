package demo.services.models.dtos;

import com.google.gson.annotations.Expose;
import demo.data.models.People;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddressesSeedDto {

    @Expose
    private String addressType;
    @Expose
    private String addressInfo;
    @Expose
    private Long people;

    public AddressesSeedDto() {
    }

    @NotNull
    @Size(max = 5, message = "The field should be maximum 5 symbols")
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

    @NotNull
    public Long getPeople() {
        return people;
    }

    public void setPeople(Long people) {
        this.people = people;
    }
}
