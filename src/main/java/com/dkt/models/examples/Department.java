package com.dkt.models.examples;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "department")
public class Department extends ResourceSupport {

    @Id
    private String _id;

    @Field("id")
    @JsonProperty("department_id")
    private int departmentID;

    @Field("title")
    private String title;

    @Field("subTitle")
    @JsonProperty("sub_title")
    private String subTitle;

    @Field("employees")
    private ArrayList<Employee> employees = null;

    private String selfLink;

    public Department(){
    }

    public Department(int departmentID, String title, String subTitle) {
        this.departmentID = departmentID;
        this.title = title;
        this.subTitle = subTitle;
        employees = new ArrayList<Employee>();
    }

    public Department(String _id, int departmentID, String title, String subTitle) {
        this._id = _id;
        this.departmentID = departmentID;
        this.title = title;
        this.subTitle = subTitle;
        employees = new ArrayList<Employee>();
    }

    public String getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(String selfLink) {
        this.selfLink = selfLink;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public boolean addEmployee(Employee newEmployee){
        for(Employee e: employees){
            if(e.getEmployeeID() == newEmployee.getEmployeeID()){
                return false;
            }
        }
        employees.add(newEmployee);
        return true;
    }

    public boolean removeEmployee(int id){
        for(int i = 0; i < employees.size(); i++){
            if(employees.get(i).getEmployeeID() == id){
                employees.remove(i);
                return true;
            }
        }
        return false;
    }

    public Employee getEmployeeById(int id){
        for(Employee e : employees)
        {
            if(e.getEmployeeID() == id){
                return e;
            }
        }
        return null;
    }

    public boolean removeEmployeeById(int id)
    {
        for(int i = 0; i < employees.size(); i++)
        {
            if(employees.get(i).getEmployeeID() == id){
                employees.remove(i);
                return true;
            }
        }
        return false;
    }

    public int getNumOfEmployee(){
        return employees.size();
    }
}