package com.dkt.repositories.Department;

import com.dkt.models.Department;
import com.dkt.models.Employee;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;

//@Repository
public interface DepartmentRepository extends MongoRepository<Department, String>, DepartmentRepositoryCustom {
    public void deleteByDepartmentID(int id);
    public Department findDepartmentByDepartmentID(int id);

//    @Query("{ 'title' : /?0/ }")
//    public List<Department> findDepartmentByTitle(String title, PageRequest pageRequest);

    @Query("{title: { $regex: ?0 } }")
    public List<Department> findDepartments(String title);
}



