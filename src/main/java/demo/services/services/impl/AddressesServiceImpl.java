package demo.services.services.impl;

import com.google.gson.Gson;
import demo.constants.GlobalConstants;
import demo.data.models.Addresses;
import demo.data.models.Mails;
import demo.data.models.People;
import demo.data.repositories.AddressesRepository;
import demo.services.models.AddressesCreateServiceModel;
import demo.services.models.dtos.AddressesSeedDto;
import demo.services.models.dtos.MailsSeedDto;
import demo.services.services.AddressesService;
import demo.services.services.PeopleService;
import demo.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class AddressesServiceImpl implements AddressesService {

    private final AddressesRepository addressesRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final PeopleService peopleService;

    public AddressesServiceImpl(AddressesRepository addressesRepository, ModelMapper modelMapper,
                                ValidationUtil validationUtil, Gson gson, PeopleService peopleService) {
        this.addressesRepository = addressesRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.peopleService = peopleService;
    }


    @Override
    public boolean areImported() {
        return this.addressesRepository.count() > 0;
    }

    @Override
    public String readAddressesFileContent() throws IOException {
        return Files.readString(Path.of(GlobalConstants.ADDRESSES_FILE_PATH));
    }

    @Override
    public String importAddresses() throws IOException {

        StringBuilder importResult = new StringBuilder();

        AddressesSeedDto[] dtos = this.gson
                .fromJson(new FileReader(GlobalConstants.ADDRESSES_FILE_PATH), AddressesSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(addressesSeedDto -> {
                    if (this.validationUtil.isValid(addressesSeedDto)) {

                        People people = this.peopleService.getPeopleById(addressesSeedDto.getPeople()).orElse(null);

                        if (people == null) {
                            return;
                        }

                        Addresses addresses = this.modelMapper.map(addressesSeedDto, Addresses.class);

                        addresses.setPeople(people);

                        this.addressesRepository.saveAndFlush(addresses);

                        importResult.append(String.format(GlobalConstants.SUCCESSFULLY_IMPORTED,
                                addresses.getAddressInfo()));

                    } else {
                        importResult.append("Invalid address");
                    }
                    importResult.append(System.lineSeparator());
                });

        return importResult.toString().trim();
    }

    @Override
    public void createAddressForUser(AddressesCreateServiceModel addressesServiceModel) {

        Addresses address = new Addresses();

        address.setAddressType(addressesServiceModel.getAddressType());
        address.setAddressInfo(addressesServiceModel.getAddressInfo());
        address.setPeople(peopleService.getLastUser());

        addressesRepository.save(address);

    }

    @Override
    public void deleteAddressForUser(People people) {
        this.addressesRepository.deleteByPeople(people);
    }

}
