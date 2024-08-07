package org.example;

import org.example.entity.Employee;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        LinkedList<Employee> employees = new LinkedList<>();
        employees.add(new Employee(1, "John", "Doe"));
        employees.add(new Employee(2, "Jane", "Doe"));
        employees.add(new Employee(1, "John", "Doe")); // Duplicate
        employees.add(new Employee(3, "Jim", "Beam"));
        employees.add(new Employee(2, "Jane", "Doe")); // Duplicate

        List<Employee> duplicates = findDuplicates(employees);
        System.out.println("Duplicates: " + duplicates);

        Map<Integer, Employee> uniques = findUniques(employees);
        System.out.println("Uniques: " + uniques.values());

        List<Employee> uniqueList = removeDuplicates(employees);
        System.out.println("Unique List after removing duplicates: " + uniqueList);
    }

    public static List<Employee> findDuplicates(List<Employee> list) {
        List<Employee> duplicates = new ArrayList<>();
        Set<Employee> seen = new HashSet<>();
        for (Employee employee : list) {
            if (employee != null && !seen.add(employee)) {
                duplicates.add(employee);
            }
        }
        return duplicates;
    }

    public static Map<Integer, Employee> findUniques(List<Employee> list) {
        Map<Integer, Employee> uniqueMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        for (Employee employee : list) {
            if (employee != null) {
                countMap.put(employee.getId(), countMap.getOrDefault(employee.getId(), 0) + 1);
            }
        }

        for (Employee employee : list) {
            if (employee != null && countMap.get(employee.getId()) == 1) {
                uniqueMap.put(employee.getId(), employee);
            }
        }

        return uniqueMap;
    }

    public static List<Employee> removeDuplicates(List<Employee> list) {
        Map<Integer, Employee> uniqueMap = findUniques(list);
        return new ArrayList<>(uniqueMap.values());
    }
}
