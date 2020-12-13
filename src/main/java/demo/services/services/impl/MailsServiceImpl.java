package demo.services.services.impl;

import com.google.gson.Gson;
import demo.constants.GlobalConstants;
import demo.data.models.Mails;
import demo.data.models.People;
import demo.data.repositories.MailsRepository;
import demo.services.models.MailsCreateServiceModel;
import demo.services.models.dtos.MailsSeedDto;
import demo.services.services.MailsService;
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
public class MailsServiceImpl implements MailsService {

    private final MailsRepository mailsRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final PeopleService peopleService;

    public MailsServiceImpl(MailsRepository mailsRepository, ModelMapper modelMapper,
                            ValidationUtil validationUtil, Gson gson, PeopleService peopleService) {
        this.mailsRepository = mailsRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.peopleService = peopleService;
    }

    @Override
    public boolean areImported() {
        return this.mailsRepository.count() > 0;
    }

    @Override
    public String readMailsFileContent() throws IOException {
        return Files.readString(Path.of(GlobalConstants.MAILS_FILE_PATH));
    }

    @Override
    public String importMails() throws IOException {

        StringBuilder importResult = new StringBuilder();

        MailsSeedDto[] dtos = this.gson
                .fromJson(new FileReader(GlobalConstants.MAILS_FILE_PATH), MailsSeedDto[].class);

        Arrays.stream(dtos)
                .forEach(mailsSeedDto -> {
                    if (this.validationUtil.isValid(mailsSeedDto)) {

                        People people = this.peopleService.getPeopleById(mailsSeedDto.getPeople()).orElse(null);

                        if (people == null) {
                            return;
                        }

                        Mails mails = this.modelMapper.map(mailsSeedDto, Mails.class);

                        mails.setPeople(people);

                        this.mailsRepository.saveAndFlush(mails);

                        importResult.append(String.format(GlobalConstants.SUCCESSFULLY_IMPORTED,
                                mails.getEmail()));

                    } else {
                        importResult.append("Invalid mail");
                    }
                    importResult.append(System.lineSeparator());
                });

        return importResult.toString().trim();
    }

    @Override
    public void createMailForUser(MailsCreateServiceModel mailsServiceModel) {

        Mails mail = new Mails();

        mail.setEmailType(mailsServiceModel.getEmailType());
        mail.setEmail(mailsServiceModel.getEmail());
        mail.setPeople(peopleService.getLastUser());

        mailsRepository.save(mail);
    }

    @Override
    public void deleteMailForUser(People people) {
        this.mailsRepository.deleteByPeople(people);
    }


}
