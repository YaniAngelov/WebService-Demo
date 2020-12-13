package demo.services.services.impl;

import com.google.gson.Gson;
import demo.constants.GlobalConstants;
import demo.data.models.People;
import demo.data.repositories.PeopleRepository;
import demo.services.models.PeopleCreateServiceModel;
import demo.services.models.dtos.PeopleSeedDto;
import demo.services.models.view.PeopleViewModel;
import demo.services.services.PeopleService;
import demo.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PeopleServiceImpl implements PeopleService {

    private final PeopleRepository peopleRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;


    public PeopleServiceImpl(PeopleRepository peopleRepository, ModelMapper modelMapper,
                             ValidationUtil validationUtil, Gson gson) {
        this.peopleRepository = peopleRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.peopleRepository.count() > 0;
    }

    @Override
    public String readPeopleFileContent() throws IOException {
        return Files.readString(Path.of(GlobalConstants.PEOPLE_FILE_PATH));
    }

    @Override
    public String importPeople() throws IOException {

        StringBuilder importResult = new StringBuilder();

        PeopleSeedDto[] dtos = this.gson
                .fromJson(new FileReader(GlobalConstants.PEOPLE_FILE_PATH), PeopleSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(peopleSeedDto -> {
                    if (this.validationUtil.isValid(peopleSeedDto)) {

                        People people = this.modelMapper.map(peopleSeedDto, People.class);

                        this.peopleRepository.saveAndFlush(people);

                        importResult.append(String.format(GlobalConstants.SUCCESSFULLY_IMPORTED,
                                people.getFullName()));

                    } else {
                        importResult.append("Invalid people");
                    }
                    importResult.append(System.lineSeparator());
                });

        return importResult.toString().trim();
    }

    @Override
    public Optional<People> getPeopleById(Long id) {
        return this.peopleRepository.findById(id);
    }

    @Override
    public List<PeopleViewModel> findAllPeople() {
        return this.peopleRepository
                .findAll()
                .stream()
                .map(people -> {
                    PeopleViewModel peopleViewModel = this.modelMapper
                            .map(people, PeopleViewModel.class);

                    return peopleViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createNewUser(PeopleCreateServiceModel peopleServiceModel) {

        People people = new People();

        people.setFullName(peopleServiceModel.getFullName());
        people.setPin(peopleServiceModel.getPin());

        peopleRepository.save(people);

    }

    @Override
    public People getLastUser() {

        List<People> allPeople = new ArrayList<>(this.peopleRepository.findAll());

        return allPeople.get(allPeople.size() - 1);
    }

    @Override
    public void deleteUser(Long id) {
        this.peopleRepository.
                deleteById(id);
    }

}
