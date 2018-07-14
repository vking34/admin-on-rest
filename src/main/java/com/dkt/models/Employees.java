package com.dkt.models;

import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

public class Employees {

    private List<Employee> list;
    private static Employees instance = null;

    public static Employees getInstance(){
        if(instance == null){
            instance = new Employees();
        }
        return instance;
    }

    //init
    public Employees()
    {
        list = new ArrayList<Employee>();
        list.add(new Employee(2234, "vuong", 21, 2300));
    }

    public List<Employee> getList(){
        return list;
    }

    // get Employee by id
    public Employee getEmployeeById(int id){
        for(Employee e : list){
            if(e.getEmployeeID() == id){
                return e;
            }
        }
        return null;
    }

    // add Employee
    public boolean addEmployee(int id, String name, int age, int salary){
        for(Employee e : list)
        {
            if(e.getEmployeeID() == id)
            {
                return false;
            }
        }
        Employee newEmployee = new Employee(id, name, age, salary);
        list.add(newEmployee);
        return true;
    }

    // update Employee
    public boolean updateEmployee(int id, String name, int age, int salary)
    {
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmployeeID() == id)
            {
                Employee newEmployee = new Employee(id, name, age, salary);
                list.set(i, newEmployee);
                return true;
            }
        }
        return false;
    }

    // delete Employee by id
    public boolean deleteEmployeeById(int id)
    {
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getEmployeeID() == id)
            {
               list.remove(i);
            }
        }
        return false;
    }


}
