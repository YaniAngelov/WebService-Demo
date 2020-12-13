package demo.data.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "T_PEOPLE")
public class People extends BaseEntity {

    private String fullName;
    private String pin;
    private Set<Mails> mails;
    private Set<Addresses> addresses;

    public People() {
    }

    @Column(name = "FULL_NAME", nullable = false)
    @Size(max = 90, message = "The field should be maximum 90 symbols")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "PIN", nullable = true, unique = true)
    @Size(min = 10, max = 10, message = "The field should be exactly 10 digits")
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "people", orphanRemoval = true)
    public Set<Mails> getMails() {
        return mails;
    }

    public void setMails(Set<Mails> mails) {
        this.mails = mails;
    }

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "people", orphanRemoval = true)
    public Set<Addresses> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Addresses> addresses) {
        this.addresses = addresses;
    }
}
