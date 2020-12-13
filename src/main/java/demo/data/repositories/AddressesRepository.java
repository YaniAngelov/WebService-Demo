package demo.data.repositories;

import demo.data.models.Addresses;
import demo.data.models.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressesRepository extends JpaRepository<Addresses, Long> {

    void deleteByPeople(People people);

}
