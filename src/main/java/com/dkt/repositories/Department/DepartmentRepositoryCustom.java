package com.dkt.repositories.Department;

import com.dkt.models.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface DepartmentRepositoryCustom {

    public List<Department> findDepartmentsByTitle(String title);
}
