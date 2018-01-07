package gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    //    @Autowired
    //    private CompanyService companyService;
    //    @Autowired
    //    private UserService userService;

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addCompany() {
        return "add_company";
    }

    //    @RequestMapping(path = "/add", method = RequestMethod.POST)
    //    public ModelAndView save(String name, String pib, String identificationNumber, String headquarters) throws
    // Exception {
    //        companyService.save(name, pib, identificationNumber, headquarters);
    //        return new ModelAndView("add_company", "message", new MessageDto(MessageDto.MESSAGE_TYPE_SUCCESS,
    // "Company successfully added"));
    //    }
    //
    //    @RequestMapping(path = "/search", method = RequestMethod.GET)
    //    public ModelAndView findAll() {
    //        ModelAndView mv = new ModelAndView("search_companies");
    //        mv.addObject("companies", companyService.findAll());
    //        return mv;
    //    }
    //
    //    @RequestMapping(path = "/company/{id}", method = RequestMethod.GET)
    //    public ModelAndView showCompany(@PathVariable("id") long id) {
    //        Company company = companyService.findOne(id);
    //        List<User> usersOfCompany = userService.findByCompanyId(id);
    //        ModelAndView mv = new ModelAndView("company");
    //        mv.addObject("company", company);
    //        mv.addObject("users", usersOfCompany);
    //        return mv;
    //    }
    //
    //    @RequestMapping(path = "/company/{id}", method = RequestMethod.POST)
    //    public String editCompany(@PathVariable("id") long id, String name, String pib, String
    // identificationNumber, String headquarters) {
    //        companyService.edit(id, name, pib, identificationNumber, headquarters);
    ////        List<User> usersOfCompany = userService.findByCompanyId(id);
    ////        ModelAndView mv = new ModelAndView("company");
    ////        mv.addObject("company", company);
    ////        mv.addObject("users", usersOfCompany);
    //        return "redirect:/companies/company/" + id;
    //    }

}
