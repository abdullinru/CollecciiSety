package com.example.collecciisety;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;
    private final int max = 3;


    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public Employee add(String firstName, String lastName) {
//        if (findPrivate(firstName, lastName)) {
//            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен! ");
//        }
        if (employees.size() == max) {
            throw new EmployeeStorageIsFullException("Массив переполнен! ");
        }
        employees.put(getKey(firstName, lastName) ,new Employee(firstName, lastName));
        return employees.get(getKey(firstName, lastName));

    }

    public Employee delete(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (!findPrivate(firstName, lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        } else {
            return employees.remove(getKey(firstName, lastName));
        }
    }

    public Employee find(String firstName, String lastName) {
        if (findPrivate(firstName, lastName)) {
            return new Employee(firstName, lastName);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    private boolean findPrivate(String firstName, String lastName) {
        return employees.containsKey(getKey(firstName, lastName));
    }

    public Collection<Employee> print() {
        return Collections.unmodifiableCollection(employees.values());
    }




    public List <Employee> bazaSotrudnikovOtdela(int otdel) {
        return employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                collect(Collectors.toList());
    }

    public Optional<Employee> sotrudMinZpOtdel(int otdel) {
        return employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                max(new EmployeeSalaryComparator());
    }

    public Optional<Employee> sotrudMaxZpOtdel(int otdel) {
        return employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                min(new EmployeeSalaryComparator());
    }

    public double sumSalaryOtdel(int otdel) {
        List<Double> SalaryList =  employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                map(e -> e.getSalary()).collect(Collectors.toList());
        return SalaryList.stream().reduce(Double::sum).get();
    }

    public double sredneeSalaryOtdel(int otdel) {
        List<Double> SalaryList =  employees.values().stream().
                filter(e -> e.getDepartment() == otdel).
                map(e -> e.getSalary()).collect(Collectors.toList());
        return SalaryList.stream().reduce(Double::sum).get()/ (long) SalaryList.size();
    }

    public List<Employee> indexSalaryOtdel(int otdel, int procent) {
        return employees.values().stream().filter(e -> e.getDepartment() == otdel).
                map(e -> e.setSalary(e.getSalary() * (1 + (double) procent / 100))).
                collect(Collectors.toList());
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
