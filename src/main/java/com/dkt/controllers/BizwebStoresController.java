package com.dkt.controllers;


import com.dkt.models.BizwebStore;
import com.dkt.models.FbPage;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.BizwebStoreRepository;
import com.dkt.repositories.FbPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("bizweb-stores")
public class BizwebStoresController {

    @Autowired
    private BizwebStoreRepository bizwebStoreRepository;

    @Autowired
    private FbPageRepository fbPageRepository;

    public void setBizwebStoreRepository(BizwebStoreRepository bizwebStoreRepository) {
        this.bizwebStoreRepository = bizwebStoreRepository;
    }

    @GetMapping("/")
    public Page<BizwebStore> getBizwebStoresPage(@RequestParam int page)
    {
        System.out.println("GET: List BizwebStores page " + page);
        PageRequest pageRequest = new PageRequest(page, 10);

        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().toString();
        System.out.println(role);
        if(role.equals("ROLE_ADMIN")){
            return bizwebStoreRepository.findAll(pageRequest);
        }else {
            return bizwebStoreRepository.findAllNotIncludingToken(pageRequest);
        }
    }

    @GetMapping("/{alias}")
    public BizwebStore getOneBizwebStore(@PathVariable String alias){
        System.out.println("GET: One BizwebStore " + alias);
        Collection grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        System.out.println(grantedAuthorities);

        String role = grantedAuthorities.iterator().next().toString();

        if(role.equals("ROLE_ADMIN")) {
            System.out.println(role);
            return bizwebStoreRepository.findBizwebStoreByAlias(alias);
        }else{
            System.out.println(role);
            return bizwebStoreRepository.findBizwebStoreNotIncludingToken(alias);
        }
    }

    @GetMapping("/{alias}/pages")
    public Page<FbPage> getPagesFromBizwebStore(@PathVariable String alias, @RequestParam int page){
        System.out.println("GET: Pages belong to " + alias);
        BizwebStore bizwebStore = bizwebStoreRepository.findBizwebStoreByAlias(alias);
        if(bizwebStore == null){
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, 25);

        return fbPageRepository.findPagesBelongToStore(bizwebStore.getStoreId(), pageRequest);
    }

    @GetMapping("/filter/")
    public Page<BizwebStore> filterBizwebStoreByAlias(@RequestParam("alias") String alias, @RequestParam("page") int page){
        System.out.println("GET: Filter BizwebStores by alias " + alias);
        PageRequest pageRequest = new PageRequest(page, 10);

        return bizwebStoreRepository.findBizwebStoresByAlias(alias, pageRequest);
    }

    @DeleteMapping("/{alias}")
    public resp deleteBizwebStore(@PathVariable String alias){
        bizwebStoreRepository.deleteBizwebStoreByAlias(alias);
        return new resp(true);
    }



}
