package blog02.demo.controller;

import blog02.demo.mapperimp.Blogmapper;
import blog02.demo.mapperimp.Tlagmapper;
import blog02.demo.mapperimp.Typemapper;
import blog02.demo.pojo.Blog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private Tlagmapper tlagmapper;

    @Autowired
    private Typemapper  typemapper;

    @Autowired
    private Blogmapper blogmapper;

    @RequestMapping("/")
    public String index(Model m,@RequestParam(defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,3,"id desc");
        List<Blog> blogs =  blogmapper.selectBlogByPublished();
//        System.out.println("查询所有发表的blog:"+blogs);
        m.addAttribute("blogscount",blogs.size());
        m.addAttribute("page",blogs);
        m.addAttribute("types",typemapper.selectPublishTrueTypeAll());
        m.addAttribute("tags",tlagmapper.selectPublishTrueTlagAll());
        m.addAttribute("recommendBlogs",blogmapper.selectBlogPublishedByRecommend());

        PageInfo<Blog> pageinfo = new PageInfo(blogs);
        m.addAttribute("pageinfo",pageinfo);
        return "index";
    }

    @RequestMapping("/blog/{id}")
    public String showblog(@PathVariable("id") int id, Model m){
        m.addAttribute("blog",blogmapper.selectBlogbyID(id));
        return "blog";
    }

    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam String query, Model model) {
        PageHelper.startPage(pageNum,3,"id desc");
        List<Blog> bloglist = blogmapper.searchBlogPublished(query);
        model.addAttribute("blogs", bloglist);
        model.addAttribute("query", query);

        PageInfo<Blog> pageinfo = new PageInfo(bloglist);
        model.addAttribute("pageinfo",pageinfo);
        return "search";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        PageHelper.startPage(1,1,"update_time desc");
        model.addAttribute("newblogs", blogmapper.selectBlogByPublished());
        return "_fragments :: newblogList";
    }

}
