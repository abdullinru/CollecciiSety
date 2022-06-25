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
        if (!findPrivate(firstName, lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
        for (int i = 0; i < employees.size(); i++) {
            if (Objects.equals(employees.get(i).getFirstName(), firstName) &&
                    Objects.equals(employees.get(i).getLastName(), lastName)) {
                Employee employee1 = new Employee(firstName, lastName);
                employees.set(i, null);
                return employee1;
            }
        }
        return null;
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (Objects.equals(employee.getFirstName(), firstName) &&
                    Objects.equals(employee.getLastName(), lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    private boolean findPrivate(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (Objects.equals(employee.getFirstName(), firstName) &&
                    Objects.equals(employee.getLastName(), lastName)) {
                return true;
            }
        }
        return false;
    }

    public List<Employee> print() {
        return employees;
    }
}
