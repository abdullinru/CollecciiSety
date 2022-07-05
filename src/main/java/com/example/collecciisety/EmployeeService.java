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




    public Employee[] bazaSotrudnikovOtdela(int otdel) {
        int count = 0;
        Employee[] sotrudOtdela = new Employee[employee.length];
        for (Employee value : employee) {
            if (value == null) {
                continue;
            }
            if (otdel == value.getOtdel()) {
                sotrudOtdela[count] = value;
                count++;
            }
        }
        return sotrudOtdela;
    }

    public void sotrudMinZpOtdel(int otdel) {
        System.out.println("Отдел № " + otdel);
        EmployeeBook book1 = new EmployeeBook(bazaSotrudnikovOtdela(otdel));
        book1.findSotrudMinZp();
    }

    public void sotrudMaxZpOtdel(int otdel) {
        System.out.println("Отдел № " + otdel);
        EmployeeBook book1 = new EmployeeBook(bazaSotrudnikovOtdela(otdel));
        book1.findSotrudMaxZp();
    }

    public void sumSalaryOtdel(int otdel) {
        System.out.println("Отдел № " + otdel);
        EmployeeBook book1 = new EmployeeBook(bazaSotrudnikovOtdela(otdel));
        book1.sumSalary();
    }

    public void sredneeSalaryOtdel(int otdel) {
        System.out.println("Отдел № " + otdel);
        EmployeeBook book1 = new EmployeeBook(bazaSotrudnikovOtdela(otdel));
        book1.sredneeSalary();
    }

    public void indexSalaryOtdel(int otdel, int procent) {
        System.out.println("Отдел № " + otdel);
        EmployeeBook book1 = new EmployeeBook(bazaSotrudnikovOtdela(otdel));
        book1.indexSalary(procent);
    }

    public void printOtdel(int otdel) {
        System.out.println("Отдел № " + otdel);
        EmployeeBook book1 = new EmployeeBook(bazaSotrudnikovOtdela(otdel));
        for (Employee value : book1.employee) {
            if (value == null) {
                continue;
            }
            System.out.println("Сотрудник id: " + value.getId() +
                    ", ФИО: " + value.getName() +
                    ", Зарплата: " + value.getSalary());
        }
    }

    public void printVseOtdely() {
        for (int i = 1; i < 6; i++) {
            printOtdel(i);

        }
    }
}
