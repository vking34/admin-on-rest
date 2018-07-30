package com.dkt.repositories.Employee;

import com.dkt.models.examples.Employee;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class EmployeeReposityImpl implements EmployeeRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public boolean updateEmployee(int id, String name, int age, int salary) {

        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("name", name);
        update.set("age",age);
        update.set("salary", salary);

        WriteResult result = mongoTemplate.updateFirst(query, update, Employee.class);

        if(result != null)
        {
            return true;
        }
        return false;
    }
}
