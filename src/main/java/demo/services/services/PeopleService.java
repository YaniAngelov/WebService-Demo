package demo.services.services;

import demo.data.models.People;
import demo.services.models.PeopleCreateServiceModel;
import demo.services.models.view.PeopleViewModel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface PeopleService {

    boolean areImported();

    String readPeopleFileContent() throws IOException;

    String importPeople() throws IOException;

    Optional<People> getPeopleById(Long id);

    List<PeopleViewModel> findAllPeople();

    void createNewUser(PeopleCreateServiceModel peopleServiceModel);

    People getLastUser();

    void deleteUser(Long id);

}
