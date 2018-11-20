package com.ld.leetcode.normaltree;

import java.util.Arrays;
import java.util.List;

/**
 * @author soapsnake
 * @date 2018/11/18
 */
public class Question690 {
    private int value;   //需要递归累积变量值时,该变量最好是全局的

    public int getImportance(List<Employee> employees, int id) {
        if (employees.size() == 0) {
            return 0;
        }
        List<Integer> subordinates = null;
        for (Employee employee : employees) {
            if (employee.id == id) {
                subordinates = employee.subordinates;
                value += employee.importance;
                break;
            }
        }
        return query(subordinates, employees);
    }

    private int query(List<Integer> employees, List<Employee> employeeList) {
        if (employees == null || employees.isEmpty()) {
            return value;
        }
        for(Integer id : employees) {
            for (Employee employee : employeeList) {
                if (employee.id == id) {
                    value += employee.importance;
                    query(employee.subordinates, employeeList);
                }
            }
        }
        return value;
    }

    public static void main(String[] args) {
        Employee employee1 = new Employee(1, 5, Arrays.asList(2,3));
        Employee employee2 = new Employee(2, 3, Arrays.asList(4));
        Employee employee3 = new Employee(3, 4, Arrays.asList());
        Employee employee4 = new Employee(4, 1, Arrays.asList());

        Question690 question690 = new Question690();
        System.out.println(question690.getImportance(Arrays.asList(employee1, employee2,
                employee3, employee4), 1));

    }
}
