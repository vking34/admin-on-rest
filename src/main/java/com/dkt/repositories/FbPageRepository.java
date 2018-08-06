package com.dkt.repositories;

import com.dkt.models.FbPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FbPageRepository extends MongoRepository<FbPage, String> {

    @Query("{ Name: { '$regex': ?0 }}")
    public Page<FbPage> findPagesByName(String name, Pageable pageable);

    public FbPage findPageByName(String name);

    public FbPage findPageById(String id);

    public void deletePageById(String id);

//    @Query(value = "{ StoreId : ?0 }")
//    public Page<FbPage> findPagesBelongToStore(int storeId, Pageable pageable);

    public Page<FbPage> findFbPagesByIdIn(List<String> pageIds, Pageable pageable);

    public List<FbPage> findFbPagesByIdIn(List<String> pageIds);
}
