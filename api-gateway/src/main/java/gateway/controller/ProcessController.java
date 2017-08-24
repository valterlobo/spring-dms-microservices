/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gateway.controller;

import gateway.dto.Activity;
import gateway.dto.Company;
import gateway.dto.Process;
import gateway.dto.MessageDto;
import gateway.dto.User;
import gateway.service.CompanyService;
import gateway.service.ProcessService;
import gateway.service.UserService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Hachiko
 */
@Controller
@RequestMapping("/processes")
public class ProcessController {

    @Autowired
    private ProcessService processService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public ModelAndView addProcess(Principal principal) {
        ModelAndView mv = new ModelAndView("add_process");
        User loggedUser = userService.findOne(principal.getName());
        mv.addObject("company", loggedUser.getCompany());
        return mv;
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ModelAndView save(Principal principal, String name, @RequestParam(name = "parent", required = false) Long parent,
            @RequestParam(name = "primitive", required = false) boolean primitive, boolean isActivity) throws Exception {
        Process process = null;
        String successMessage = "Process successfully added";
        if (parent == null && isActivity) {
            throw new Exception("Activity has to have a parent");
        }
        if (isActivity) {
            process = processService.findOne(parent);
            if (!process.isPrimitive()) {
                throw new Exception("Can't add activity to a non primitive process");
            }
            Activity activity = new Activity(name);
            process.getActivityList().add(activity);
            processService.save(process);
            successMessage = "Activity successfully added";
        } else {
            if (parent != null) {
                Process parentProcess = processService.findOne(parent);
                if (parentProcess.isPrimitive()) {
                    throw new Exception("Can't add process to a primitive process");
                }
                process = new Process(name, parentProcess, primitive);
            } else {
                process = new Process(name, null, primitive);
            }
            User loggedUser = userService.findOne(principal.getName());
            Company company = companyService.findOne(loggedUser.getCompany());
//            company.getProcesses().add(process);
//            companyService.save(company);
        }
        return new ModelAndView("add_process", "message", new MessageDto(MessageDto.MESSAGE_TYPE_SUCCESS, successMessage));
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}