package com.example.collecciisety;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
//    private final Map<String, Employee> employees;
//
//    public DepartmentService() {
//        this.employees = new HashMap<>();
//    }
    private final EmployeeService employeeService;
    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee sotrudMaxZpOtdel(int otdel) {
        Map<String, Employee> empl = employeeService.getEmployees();
        Optional <Employee> optional = empl.values().stream().
                filter(e -> e.getDepartment() == otdel).
                max(new EmployeeSalaryComparator());
        return optional.orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Employee sotrudMinZpOtdel(int otdel) {
        Map<String, Employee> empl = employeeService.getEmployees();
        return empl.values().stream().
                filter(e -> e.getDepartment() == otdel).
                min(new EmployeeSalaryComparator()).
                orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }
    public double sumSalaryOtdel(int otdel) {
        Map<String, Employee> empl = employeeService.getEmployees();
        return empl.values().stream().
                filter(e -> e.getDepartment() == otdel).
                map(e -> e.getSalary()).
                reduce(Double::sum).get();
    }

    public double sredneeSalaryOtdel(int otdel) {
        Map<String, Employee> empl = employeeService.getEmployees();
        return empl.values().stream().
                filter(e -> e.getDepartment() == otdel).
                mapToDouble(e -> e.getSalary()).average().getAsDouble();
    }

    public List<Employee> indexSalaryOtdel(int otdel, int procent) {
        Map<String, Employee> empl = employeeService.getEmployees();
        empl.values().stream().filter(e -> e.getDepartment() == otdel).
                forEach(e -> e.setSalary(e.getSalary() * (1 + (double) procent / 100)));
        return empl.values().stream().
                filter(e -> e.getDepartment() == otdel).
                collect(Collectors.toList());
    }
    public List<Employee> bazaSotrudnikovOtdela(int otdel) {
        Map<String, Employee> empl = employeeService.getEmployees();
        return empl.values().stream().
                filter(e -> e.getDepartment() == otdel).
                collect(Collectors.toList());
    }
    public Map<Integer, List<Employee>> sotrudnikiAll() {
        Map<String, Employee> empl = employeeService.getEmployees();
        Map<Integer, List<Employee>> result = new HashMap<>();
        List <Employee > resultList = new ArrayList<>(empl.values());
        result = resultList.stream().
                collect(Collectors.groupingBy(Employee::getDepartment));
        return result;
    }
}
