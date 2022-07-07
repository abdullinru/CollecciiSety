package com.example.collecciisety;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final Map<String, Employee> employees;

    public DepartmentService() {
        this.employees = new HashMap<>();
    }
//    private final EmployeeService employeeService;
//
//    public DepartmentService(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    public Employee sotrudMaxZpOtdel(int otdel) {
        Optional <Employee> optional = employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                max(new EmployeeSalaryComparator());
        return optional.orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    public Employee sotrudMinZpOtdel(int otdel) {
        return employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                min(new EmployeeSalaryComparator()).
                orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }
    public double sumSalaryOtdel(int otdel) {
        return employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                map(e -> e.getSalary()).
                reduce(Double::sum).get();
    }

    public double sredneeSalaryOtdel(int otdel) {
        return employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                mapToDouble(e -> e.getSalary()).average().getAsDouble();
    }

    public List<Employee> indexSalaryOtdel(int otdel, int procent) {
        employees.values().stream().filter(e -> e.getDepartment() == otdel).
                forEach(e -> e.setSalary(e.getSalary() * (1 + (double) procent / 100)));
        return employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                collect(Collectors.toList());
    }
    public List<Employee> bazaSotrudnikovOtdela(int otdel) {
        return employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                collect(Collectors.toList());
    }
    public Map<Integer, List<Employee>> sotrudnikiAll() {
        Map<Integer, List<Employee>> result = new HashMap<>();
        List <Employee > resultList = new ArrayList<>(employees.values());
        result = resultList.stream().
                collect(Collectors.groupingBy(Employee::getDepartment));
        return result;
    }

//    public void printOtdel(int otdel) {
//        System.out.println("Отдел № " + otdel);
//        EmployeeBook book1 = new EmployeeBook(bazaSotrudnikovOtdela(otdel));
//        for (Employee value : book1.employee) {
//            if (value == null) {
//                continue;
//            }
//            System.out.println("Сотрудник id: " + value.getId() +
//                    ", ФИО: " + value.getName() +
//                    ", Зарплата: " + value.getSalary());
//        }
//    }
//
//    public void printVseOtdely() {
//        for (int i = 1; i < 6; i++) {
//            printOtdel(i);
//
//        }
//    }
}
