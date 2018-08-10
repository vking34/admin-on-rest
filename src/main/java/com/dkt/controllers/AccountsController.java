package com.dkt.controllers;

import com.dkt.models.*;
import com.dkt.passingObjects.resp;
import com.dkt.repositories.AccountRepository;
import com.dkt.repositories.FbPageRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/{id}")
    public resp updateAccout(@PathVariable String id, @RequestBody Map info){
        System.out.println("UPDATE: Account " + id);
        Account account = accountRepository.findAccountById(id);
        List<Customer> customers = account.getBizwebCustomerMaps();
//        String customerMaps = info.get("bizweb_customer_maps");

//        System.out.println(info.get(0).toString());
        Customer customer = customers.get(0);
        customer.setName("Le Hien Thu");
        System.out.println(customers.get(0).getPageId());



        return new resp(false);
    }

    @DeleteMapping("/{id}")
    public resp deleteAccountById(@PathVariable String id){
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

        List<PageMap> pageMaps = new ArrayList<>();
        List<FbPage> fbPages = new ArrayList<>();
        try {
            pageMaps = account.getPageMaps();
            pageMaps.forEach(p -> {
                try {
                    FbPage fbPage = pageRepository.findPageById(p.getPageId());
                    if(fbPage == null)
                        fbPage = new FbPage(p.getPageId());
                    fbPages.add(fbPage);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            });
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return new PageImpl<>(fbPages);
    }

    @DeleteMapping("/{accountId}/pages/{pageId}")
    public resp deletePageFromAccount(@PathVariable("accountId") String accountId, @PathVariable("pageId") String pageId){
        System.out.println("DELETE: Page " + pageId + " from Page " + pageId);
        Account account = accountRepository.findAccountById(accountId);

        if(account != null){
            try{
                List<PageMap> pages = account.getPageMaps();
                for(int i = 0; i < pages.size(); i++){
                    if(pages.get(i).getPageId().equals(pageId)){
                        pages.remove(i);
                        account.setPageMaps(pages);
                        accountRepository.save(account);
                        return new resp(true);
                    }
                }
                return new resp(false);
            }
            catch (Exception e){
                return new resp(false);
            }
        }else {
            return new resp(false);
        }
    }
}
