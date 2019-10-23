package blog02.demo.controller;

import blog02.demo.mapperimp.Blogmapper;
import blog02.demo.mapperimp.Blogtagsmapper;
import blog02.demo.mapperimp.Tlagmapper;

import blog02.demo.pojo.Blog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class TagShowController {


    @Autowired
    private Tlagmapper tlagmapper;

    @Autowired
    private Blogtagsmapper blogtagsmapper;

    @Autowired
    private Blogmapper blogmapper;

    @RequestMapping("/tags/{id}")
    public String tagpage(@PathVariable("id") int id,Model m,@RequestParam(defaultValue = "1") Integer pageNum){
        m.addAttribute("tags",tlagmapper.selectPublishTrueTlagAll());
        List<Blog> blogs = null;
        PageHelper.startPage(pageNum,10);
        if (id==0){
            blogs = blogmapper.selectBlogByPublished();
            m.addAttribute("blogs",blogs);
            m.addAttribute("activeTagId",null);
        }else {
            blogs = blogmapper.selectBlogPublishedByTlagID(id);
            m.addAttribute("blogs",blogs);
            m.addAttribute("activeTagId",id);
        }
        PageInfo<Blog> pageinfo = new PageInfo(blogs);
        m.addAttribute("pageinfo",pageinfo);

        return "tags";
    }

}
