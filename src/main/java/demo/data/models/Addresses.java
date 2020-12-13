package demo.data.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "T_ADDRESSES")
public class Addresses extends BaseEntity {

    private String addressType;
    private String addressInfo;
    private People people;

    public Addresses() {
    }

    @Column(name = "ADDR_TYPE", nullable = false)
    @Size(max = 5, message = "The field should be maximum 5 symbols")
    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Column(name = "ADDR_INFO", nullable = true, columnDefinition = "TEXT")
    @Size(max = 300, message = "The field should be maximum 300 symbols")
    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }
}
