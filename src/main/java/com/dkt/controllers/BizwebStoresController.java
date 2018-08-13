package com.dkt.controllers;

import com.dkt.models.*;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.AccountRepository;
import com.dkt.repositories.BizwebStoreRepository;
import com.dkt.repositories.FbPageRepository;
import com.google.gson.Gson;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


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
    public Page<BizwebStore> getStoresPage(@RequestParam int page)
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
    public BizwebStore getOneStore(@PathVariable String id){
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

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public resp updateStore(@PathVariable String id,@RequestBody Map<String, Object> info){
        System.out.println("UPDATE: Store " + id);
        BizwebStore store = bizwebStoreRepository.findBizwebStoreById(id);

        if(store != null){
            JSONObject json = new JSONObject(info);
            String alias = json.getString("alias");

            if(bizwebStoreRepository.findBizwebStoreByAlias(alias) != null){
                return new resp(false);
            }

            if(!store.getAlias().equals(alias))
                store.setAlias(alias);

            Gson gson = new Gson();
            PackageInfo packageInfo = gson.fromJson(json.getJSONObject("packageInfo").toString(), PackageInfo.class);
            store.setPackageInfo(packageInfo);
            bizwebStoreRepository.save(store);
            return new resp(true);
        }
        else {
            return new resp(false);
        }
    }

    @DeleteMapping("/{id}")
    public resp deleteStore(@PathVariable String id){
        System.out.println("DELETE: BizwebStore " + id);
        bizwebStoreRepository.deleteBizwebStoreById(id);
        return new resp(true);
    }

    @GetMapping("/filter/")
    public Page<BizwebStore> filterStoreByAlias(@RequestParam("alias") String alias, @RequestParam("page") int page){
        System.out.println("GET: Filter BizwebStores by alias " + alias);
        PageRequest pageRequest = new PageRequest(page, 10);

        return bizwebStoreRepository.findBizwebStoresByAlias(alias, pageRequest);
    }

    @GetMapping("/{id}/pages")
    public Page<FbPage> getPagesFromStore(@PathVariable String id, @RequestParam int page){
        System.out.println("GET: Pages belong to " + id);
        BizwebStore bizwebStore = bizwebStoreRepository.findBizwebStoreById(id);
        if(bizwebStore == null){
            return null;
        }

        PageRequest pageRequest = new PageRequest(page, 25);
        return fbPageRepository.findFbPagesByIdIn(bizwebStore.getPageIds(), pageRequest);
    }

    @GetMapping("/{id}/accounts")
    public Page<StoreAccount> getAccountsFromStore(@PathVariable String id, @RequestParam int page){
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

    @DeleteMapping("/{storeId}/accounts/{accountId}")
    public resp deleteAccountFromStore(@PathVariable("storeId") String storeId, @PathVariable("accountId") String accountId){
        System.out.println("DELETE: Account " + accountId + " from store " + storeId);
        BizwebStore bizwebStore = bizwebStoreRepository.findBizwebStoreById(storeId);
        if (bizwebStore != null){
            try{
                List<String> accountIds = bizwebStore.getAccountIds();
                for(int i = 0; i < accountIds.size(); i++){
                    if(accountIds.get(i).equals(accountId)){
                        accountIds.remove(i);
                        bizwebStore.setAccountIds(accountIds);
                        bizwebStoreRepository.save(bizwebStore);
                        return new resp(true);
                    }
                }
                return new resp(false);
            }
            catch (Exception e){
                return new resp(false);
            }
        }
        else {
            return new resp(false);
        }
    }

    @DeleteMapping("/{storeId}/pages/{pageId}")
    public resp deletePageFromStore(@PathVariable("storeId") String storeId, @PathVariable("pageId") String pageId){
        System.out.println("DELETE: Page " + pageId + " from store " + storeId);
        BizwebStore bizwebStore = bizwebStoreRepository.findBizwebStoreById(storeId);
        if (bizwebStore != null){
            try{
                List<String> pageIds = bizwebStore.getPageIds();
                for(int i = 0; i < pageIds.size(); i++){
                    if(pageIds.get(i).equals(pageId)){
                        pageIds.remove(i);
                        bizwebStore.setPageIds(pageIds);
                        bizwebStoreRepository.save(bizwebStore);
                        return new resp(true);
                    }
                }
                return new resp(false);
            }
            catch (Exception e){
                return new resp(false);
            }
        }
        else {
            return new resp(false);
        }
    }
}
