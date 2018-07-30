package com.dkt.repositories;

import com.dkt.models.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<AppUser, String> {
    public AppUser findUserByUsername(String username);
    public void deleteUserByUsername(String username);

    @Query("{username : {'$regex': ?0}}")
    public Page<AppUser> findUsersByUsername(String username, Pageable pageable);

}
