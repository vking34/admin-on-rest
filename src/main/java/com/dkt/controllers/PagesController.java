package com.dkt.controllers;


import com.dkt.models.Account;
import com.dkt.models.AccountMap;
import com.dkt.models.BizwebStore;
import com.dkt.models.FbPage;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.AccountRepository;
import com.dkt.repositories.FbPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pages")
public class PagesController {

    @Autowired
    private FbPageRepository fbPageRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/")
    public Page<FbPage> getPages(@RequestParam int page)
    {
        System.out.println("GET: List FbPage page " + page);
        PageRequest pageRequest = new PageRequest(page, 10);
        return fbPageRepository.findAll(pageRequest);
    }

//    @GetMapping("/belong/")
//    public Page<FbPage> getPagesBelongToStore(@RequestParam int storeId, @RequestParam int page){
//        System.out.println("GET: List Pages belong to StoreID " + storeId);
//        PageRequest pageRequest = new PageRequest(page, 25);
//        return fbPageRepository.findPagesBelongToStore(storeId, pageRequest);
//    }


    @GetMapping("/{id}")
    public FbPage getOnePage(@PathVariable String id){
        System.out.println("GET: One FbPage by id: " + id);
        return fbPageRepository.findPageById(id);
    }

    @DeleteMapping("/{id}")
    public resp deletePage(@PathVariable String id){
        System.out.println("DELETE: Page " + id);
        fbPageRepository.deletePageById(id);
        return new resp(true);
    }

    @GetMapping("/{id}/accounts")
    public Page<AccountMap> getAccountsInPage(@PathVariable String id, @RequestParam int page){
        System.out.println("GET: Accounts in Page: " + id);

        FbPage fbPage = fbPageRepository.findPageById(id);

        List<AccountMap> accountMapList = fbPage.getAccountMaps();
//        List<String> accounts = new ArrayList<String>();

        accountMapList.forEach(e -> {
            try {
                Account account = accountRepository.findAccountById(e.getAccountId());
                e.setName(account.getName());
            }
            catch (Exception err){
                System.out.println(err.getMessage());
            }
        });

//        PageRequest pageRequest = new PageRequest(page, 25);
//
//        Page<Account> result = accountRepository.findAccountsByIdIn(accounts, pageRequest);
//
//        List<Account> accountList = result.getContent();


        return new PageImpl<>(accountMapList);
    }


    @GetMapping("/filter/")
    public Page<FbPage> filterPageByName(@RequestParam("name") String name, @RequestParam("page") int page){
        System.out.println("GET: Filter Page by alias " + name);
        PageRequest pageRequest = new PageRequest(page, 10);

        return fbPageRepository.findPagesByName(name, pageRequest);
    }




}
