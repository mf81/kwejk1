package eu.maciejfijalkowski.kwejk2.controller;

import eu.maciejfijalkowski.kwejk2.model.Mem;
import eu.maciejfijalkowski.kwejk2.model.MemsCategory;
import eu.maciejfijalkowski.kwejk2.repository.MemRepository;
import eu.maciejfijalkowski.kwejk2.repository.MemsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.GeneratedValue;

@Controller
public class MemController {

    @Autowired
    MemRepository memRepository;

    @Autowired
    MemsCategoryRepository memsCategoryRepository;

    @GetMapping("/mems/add")
    public String add(ModelMap modelMap){
        modelMap.addAttribute("mem", new Mem());
        return "mems/add";
    }

    @PostMapping("/mems")
    public String create(@ModelAttribute Mem mem){
        memRepository.save(mem);
        return "redirect:/";
    }


    @GetMapping("/")
    public String indexMems(ModelMap modelMap){
        modelMap.addAttribute("mems",memRepository.findAll());
        return "mems/index";
    }

    @GetMapping("/addcategory")
    public String categoryAdd(ModelMap modelMap){
        modelMap.addAttribute("memsCategory",new MemsCategory());
        return "mems/addcategory";
    }


    @PostMapping("/savecategory")
    public String categorySave(@ModelAttribute MemsCategory memsCategory){
        memsCategoryRepository.save(memsCategory);
        return "redirect:/indexcategory";
    }

    @GetMapping("/indexcategory")
    public String categoryIndex(ModelMap modelMap){
        modelMap.addAttribute("category",memsCategoryRepository.findAll());
        return "mems/indexcategory";
    }
}
