package com.dkt.repositories.Employee;

import com.dkt.models.examples.Employee;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    public Employee findByEmployeeID(int id);

    public void deleteByEmployeeID(int id);
}