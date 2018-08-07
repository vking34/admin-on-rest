package com.dkt.repositories;

import com.dkt.models.AppUser;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
    public AppUser findUserById(String id);
    public AppUser findUserByUsername(String name);
    public void deleteUserById(String id);
    @Query(value = "{username : {'$regex': ?0}}", fields = "{ password: 0}")
    public Page<AppUser> findUsersByUsername(String username, Pageable pageable);

    @Query(value = "{}", fields = "{ password: 0 }")
    public Page<AppUser> findAllNotCludingPassword(Pageable pageable);

    @Query(value = "{ _id : ?0 }", fields = "{ password : 0 }")
    public AppUser findUserByUsernameNotCludingPassword(ObjectId objectId);

}
