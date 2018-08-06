package com.dkt.controllers;


import com.dkt.models.Account;
import com.dkt.models.BizwebStore;
import com.dkt.models.FbPage;
import com.dkt.models.StoreAccount;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.AccountRepository;
import com.dkt.repositories.BizwebStoreRepository;
import com.dkt.repositories.FbPageRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("bizweb-stores")
public class BizwebStoresController {

    @Autowired
    private BizwebStoreRepository bizwebStoreRepository;

    @Autowired
    private FbPageRepository fbPageRepository;

    @Autowired
    private AccountRepository accountRepository;

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



    @GetMapping("/{id}")
    public BizwebStore getOneBizwebStore(@PathVariable String id){
        System.out.println("GET: One BizwebStore " + id);
        Collection grantedAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        System.out.println(grantedAuthorities);

        String role = grantedAuthorities.iterator().next().toString();

        if(role.equals("ROLE_ADMIN")) {
            System.out.println(role);
            return bizwebStoreRepository.findBizwebStoreById(id);
        }else{
            System.out.println(role);
            ObjectId obj = new ObjectId(id);
            return bizwebStoreRepository.findBizwebStoreNotIncludingToken(obj);
        }
    }

    @GetMapping("/{id}/pages")
    public Page<FbPage> getPagesFromBizwebStore(@PathVariable String id, @RequestParam int page){
        System.out.println("GET: Pages belong to " + id);
        BizwebStore bizwebStore = bizwebStoreRepository.findBizwebStoreById(id);
        if(bizwebStore == null){
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, 25);
        return fbPageRepository.findFbPagesByIdIn(bizwebStore.getPageIds(), pageRequest);
    }

    @GetMapping("/{id}/accounts")
    public Page<StoreAccount> getAccountsFromBizwebStore(@PathVariable String id, @RequestParam int page){
        System.out.println("GET: Accounts from Bizweb Store " + id);
        try{
            BizwebStore store = bizwebStoreRepository.findBizwebStoreById(id);

            List<Account> accounts = accountRepository.findAccountsByIdIn(store.getAccountIds());
            List<FbPage> pages = fbPageRepository.findFbPagesByIdIn(store.getPageIds());
            List<StoreAccount> storeAccounts = new ArrayList<StoreAccount>();

            accounts.forEach(account -> {
                String accountID = account.getId();
                StoreAccount storeAccount = new StoreAccount(accountID, account.getName());
                storeAccounts.add(storeAccount);
                pages.forEach(p -> {
                    p.getAccountMaps().forEach(acc -> {
                        if(acc.getAccountId().equals(accountID)){
                            storeAccount.getPageName().add(p.getName());
                        }
                    });
                });
            });

            return new PageImpl<>(storeAccounts);

        }
        catch (Exception e){
            return null;
        }
    }

    @GetMapping("/filter/")
    public Page<BizwebStore> filterBizwebStoreByAlias(@RequestParam("alias") String alias, @RequestParam("page") int page){
        System.out.println("GET: Filter BizwebStores by alias " + alias);
        PageRequest pageRequest = new PageRequest(page, 10);

        return bizwebStoreRepository.findBizwebStoresByAlias(alias, pageRequest);
    }

    @DeleteMapping("/{id}")
    public resp deleteBizwebStore(@PathVariable String id){
        System.out.println("DELETE: BizwebStore " + id);
        bizwebStoreRepository.deleteBizwebStoreById(id);
        return new resp(true);
    }



}
