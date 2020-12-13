package demo.services.models;

import javax.validation.constraints.Size;

public class AddressesCreateServiceModel {

    private String addressType;
    private String addressInfo;

    public AddressesCreateServiceModel() {
    }

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
}
