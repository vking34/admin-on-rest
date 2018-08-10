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

    @GetMapping("/filter/")
    public Page<FbPage> filterPageByName(@RequestParam("name") String name, @RequestParam("page") int page){
        System.out.println("GET: Filter Page by alias " + name);
        PageRequest pageRequest = new PageRequest(page, 10);

        return fbPageRepository.findPagesByName(name, pageRequest);
    }

    @GetMapping("/{id}/accounts")
    public Page<AccountMap> getAccountsInPage(@PathVariable String id, @RequestParam int page){
        System.out.println("GET: Accounts in Page: " + id);

        FbPage fbPage = fbPageRepository.findPageById(id);
        List<AccountMap> accountMapList = fbPage.getAccountMaps();

        accountMapList.forEach(e -> {
            try {
                Account account = accountRepository.findAccountById(e.getAccountId());
                e.setName(account.getName());
            }
            catch (Exception err){
                System.out.println(err.getMessage());
            }
        });

        return new PageImpl<>(accountMapList);
    }

    @DeleteMapping("/{pageId}/accounts/{accountId}")
    public resp deleteAccountFromPage(@PathVariable("pageId") String pageId, @PathVariable("accountId") String accountId){
        System.out.println("DELETE: Account " + accountId + " from Page " + pageId);
        FbPage fbPage = fbPageRepository.findPageById(pageId);

        if(fbPage != null){
            List<AccountMap> accounts = fbPage.getAccountMaps();
            for(int i = 0; i < accounts.size(); i++){
                if(accounts.get(i).getAccountId().equals(accountId)){
                    accounts.remove(i);
                    fbPage.setAccountMaps(accounts);
                    fbPageRepository.save(fbPage);
                    return new resp(true);
                }
            }

            return new resp(false);
        }else {
            return new resp(false);
        }
    }

}
