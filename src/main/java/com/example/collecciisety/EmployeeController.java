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
        try {
            return employeeService.add(firstName, lastName);
        } catch (EmployeeStorageIsFullException | EmployeeAlreadyAddedException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @GetMapping("remove")
    public Employee removeEmployee(@RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "lastName") String lastName) {
        try {
            return employeeService.delete(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @GetMapping("find")
    public Employee findEmployee(@RequestParam(name = "firstName") String firstName,
                                 @RequestParam(name = "lastName") String lastName) {
        try {
            return employeeService.find(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    @GetMapping("print")
    public List<Employee> printEmployee() {
        return employeeService.print();
    }
}
