package com.dkt.models.examples;

import java.util.ArrayList;
import java.util.List;

public class Departments {

    private List<Department> list;
    private static Departments instance = null;

    public static Departments getInstance()
    {
        if(instance == null){
            instance = new Departments();
        }
        return instance;
    }

    // init
    public Departments(){
        list = new ArrayList<Department>();
        list.add(new Department(34, "Sale", "communicate with clients"));
    }

    public List<Department> getList(){ return list;}

    // get department by id
    public Department getDepartmentById(int id){
        for(Department d : list){
            if(d.getDepartmentID() == id){
                return d;
            }
        }
        return null;
    }

    // create department
    public boolean createDepartment(int id, String title, String subTitle){
        for(Department d : list){
            if(d.getDepartmentID() == id){
                return false;
            }
        }
        Department newDepartment = new Department(id, title, subTitle);
        list.add(newDepartment);
        return true;
    }

    // update department
    public boolean updateDepartment(int id, String title, String subTitle){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getDepartmentID() == id)
            {
                Department newDepartment = new Department(id, title, subTitle);
                list.set(i, newDepartment);
                return true;
            }
        }
        return false;
    }

    // delete department by id
    public boolean deleteDepartmentById(int id){
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getDepartmentID() == id)
            {
                list.remove(i);
                return true;
            }
        }
        return false;
    }

}
