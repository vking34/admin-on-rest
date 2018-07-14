package com.dkt.repositories.Employee;

import com.dkt.models.Employee;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    public Employee findByEmployeeID(int id);

    public void deleteByEmployeeID(int id);
}