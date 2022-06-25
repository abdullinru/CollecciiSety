package com.example.collecciisety;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {
    private final List<Employee> employees;
    private final int max = 3;


    public EmployeeService() {
        this.employees = new ArrayList<>();
    }

    public Employee add(String firstName, String lastName) {
        if (findPrivate(firstName, lastName)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен! ");
        }
        if (employees.size() == max) {
            throw new EmployeeStorageIsFullException("Массив переполнен! ");
        }
        employees.add(new Employee(firstName, lastName));
        return employees.get(employees.size() - 1);

    }

    public Employee delete(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (!findPrivate(firstName, lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        } else {
            employees.remove(emp);
        }
        return emp;
    }

    public Employee find(String firstName, String lastName) {
        if (findPrivate(firstName, lastName)) {
            return new Employee(firstName, lastName);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    private boolean findPrivate(String firstName, String lastName) {
        return employees.contains(new Employee(firstName, lastName));
    }

    public List<Employee> print() {
        return employees;
    }
}
