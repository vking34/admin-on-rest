package com.dkt.repositories;

import com.dkt.models.Account;
import com.dkt.models.AccountMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {


    @Query("{ Name : { '$regex': ?0, '$options': 'i' }}")
    public Page<Account> findAccountsByName(String Name, Pageable pageable);

    public Account findAccountById(String id);

    public void deleteAccountById(String id);

    public Page<Account> findAccountsByIdIn(List<String> accountMapList, Pageable pageable);

    public List<Account> findAccountsByIdIn(List<String> IDs);

}
