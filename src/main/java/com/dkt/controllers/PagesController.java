package com.dkt.controllers;


import com.dkt.models.BizwebStore;
import com.dkt.models.FbPage;
import com.dkt.repositories.FbPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pages")
public class PagesController {

    @Autowired
    private FbPageRepository fbPageRepository;

    @GetMapping("/")
    public Page<FbPage> getPages(@RequestParam int page)
    {
        System.out.println("GET: List FbPage page " + page);
        PageRequest pageRequest = new PageRequest(page, 10);
        return fbPageRepository.findAll(pageRequest);
    }

    @GetMapping("/belong/")
    public Page<FbPage> getPagesBelongToStore(@RequestParam int storeId, @RequestParam int page){
        System.out.println("GET: List Pages belong to StoreID " + storeId);
        PageRequest pageRequest = new PageRequest(page, 25);
        return fbPageRepository.findPagesBelongToStore(storeId, pageRequest);
    }


    @GetMapping("/{id}")
    public FbPage getOnePage(@PathVariable String id){
        System.out.println("GET: One FbPage by id: " + id);
        return fbPageRepository.findPageById(id);
    }

    @GetMapping("/filter/")
    public Page<FbPage> filterPageByName(@RequestParam("name") String name, @RequestParam("page") int page){
        System.out.println("GET: Filter Page by alias " + name);
        PageRequest pageRequest = new PageRequest(page, 10);

        return fbPageRepository.findPagesByName(name, pageRequest);
    }




}
