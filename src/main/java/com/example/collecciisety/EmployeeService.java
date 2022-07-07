package com.example.collecciisety;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final Map<String, Employee> employees;
    private static final int MAX = 3;


    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public Employee add(String firstName, String lastName, int department, double salary) {
        String key = getKey(firstName, lastName);
        if (findPrivate(firstName, lastName)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен! ");
        }
        if (employees.size() == MAX) {
            throw new EmployeeStorageIsFullException("Массив переполнен! ");
        }
        employees.put(key ,new Employee(firstName, lastName, department, salary));
        return employees.get(key);
    }

    public Employee delete(String firstName, String lastName) {
        if (!findPrivate(firstName, lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        } else {
            return employees.remove(getKey(firstName, lastName));
        }
    }

    public Employee find(String firstName, String lastName) {
        if (findPrivate(firstName, lastName)) {
            return employees.get(getKey(firstName, lastName));
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    private boolean findPrivate(String firstName, String lastName) {
        return employees.containsKey(getKey(firstName, lastName));
    }

    public Collection<Employee> allSotrudniki() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
