package com.dkt.repositories.Department;

import com.dkt.models.examples.Department;

import java.util.List;

//@Repository
public interface DepartmentRepositoryCustom {

    public List<Department> findDepartmentsByTitle(String title);
}
