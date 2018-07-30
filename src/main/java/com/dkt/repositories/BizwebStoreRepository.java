package com.dkt.repositories;

import com.dkt.models.BizwebStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BizwebStoreRepository extends MongoRepository<BizwebStore, String> {

    public BizwebStore findBizwebStoreByAlias(String alias);

    public void deleteBizwebStoreByAlias(String alias);

    @Query(value = "{}", fields = "{ ApiAccessToken : 0 }")
    public Page<BizwebStore> findAllNotIncludingToken(Pageable pageable);

    @Query("{ Alias: { '$regex': ?0}}")
    public Page<BizwebStore> findBizwebStoresByAlias(String alias, Pageable pageRequest);

    @Query(value = "{ Alias : ?0 }", fields ="{ ApiAccessToken: 0 }")
    public BizwebStore findBizwebStoreNotIncludingToken(String alias);
}
