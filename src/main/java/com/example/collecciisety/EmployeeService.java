package com.example.collecciisety;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;
    private final int max = 3;


    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee add(String firstName, String lastName) {
//        if (findPrivate(firstName, lastName)) {
//            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен! ");
//        }
        if (employees.size() == max) {
            throw new EmployeeStorageIsFullException("Массив переполнен! ");
        }
        employees.put(firstName + " " + lastName ,new Employee(firstName, lastName));
        return employees.get(firstName + " " + lastName);

    }

    public Employee delete(String firstName, String lastName) {
        Employee emp = new Employee(firstName, lastName);
        if (!findPrivate(firstName, lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        } else {
            return employees.remove(firstName + " " + lastName);
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
        return employees.containsKey(firstName + " " + lastName);
    }

    public Collection<Employee> print() {

        return Collections.unmodifiableCollection(employees.values());
    }
}
