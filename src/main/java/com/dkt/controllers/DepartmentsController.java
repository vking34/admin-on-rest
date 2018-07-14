package com.dkt.controllers;


import com.dkt.models.Department;
import com.dkt.models.Departments;
import com.dkt.models.Employee;
import com.dkt.models.Employees;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.Department.DepartmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import com.dkt.repositories.Employee.EmployeeRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@RestController
@Controller
@RequestMapping("departments")
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class DepartmentsController {
    public static final String url = "/departments";
    final PageRequest defaultPageRequest = new PageRequest(0 ,4);

    @JsonProperty("department_list")
    List<Department> department_list;

    @Autowired
    private DepartmentRepository departmentRepository;

//    @Autowired
//    private EmployeeRepository employeeRepository;

    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


//     get departments list
    @GetMapping
    public String getDepartmentsList(Model model){
//        List<Department> departmentsList = departments.getList();
        System.out.println("GET default departments...");

        Page<Department> departmentsInPage = departmentRepository.findAll(defaultPageRequest);

        for(Department department : departmentsInPage){
            department.removeLinks();
            Link selfLink = linkTo(DepartmentsController.class).slash(department.getDepartmentID()).withSelfRel(); ;
            department.setSelfLink(url + "/" + department.getDepartmentID());
        }

        model.addAttribute("page", 0);
        model.addAttribute("totalPages", departmentsInPage.getTotalPages());
        model.addAttribute("departments", departmentsInPage);
        model.addAttribute("self_link", "/rest-api/departments/addEmployee");

        return "departments";
    }

    @GetMapping(value = "/", produces = {"application/hal+json"})
    public String getDepartmentsPage(@RequestParam int page, Model model){
        System.out.println("GET departments page " + page);
        PageRequest pageRequest = new PageRequest(page, 4);
        Page<Department> departmentsInPage = departmentRepository.findAll(pageRequest);

        for(Department department : departmentsInPage){
            department.removeLinks();
            Link selfLink = linkTo(DepartmentsController.class).slash(department.getDepartmentID()).withSelfRel(); ;
            department.setSelfLink(url + "/" + department.getDepartmentID());
        }


        model.addAttribute("page", page);
        model.addAttribute("departments", departmentsInPage);
        model.addAttribute("self_link", "/rest-api/departments/addEmployee");

        return "departments";
    }

    @GetMapping(value = "/search/", produces = {"application/hal+json"})
    public String getSearchListByName(@RequestParam String title, Model model){

        System.out.println("Searching: 0"+title+"0");
//        List<Department> departments = departmentRepository.findDepartmentsByTitle(title);
        List<Department> departments = departmentRepository.findDepartments(title);

        for(Department department : departments){
            department.removeLinks();
            Link selfLink = linkTo(DepartmentsController.class).slash(department.getDepartmentID()).withSelfRel(); ;
            department.setSelfLink(url + "/" + department.getDepartmentID());
        }

        model.addAttribute("page", 0);
        model.addAttribute("departments", departments);
        model.addAttribute("funct_link", "/rest-api/departments/addEmployee");

        return "departmentSearchResults";
    }

    // get department by id
    @GetMapping(value = "/{id}", produces = {"application/hal+json"})
    public String getDepartment(@PathVariable int id, Model model){
//        Department department = departments.getDepartmentById(id);
        Department department = departmentRepository.findDepartmentByDepartmentID(id);

//        PageRequest pageRequest = new PageRequest(0, 3);
//        Page<Employee> employeePage = departmentRepository.findEmployeesByDepartmentID(id);

        for(Employee e : department.getEmployees()){
//            e.removeLinks();
//            Link eLink = linkTo(DepartmentsController.class).slash(id).slash("employees").slash(e.getEmployeeID()).withSelfRel();
//            Link eLink = linkTo(methodOn(DepartmentsController.class).getEmployee(id, e.getEmployeeID())).withSelfRel();
//            e.add(eLink);
            if(e.getSelfLink() == null)
            {
                e.setSelfLink(url + "/" + id + "employees/" + e.getEmployeeID());
            }
        }
//        Link selfLink = department.getLink("self");
//        if(selfLink == null){
//            selfLink = linkTo(DepartmentsController.class).slash(id).withSelfRel();
//            department.add(selfLink);
//        }

//        Link all = department.getLink("allDepartments");
//        if(all == null){
//            System.out.println("add all departments link");
//             all = linkTo(DepartmentsController.class).withRel("allDepartments");
//            department.add(all);
//        }
//        return new Resource<Department>(department, selfLink, all);

        model.addAttribute("department", department);
        model.addAttribute("selfLink", url + "/" + id);
        model.addAttribute("apiLink", Department_REST_API.url);
        return "department";
    }


}
