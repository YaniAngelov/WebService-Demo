package demo.data.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
@Table(name = "T_MAILS")
public class Mails extends BaseEntity {

    private String emailType;
    private String email;
    private People people;

    public Mails() {
    }

    @Column(name = "EMAIL_TYPE", nullable = false)
    @Size(max = 5, message = "The field should be maximum 5 symbols")
    public String getEmailType() {
        return emailType;
    }

    public void setEmailType(String emailType) {
        this.emailType = emailType;
    }

    @Column(name = "EMAIL", nullable = true)
    @Size(max = 40, message = "The field should be maximum 40 symbols")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

}
