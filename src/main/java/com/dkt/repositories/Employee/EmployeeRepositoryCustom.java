package com.dkt.repositories.Employee;

import org.springframework.stereotype.Repository;


public interface EmployeeRepositoryCustom {
    boolean updateEmployee(int id, String name, int age, int salary);

}
