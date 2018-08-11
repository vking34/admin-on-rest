package com.dkt.repositories;

import com.dkt.models.BizwebStore;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BizwebStoreRepository extends MongoRepository<BizwebStore, String> {

    public BizwebStore findBizwebStoreById(String id);

    public BizwebStore findBizwebStoreByAlias(String alias);

    public void deleteBizwebStoreById(String id);

    @Query(value = "{}", fields = "{ ApiAccessToken : 0 }")
    public Page<BizwebStore> findAllNotIncludingToken(Pageable pageable);

    @Query("{ Alias: { '$regex': ?0, '$options' : 'i' }}")
    public Page<BizwebStore> findBizwebStoresByAlias(String alias, Pageable pageRequest);

    @Query(value = "{ '_id' : ?0 }", fields ="{ ApiAccessToken: 0 }")
    public BizwebStore findBizwebStoreNotIncludingToken(ObjectId obj);

}
