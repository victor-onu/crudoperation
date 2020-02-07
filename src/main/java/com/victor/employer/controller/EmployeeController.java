package com.victor.employer.controller;

import com.victor.employer.model.Employee;
import com.victor.employer.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String employees(Model model){
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        model.addAttribute("title", employees);
        model.addAttribute("isAdd", true);
        return  "view/employees";
    }

    @PostMapping(value = "/save")
    public String save(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes, Model model){
        Employee dbEmployee = employeeService.save(employee);
        if(dbEmployee != null){
            redirectAttributes.addFlashAttribute("successmessage", "employee is saved successfully");
            return "redirect:/";
        }else{
            model.addAttribute("errormessage", "this employee was not saved, please try again");
            model.addAttribute("employee", employee);
            return  "view/employees";
        }
    }
}

