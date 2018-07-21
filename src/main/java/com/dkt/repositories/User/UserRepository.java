package com.dkt.repositories.User;

import com.dkt.models.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
    public AppUser findUserByUsername(String username);

}
