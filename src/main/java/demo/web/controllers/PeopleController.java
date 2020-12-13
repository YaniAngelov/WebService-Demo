package demo.web.controllers;

import demo.data.models.People;
import demo.services.models.AddressesCreateServiceModel;
import demo.services.models.MailsCreateServiceModel;
import demo.services.models.PeopleCreateServiceModel;
import demo.services.models.UserValidationServiceModel;
import demo.services.models.view.PeopleViewModel;
import demo.services.services.AddressesService;
import demo.services.services.MailsService;
import demo.services.services.PeopleService;
import demo.web.models.AddressesCreateModel;
import demo.web.models.MailsCreateModel;
import demo.web.models.PeopleCreateModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class PeopleController {

    private final PeopleService peopleService;
    private final MailsService mailsService;
    private final AddressesService addressesService;
    private final ModelMapper modelMapper;

    public PeopleController(PeopleService peopleService, MailsService mailsService,
                            AddressesService addressesService, ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.mailsService = mailsService;
        this.addressesService = addressesService;
        this.modelMapper = modelMapper;
    }

    @GetMapping(value = "/showAll")
    public ModelAndView list(ModelAndView modelAndView) {
        modelAndView.addObject("users", this.peopleService.findAllPeople());
        modelAndView.setViewName("user/user-show-all");
        return modelAndView;
    }

    @GetMapping(value = "/find")
    public String findUser() {
        return "user/user-find.html";
    }

    @GetMapping(value = "/create")
    public String getCreateUser(Model model) {

        if (!model.containsAttribute("userValidationServiceModel")) {
            model.addAttribute("userValidationServiceModel", new UserValidationServiceModel());
        }

        return "user/user-create";

    }

    @GetMapping(value = "/createSuccess")
    public String successCreateUser() {
        return "user/user-create-successfull.html";
    }

    @PostMapping("/create")
    public String postCreateUser(@Valid @ModelAttribute("userValidationServiceModel") UserValidationServiceModel userValidationServiceModel,
                                 BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                 PeopleCreateModel peopleCreateModel, MailsCreateModel mailsCreateModel,
                                 AddressesCreateModel addressesCreateModel) {
        System.out.println();

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userValidationServiceModel", userValidationServiceModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userValidationServiceModel",
                    bindingResult);

            return "redirect:/users/create";
        }

        PeopleCreateServiceModel peopleServiceModel = modelMapper.map(peopleCreateModel, PeopleCreateServiceModel.class);
        MailsCreateServiceModel mailsServiceModel = modelMapper.map(mailsCreateModel, MailsCreateServiceModel.class);
        AddressesCreateServiceModel addressesServiceModel = modelMapper.map(addressesCreateModel, AddressesCreateServiceModel.class);

        try {
            peopleService.createNewUser(peopleServiceModel);
            mailsService.createMailForUser(mailsServiceModel);
            addressesService.createAddressForUser(addressesServiceModel);
            return "redirect:/users/createSuccess";
        } catch (Exception ex) {
            return "redirect:/users/create";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        this.peopleService.deleteUser(id);
        return "redirect:/users/showAll";
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView getUpdateUser(ModelAndView modelAndView, @PathVariable Long id) {
        modelAndView.addObject("user", this.peopleService.getPeopleById(id));
        modelAndView.setViewName("user/user-update");
        return modelAndView;
    }

}
