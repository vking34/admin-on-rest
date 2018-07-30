package com.dkt.repositories.Department;

import com.dkt.models.examples.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

//@Repository
public class DepartmentRepositoryImpl implements DepartmentRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Department> findDepartmentsByTitle(String title) {

//        BasicQuery query = new BasicQuery("{\"'title\": {$regex : '" + title + "'} }");

        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex(title));

        List<Department> departments = mongoTemplate.find(query, Department.class);
        if(departments != null)
        {
            System.out.println("found nothing");
        }
        return departments;
    }
}
