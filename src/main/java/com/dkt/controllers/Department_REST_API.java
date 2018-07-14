package com.dkt.controllers;

import com.dkt.models.Department;
import com.dkt.models.Employee;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.Department.DepartmentRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("rest-api/departments")
public class Department_REST_API {
    public static final String url = "/rest-api/departments";

    final PageRequest defaultPageRequest = new PageRequest(0 ,4);
    @JsonProperty("employee_list")
    List<Employee> employee_list;

    @Autowired
    private DepartmentRepository departmentRepository;

    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    // get all departments

    @GetMapping
    public List<Department> getAllDepartments(){
        System.out.println("REST-API: GET all departments...");
        List<Department> departments = departmentRepository.findAll();

        return departments;
    }

    // get department pages
    @GetMapping(value = "/", produces = {"application/hal+json"})
    private Page<Department> getDepartmentPage(@RequestParam int page){

        System.out.println("GET default departments...");
        PageRequest pageRequest = new PageRequest(page, 4);
        Page<Department> departmentsInPage = departmentRepository.findAll(pageRequest);
        for(Department department : departmentsInPage){
            department.removeLinks();
            Link selfLink = linkTo(DepartmentsController.class).slash(department.getDepartmentID()).withSelfRel(); ;
            department.setSelfLink(url + "/" + department.getDepartmentID());
        }

        return departmentsInPage;
    }

    // create a new department
    @PostMapping
    public resp createDepartment(@RequestBody Map<String, String> department){
        System.out.println("Create a new department...");
        int id = Integer.parseInt(department.get("department_id"));
        String title = department.get("title");
        String subTitle = department.get("sub_title");
//        return departments.createDepartment(id, title, subTitle);
        if(departmentRepository.findDepartmentByDepartmentID(id) == null)
        {
            System.out.println("not existing...");
            try{
                departmentRepository.insert(new Department(id, title, subTitle));
                return new resp(true);
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                return new resp(false);
            }
        }else{
            System.out.println("existing...");
            return new resp(false);
        }

    }

    // edit an existing department
    @PutMapping("/{id}")
    public resp editDepartment(@PathVariable int id ,@RequestBody Map<String, String> newInfo){
//        int id = Integer.parseInt(department.get("id"));
        System.out.println("Edit department " + id + " ...");
        String title = newInfo.get("title");
        String subTitle = newInfo.get("sub_title");
        Department department = departmentRepository.findDepartmentByDepartmentID(id);
        if(department != null)
        {
            department.setTitle(title);
            department.setSubTitle(subTitle);
            departmentRepository.save(department);
            return new resp(true);
        }
        else
            return new resp(false);
    }


    // delete department by id
    @DeleteMapping("/{id}")
    public resp deleteDepartment(@PathVariable int id){
//        return departments.deleteDepartmentById(id);
        departmentRepository.deleteByDepartmentID(id);
        return new resp(true);
    }


    // get all employees in one department
    @GetMapping(value = "/{id}/employees",produces = {"application/hal+json"})
    public Resources<Employee> getEmployeeList(@PathVariable int id){
//        List<Employee> employeeList = list.getList();
        employee_list = departmentRepository.findDepartmentByDepartmentID(id).getEmployees();

        for(Employee e : employee_list){
            if(!e.hasLinks())
            {
                Link selfLink = linkTo(DepartmentsController.class).slash(id).slash("employees").slash(e.getEmployeeID()).withSelfRel();
                e.add(selfLink);
            }
        }
        Link departmentLink = linkTo((DepartmentsController.class)).slash(id).withRel("department");
        Resources<Employee> result = new Resources<Employee>(employee_list, departmentLink);
        return result;
    }

    // add an employee to one department
    @PostMapping(value = "{id}/employees", produces = {"application/hal+json"})
    public resp addEmployee(@PathVariable int id, @RequestBody Map<String, String> newEmployee){
        System.out.println("Add an employee to department " + id);
        Department department = departmentRepository.findDepartmentByDepartmentID(id);
        if(department != null && department.addEmployee(new Employee(Integer.parseInt(newEmployee.get("id")), newEmployee.get("name"), Integer.parseInt(newEmployee.get("age")), Integer.parseInt(newEmployee.get("salary")))))
        {
            departmentRepository.save(department);
            return new resp(true);
        }else {
            return new resp(false);
        }
    }

    // update an employee in one department
    @PutMapping(value = "/{departmentID}/employees/{employeeID}", produces = {"application/hal+json"})
    public resp updateEmployee(@PathVariable("departmentID") int departmentID, @PathVariable("employeeID") int employeeID, @RequestBody Map<String, String> info)
    {
        try{
            Department department = departmentRepository.findDepartmentByDepartmentID(departmentID);
            Employee employee = department.getEmployeeById(employeeID);
            if(employee != null)
            {
                employee.setName(info.get("name"));
                employee.setAge(Integer.parseInt(info.get("age")));
                employee.setSalary(Integer.parseInt(info.get("salary")));
                departmentRepository.save(department);
                return new resp(true);
            }else {
                return new resp(false);
            }
        }catch (Exception e)
        {
            return new resp(false);
        }
    }

//    // get an employee in one department
//    @GetMapping(value = "/{departmentID}/employees/{employeeID}", produces = {"application/hal+json"})
//    public Resource<Employee> getEmployee(@PathVariable("departmentID") int departmentID, @PathVariable("employeeID") int employeeID)
//    {
//        try{
//            Employee employee = departmentRepository.findDepartmentByDepartmentID(departmentID).getEmployeeById(employeeID);
//            if(employee != null)
//            {
//                Link selfLink = linkTo(DepartmentsController.class).slash(departmentID).slash("employees").slash(employeeID).withSelfRel();
//                Link allEmployees = linkTo(methodOn(DepartmentsController.class).getEmployeeList(departmentID)).withRel("employees_in_same_department");
//                employee.add(selfLink,allEmployees);
//                return new Resource<Employee>(employee, selfLink,allEmployees);
//            }else {
//                return null;
//            }
//        }catch (Exception e)
//        {
//            return null;
//        }
//    }

    // remove an employee in one department
    @DeleteMapping(value = "/{departmentID}/employees/{employeeID}", produces = {"application/hal+json"})
    public resp removeEmployee(@PathVariable("departmentID") int departmentID, @PathVariable("employeeID") int employeeID)
    {
        try{
            Department department = departmentRepository.findDepartmentByDepartmentID(departmentID);
            department.removeEmployee(employeeID);
            departmentRepository.save(department);
            return new resp(true);

        }catch (Exception e)
        {
            return new resp(false);
        }
    }




}
