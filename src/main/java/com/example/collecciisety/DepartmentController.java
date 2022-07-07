package com.example.collecciisety;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/departments")

@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public Employee employeeMaxZpByDepartment (@RequestParam(name = "departmentId") Integer department) {
        return departmentService.sotrudMaxZpOtdel(department);
    }
    @GetMapping("/min-salary")
    public Employee employeeMinZpByDepartment (@RequestParam(name = "departmentId") Integer department) {
        return departmentService.sotrudMinZpOtdel(department);
    }
    @GetMapping("/all")
    public Map<Integer, List<Employee>> allEmployee () {
        return departmentService.sotrudnikiAll();
    }
    @GetMapping("/alll")
    public List<Employee> allEmployeeByDepartment (
            @RequestParam(name = "departmentId") Integer department) {
        return departmentService.bazaSotrudnikovOtdela(department);
    }


}
