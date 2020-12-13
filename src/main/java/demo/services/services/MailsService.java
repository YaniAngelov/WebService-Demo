package demo.services.services;

import demo.data.models.Mails;
import demo.data.models.People;
import demo.services.models.MailsCreateServiceModel;

import java.io.IOException;

public interface MailsService {

    boolean areImported();

    String readMailsFileContent() throws IOException;

    String importMails() throws IOException;

    void createMailForUser(MailsCreateServiceModel mailsServiceModel);

    void deleteMailForUser(People people);

}
