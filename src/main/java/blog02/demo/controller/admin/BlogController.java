package blog02.demo.controller.admin;


import blog02.demo.mapperimp.Blogmapper;
import blog02.demo.mapperimp.Blogtagsmapper;
import blog02.demo.mapperimp.Tlagmapper;
import blog02.demo.mapperimp.Typemapper;
import blog02.demo.pojo.Blog;
import blog02.demo.pojo.searchObj;
import blog02.demo.serviceImp.BlogAddServiceImp;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:/admin/blogs";

    @Autowired
    private Blogmapper blogmapper;

    @Autowired
    private Typemapper typemapper;

    @Autowired
    private Tlagmapper tlagmapper;

    @Autowired
    private Blogtagsmapper blogtagsmapper;

    @Autowired
    private BlogAddServiceImp blogAddServiceImp;

    //公共方法
    private void setTypeAndTag(Model model){
        model.addAttribute("types", typemapper.selectTypeAll());
        model.addAttribute("tags", tlagmapper.selectTlagAll());
    }

    @PostMapping("/admin/blogs/search")
    public String search(@RequestParam(defaultValue = "1") Integer pageNum,searchObj search, Model model) {
//        System.out.println("这是搜索类:"+search);

//        PageHelper.startPage(pageNum,10,"id desc");
        List<Blog> blogs = blogmapper.searchBlog(search);

//        System.out.println("匹配条件的blog:"+blogs);
        model.addAttribute("blogs", blogs);

        PageInfo<Blog> pageinfo = new PageInfo(blogs);
        model.addAttribute("pageinfoSearch",pageinfo);
        model.addAttribute("pageinfo",null);
        return "admin/blogs :: blogList";
    }

    @GetMapping("/admin/blogs")
    public String blogs(Model model,@RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum,8,"update_time desc");
        List<Blog> blogs =  blogmapper.selectBlogAll();

        model.addAttribute("types", typemapper.selectTypeAll());
        model.addAttribute("blogs", blogs);

        PageInfo<Blog> pageinfo = new PageInfo(blogs);
        model.addAttribute("pageinfo",pageinfo);
        model.addAttribute("pageinfoSearch",null);
        return LIST;
    }

    @GetMapping("/admin/blogs/input")
    public String input(Model model) {
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    @PostMapping("/admin/blogs")
    public String post(Blog blog, @RequestParam("tlaglist") String tlaglist,RedirectAttributes attributes, HttpSession session) {

        int flag = 0;
        if (blog.getId()== null){
            flag = blogAddServiceImp.insertBlog(blog,tlaglist,session);
        }else {
//            System.out.println("blog ID更新!!");
            flag = blogAddServiceImp.updateBlog(blog,tlaglist);
        }

        if (flag == 1) {
            attributes.addFlashAttribute("message", "操作成功");
        } else {
            attributes.addFlashAttribute("message", "操作失败");
        }
        return  REDIRECT_LIST;

    }

    // 删除操作
    @GetMapping("/admin/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        int flag = blogAddServiceImp.deleteBlog(id);
        if (flag != 0){
            attributes.addFlashAttribute("message", "删除成功");
        }else {
            attributes.addFlashAttribute("message", "删除失败");
        }
        return REDIRECT_LIST;
    }

    // 更新页面显示数据
    @GetMapping("/admin/blogs/{id}/input")
    public String editInput(@PathVariable int id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogmapper.selectBlogbyID(id);
        blog.ObjToStringTlagTable();
        model.addAttribute("blog",blog);
        return INPUT;
    }

}
