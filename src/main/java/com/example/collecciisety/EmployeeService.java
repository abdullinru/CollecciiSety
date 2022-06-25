package com.example.collecciisety;

import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class EmployeeService {
    Employee[] employees;

//    public EmployeeService(Employee[] employees) {
//        this.employees = employees;
//    }

    public void add(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i] == null) {
                employees[i] = new Employee(firstName, lastName);
                break;
            }
        }

    }

    public void delete(String firstName, String lastName) {
        for (int i = 0; i < employees.length; i++) {
            if (Objects.equals(employees[i].getFirstName(), firstName) &&
                    Objects.equals(employees[i].getLastName(), lastName)) {
                employees[i] = null;
                break;
            }
        }
    }

    public boolean find(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (Objects.equals(employee.getFirstName(), firstName) &&
                    Objects.equals(employee.getLastName(), lastName)) {
                return true;
            }
        }
        return false;
    }
}
