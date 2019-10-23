package blog02.demo.controller;

import blog02.demo.mapperimp.Blogmapper;
import blog02.demo.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class ArchiveShowController {

    @Autowired
    private Blogmapper blogmapper;

    @GetMapping("/archives")
    public String archives(Model model) {

        List<String> yearlist = blogmapper.selectYearList();
        HashMap<String,List<Blog>> m = new HashMap<String,List<Blog>>();
        for (String year : yearlist){
            m.put(year,blogmapper.selectBlogByYear(year));
        }
        model.addAttribute("archiveMap",m);
        model.addAttribute("blogCount", blogmapper.selectBlogByPublishedCount());
        return "archives";
    }
}
