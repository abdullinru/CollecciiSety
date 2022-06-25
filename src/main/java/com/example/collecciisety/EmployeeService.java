package com.example.collecciisety;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeService {
    private Employee[] employees;

    public EmployeeService() {
        this.employees = new Employee[3];;
    }

    public Employee add(String firstName, String lastName) {
        if (findPrivate(firstName, lastName)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен! ");
        }
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(firstName, lastName);
                return employees[i];
            }
        }
        throw new EmployeeStorageIsFullException("Массив переполнен! ");

    }

    public Employee delete(String firstName, String lastName) {
        if (!findPrivate(firstName, lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        }
        for (int i = 0; i < employees.length; i++) {
            if (Objects.equals(employees[i].getFirstName(), firstName) &&
                    Objects.equals(employees[i].getLastName(), lastName)) {
                Employee employee1 = new Employee(firstName, lastName);
                employees[i] = null;
                return employee1;
            }
        }
        return null;
    }

    public Employee find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee == null) {
                throw new EmployeeNotFoundException("Сотрудник не найден");
            }
            if (Objects.equals(employee.getFirstName(), firstName) &&
                    Objects.equals(employee.getLastName(), lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }
    private boolean findPrivate(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee == null) {
                return false;
            }
            if (Objects.equals(employee.getFirstName(), firstName) &&
                    Objects.equals(employee.getLastName(), lastName)) {
                return true;
            }
        }
        return false;
    }
}
