package com.dkt.controllers;


import com.dkt.models.Employee;
import com.dkt.models.Employees;
import com.dkt.repositories.Employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.util.List;
import java.util.Map;

//@RestController
//@RequestMapping("rest-api/employees")

//@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)

public class EmployeesController {

    Employees list = Employees.getInstance();

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeesController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // get all employees
    @GetMapping(produces = {"application/hal+json"})
    public Resources<Employee> getEmployeeList(){
//        List<Employee> employeeList = list.getList();
        List<Employee> employeeList = employeeRepository.findAll();

        for(Employee e : employeeList){
            if(!e.hasLinks())
            {
                Link selfLink = linkTo(EmployeesController.class).slash(e.getEmployeeID()).withSelfRel();
                e.add(selfLink);
            }
        }
        Link employeesLink = linkTo(EmployeesController.class).withSelfRel();
        Resources<Employee> result = new Resources<Employee>(employeeList, employeesLink);
        return result;
    }

    // add Employee
    @PostMapping
    public boolean addEmployee(@RequestBody Map<String, String> employee){
        int id = Integer.parseInt(employee.get("id"));
        String name = employee.get("name");
        int age = Integer.parseInt(employee.get("age"));
        int salary = Integer.parseInt(employee.get("salary"));
        if(employeeRepository.findByEmployeeID(id) == null)
        {
            employeeRepository.insert(new Employee(id, name, age, salary));
            return true;
        }else
            return false;
    }

    // update Employee
    @PutMapping("/{id}")
    public boolean updateEmployee(@PathVariable int id, @RequestBody Map<String, String> newInfo){
//        int id = Integer.parseInt(employee.get("id"));
        String name = newInfo.get("name");
        int age = Integer.parseInt(newInfo.get("age"));
        int salary = Integer.parseInt(newInfo.get("salary"));
        System.out.println("PUT req...");

        Employee employee = employeeRepository.findByEmployeeID(id);
        if( employee != null)
        {
            employee.setName(name);
            employee.setSalary(salary);
            employee.setAge(age);
            employeeRepository.save(employee);
            return true;
        }else {
            return false;
        }
    }

    // delete Employee
    @DeleteMapping("/{ID}")
    public boolean deleteEmployee(@PathVariable int ID){
//        return list.deleteEmployeeById(ID);
        employeeRepository.deleteByEmployeeID(ID);
        return true;
    }

    // get Employee by id
    @GetMapping(value = "/{id}", produces = {"application/hal+json"})
    public Resource<Employee> getEmployee(@PathVariable int id){
//        Employee Employee = list.getEmployeeById(id);
        Employee employee = employeeRepository.findByEmployeeID(id);

        Link selfLink = employee.getLink("self");
        if(selfLink == null){
            selfLink = linkTo(EmployeesController.class).slash(id).withSelfRel();
            employee.add(selfLink);
        }

        Link all = employee.getLink("allEmployees");
        if(all == null)
        {
            all = linkTo(EmployeesController.class).withRel("allEmployees");
            employee.add(all);
        }

        return new Resource<Employee>(employee, selfLink, all);
    }


}
