package com.example.collecciisety;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee/")

public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @GetMapping("add")
    public Employee addEmployee(@RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "lastName") String lastName) {
            return employeeService.add(firstName, lastName);
    }
    @GetMapping("remove")
    public Employee removeEmployee(@RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "lastName") String lastName) {
            return employeeService.delete(firstName, lastName);
    }
    @GetMapping("find")
    public Employee findEmployee(@RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "lastName") String lastName) {
            return employeeService.find(firstName, lastName);
    }
    @GetMapping("print")
    public List<Employee> printEmployee() {
        return employeeService.print();
    }
}
