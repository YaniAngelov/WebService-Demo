package demo.services.services;

import demo.data.models.Addresses;
import demo.data.models.People;
import demo.services.models.AddressesCreateServiceModel;

import java.io.IOException;

public interface AddressesService {

    boolean areImported();

    String readAddressesFileContent() throws IOException;

    String importAddresses() throws IOException;

    void createAddressForUser(AddressesCreateServiceModel addressesServiceModel);

    void deleteAddressForUser(People people);
}
