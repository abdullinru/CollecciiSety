package com.example.collecciisety;

import org.apache.commons.lang3.StringUtils;
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

    public Map<String, Employee> getEmployees() {
        return employees;
    }
    private String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    private void validateFirstLastName(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new EmployeeNotEntered("Вы не ввели имя или фамилию сотрудника");
        }
    }

    public Employee add(String firstName, String lastName, int department, double salary) {
        validateFirstLastName(firstName, lastName);
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
        validateFirstLastName(firstName, lastName);

        if (!findPrivate(firstName, lastName)) {
            throw new EmployeeNotFoundException("Нет такого сотрудника");
        } else {
            return employees.remove(getKey(firstName, lastName));
        }
    }

    public Employee find(String firstName, String lastName) {
        validateFirstLastName(firstName, lastName);

        if (findPrivate(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName))) {
            return employees.get(getKey(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName)));
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
