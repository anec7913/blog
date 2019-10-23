package blog02.demo.controller;

import blog02.demo.mapperimp.Blogmapper;
import blog02.demo.mapperimp.Typemapper;
import blog02.demo.pojo.Blog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private Typemapper typemapper;

    @Autowired
    private Blogmapper blogmapper;

    @GetMapping("/types/{id}")
    public String types(@PathVariable("id") int id, Model m,@RequestParam(defaultValue = "1") Integer pageNum) {
        m.addAttribute("types",typemapper.selectPublishTrueTypeAll());
        List<Blog> blogs = null;
        if (id==0){
            PageHelper.startPage(pageNum,10);
            blogs = blogmapper.selectBlogByPublished();
            m.addAttribute("blogs",blogs);
            m.addAttribute("activeTypeId", null);
        }else {
            PageHelper.startPage(pageNum,10);
            blogs = blogmapper.selectBlogPublishedByTypeID(id);
            m.addAttribute("blogs",blogs);
            m.addAttribute("activeTypeId", id);
        }
        PageInfo<Blog> pageinfo = new PageInfo(blogs);
        m.addAttribute("pageinfo",pageinfo);

        return "types";
    }


}
