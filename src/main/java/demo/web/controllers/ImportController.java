package demo.web.controllers;

import demo.services.services.AddressesService;
import demo.services.services.MailsService;
import demo.services.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {

    private final PeopleService peopleService;
    private final MailsService mailsService;
    private final AddressesService addressesService;

    public ImportController(PeopleService peopleService, MailsService mailsService, AddressesService addressesService) {
        this.peopleService = peopleService;
        this.mailsService = mailsService;
        this.addressesService = addressesService;
    }

    @GetMapping("/json")
    public ModelAndView importJson() {

        boolean[] areImported = new boolean[]{
                this.peopleService.areImported(),
                this.mailsService.areImported(),
                this.addressesService.areImported()
        };

        return super.view("json/import-json", "areImported", areImported);
    }

    @GetMapping("/people")
    public ModelAndView importPeople() throws IOException {
        String fileContent = this.peopleService.readPeopleFileContent();

        return super.view("json/import-people", "people", fileContent);
    }

    @PostMapping("/people")
    public ModelAndView importPeopleConfirm() throws IOException {
        System.out.println(this.peopleService.importPeople());
        return super.redirect("/import/json");
    }

    @GetMapping("/mails")
    public ModelAndView importMails() throws IOException {
        String fileContent = this.mailsService.readMailsFileContent();

        return super.view("json/import-mails", "mails", fileContent);
    }

    @PostMapping("/mails")
    public ModelAndView importPlayersConfirm() throws IOException {
        System.out.println(this.mailsService.importMails());
        return super.redirect("/import/json");
    }

    @GetMapping("/addresses")
    public ModelAndView importAddresses() throws IOException {
        String fileContent = this.addressesService.readAddressesFileContent();

        return super.view("json/import-addresses", "addresses", fileContent);
    }

    @PostMapping("/addresses")
    public ModelAndView importAddressesConfirm() throws IOException {
        System.out.println(this.addressesService.importAddresses());
        return super.redirect("/import/json");
    }

}
