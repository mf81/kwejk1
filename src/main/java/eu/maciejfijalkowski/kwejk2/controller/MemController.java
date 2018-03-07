package eu.maciejfijalkowski.kwejk2.controller;

import eu.maciejfijalkowski.kwejk2.model.Mem;
import eu.maciejfijalkowski.kwejk2.model.MemComments;
import eu.maciejfijalkowski.kwejk2.model.MemsCategory;
import eu.maciejfijalkowski.kwejk2.repository.MemCommentsRepository;
import eu.maciejfijalkowski.kwejk2.repository.MemRepository;
import eu.maciejfijalkowski.kwejk2.repository.MemsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import java.util.Optional;

@Controller
public class MemController {

    @Autowired
    MemRepository memRepository;

    @Autowired
    MemsCategoryRepository memsCategoryRepository;

    @Autowired
    MemCommentsRepository memCommentsRepository;

    @GetMapping("/mems/add")
    public String add(ModelMap modelMap) {
        modelMap.addAttribute("mem", new Mem());
        modelMap.addAttribute("memsCategory", memsCategoryRepository.findAll());
        return "mems/add";
    }

    @PostMapping("/mems")
    public String create(@ModelAttribute Mem mem) {
        memRepository.save(mem);
        return "redirect:/";
    }


    @GetMapping("/")
    public String indexMems(ModelMap modelMap) {
        modelMap.addAttribute("mems", memRepository.findAll());
        return "mems/index";
    }

    @GetMapping("/addcategory")
    public String categoryAdd(ModelMap modelMap) {
        modelMap.addAttribute("memsCategory", new MemsCategory());
        return "mems/addcategory";
    }


    @PostMapping("/savecategory")
    public String categorySave(@ModelAttribute MemsCategory memsCategory) {
        memsCategoryRepository.save(memsCategory);
        return "redirect:/indexcategory";
    }

    @GetMapping("/indexcategory")
    public String categoryIndex(ModelMap modelMap) {
        modelMap.addAttribute("category", memsCategoryRepository.findAll());
        return "mems/indexcategory";
    }

    @GetMapping("/addcomment/{id}")
    public String commant(ModelMap modelMap, @PathVariable Long id, Mem mem) {
        modelMap.addAttribute("mem", new Mem());
        modelMap.addAttribute("memdb", memRepository.findById(id));
        return "mems/addcom";
    }

    @GetMapping("/addcommantsave")
    public String commantSave(@ModelAttribute Mem mem) {
        memRepository.save(mem);
        return "redirect:/";
    }

    @GetMapping("mems/{id}")
    public String mem (@PathVariable Long id, ModelMap modelMap){
        Optional<Mem> memOptional = memRepository.findById(id);
        memOptional.ifPresent(mem-> {modelMap.addAttribute("mem",mem);});
        return "mems/show";
    }

    @PostMapping("/mems/addComment")
    public String memsave (@RequestParam String commentBody, @RequestParam Long id){

        Optional<Mem> optionalMem = memRepository.findById(id);
        optionalMem.ifPresent(m -> {
                MemComments memComments = new MemComments();
                memComments.setMem(m);
                memComments.setComments(commentBody);
                memCommentsRepository.save(memComments);
        });

        return "redirect:/";
    }
}