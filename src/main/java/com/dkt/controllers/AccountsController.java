package com.dkt.controllers;

import com.dkt.models.Account;
import com.dkt.models.FbPage;
import com.dkt.models.PageMap;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.AccountRepository;
import com.dkt.repositories.FbPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("accounts")
public class AccountsController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private FbPageRepository pageRepository;

    @GetMapping("/")
    public Page<Account> getAccountPage(@RequestParam int page){
        System.out.println("GET: List Account page " + page);

        PageRequest pageRequest = new PageRequest(page, 10);
        return accountRepository.findAll(pageRequest);
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable String id){
        System.out.println("GET: Account " + id);
        return accountRepository.findAccountById(id);
    }

    @DeleteMapping("/{id}")
    public resp deleteBizwebStore(@PathVariable String id){
        System.out.println("DELETE: Account " + id);
        accountRepository.deleteAccountById(id);
        return new resp(true);
    }

    @GetMapping("/filter/")
    public Page<Account> filterAccountsByName(@RequestParam("name") String name, @RequestParam("page") int page){
        System.out.println("GET: Filter accounts using name - " + name + " - page " + page);
        PageRequest pageRequest = new PageRequest(page, 10);

        return accountRepository.findAccountsByName(name, pageRequest);
    }


    @GetMapping("/{id}/pages")
    public Page<FbPage> getAccountsInPage(@PathVariable String id, @RequestParam int page) {
        System.out.println("GET: Pages of Account: " + id);

        Account account = accountRepository.findAccountById(id);

        List<PageMap> pageMaps = account.getPageMaps();
        List<FbPage> fbPages = new ArrayList<FbPage>();

        pageMaps.forEach(p -> {
            try {
                FbPage fbPage = pageRepository.findPageById(p.getPageId());
                fbPages.add(fbPage);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        return new PageImpl<>(fbPages);
    }
}
