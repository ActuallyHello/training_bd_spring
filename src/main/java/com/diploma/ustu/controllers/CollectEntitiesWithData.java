package com.diploma.ustu.controllers;

import com.diploma.ustu.models.Entities.EntityDB;
import com.diploma.ustu.repo.EntityDBRepo;
import com.diploma.ustu.services.ConstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CollectEntitiesWithData {

    @Autowired
    ConstructorService constructorService;

    //  just to work with
    @Autowired
    EntityDBRepo entityDBRepo;

    private List<EntityDB> list = new ArrayList<>();

    @GetMapping("entities")
    public String allEntities(Model model) {
        list = entityDBRepo.findAll();
        model.addAttribute("list", list);
//        model.addAttribute("collected", null);
        return "entities";
    }

    @GetMapping("collected") // collected?first=5&first=2&first=1 how varargs works
    public String collectedEntity(Model model,
                                  @RequestParam int... first) {
        int i = 0;
        for (EntityDB entityDB : list) {
            constructorService.collectEntity(entityDB, first[i++]);
        }
        List<String> collected = constructorService.getEntities();
        model.addAttribute("collected", collected);
        constructorService.insertIdToFK();
        List<String> collectedNEW = constructorService.getEntities();
        model.addAttribute("collectedNEW", collectedNEW);
        return "entities";
    }
}
