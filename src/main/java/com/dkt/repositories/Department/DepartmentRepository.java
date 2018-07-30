package com.dkt.repositories.Department;

import com.dkt.models.examples.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

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



