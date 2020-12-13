package demo.data.repositories;

import demo.data.models.Mails;
import demo.data.models.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MailsRepository extends JpaRepository<Mails, Long> {

    void deleteByPeople(People people);

}
