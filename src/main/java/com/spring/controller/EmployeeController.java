package com.spring.controller;

import com.spring.entity.Employee;
import com.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping(path = "/list")
    public String listEmp(Model model, @RequestParam(name = "page", defaultValue = "1") int page,
                          @RequestParam(name = "limit", defaultValue = "5") int limit) {
        model.addAttribute("emps", employeeService.getAllEmployees(page, limit));
        model.addAttribute("empsList", employeeService.getAllEmp());
        return "list";
    }

    @GetMapping(path = "/addemployee")
    public String addNewEmp(Model model) {
        Employee employee = new Employee();
        model.addAttribute("emp", employee);
        return "add_employee";
    }

    @RequestMapping(path = "/addemployee", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute Employee employee) {
        employeeService.createEmp(employee);
        return "redirect:/list";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable(name = "id") int id, Model model) {
        model.addAttribute("emp", employeeService.getDetailEmp(id));
        return "add_employee";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        employeeService.deleteEmp(id);
        return "redirect:/list";
    }

    @GetMapping(path = "/detail/{id}")
    public String getDetailEmp(@PathVariable int id, Model model) {
        model.addAttribute("emp", employeeService.getDetailEmp(id));
        return "detailEmp";
    }

    //search by name in list page
    @GetMapping("/employee/search")
    public String search(@RequestParam("s") String s, Model model) {
        if(s.equals(""))
            return"redirect:/list";

        model.addAttribute("emps",employeeService.searchByName(s));
        return"list";
    }

}
