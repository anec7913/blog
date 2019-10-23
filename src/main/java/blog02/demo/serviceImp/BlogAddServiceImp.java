package blog02.demo.serviceImp;

import blog02.demo.mapperimp.Blogmapper;
import blog02.demo.mapperimp.Blogtagsmapper;
import blog02.demo.pojo.Blog;
import blog02.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class BlogAddServiceImp {

    @Autowired
    Blogmapper blogmapper;

    @Autowired
    Blogtagsmapper blogtagsmapper;

    @Transactional
    public int insertBlog(Blog blog, String tlaglist , HttpSession session){
        User u = (User)session.getAttribute("user");
        Date d = new Date();
        blog.setCreateTime(d);
        blog.setUpdateTime(d);
        blog.setUserId((long)u.getId());
        blog.NullToFalse();
        int flag = blogmapper.insertBlog(blog);
//        System.out.println("这是新增页面的Blog:"+blog);

        String[] alist =  tlaglist.split(",");
//        System.out.println("需要添加的标签List" + alist);
        for ( String tlag : alist){
            flag += blogtagsmapper.insertBlogTlag(blog.getId(),Integer.parseInt(tlag));

        }
        if (flag >= 1){
            return 1;
        }
        return 0;
    }

    @Transactional
    public int deleteBlog(long id){
        int flag = blogmapper.deleteBlogById(id);
        blogtagsmapper.deleteByBlogId(id);
        return flag;
    }

    @Transactional
    public int updateBlog(Blog blog, String tlaglist){
        Date dd = new Date();
        blog.setUpdateTime(dd);
        blog.NullToFalse();
//        System.out.println("这是更新页面:before Blog" + blog);
        int flag = blogmapper.updateBlog(blog);
//        System.out.println("这是更新页面:after Blog" + blog);
        int ceshinum = blogtagsmapper.deleteByBlogId(blog.getId());
//        System.out.println("这是更新页面删除之前Blog与tlag关系,删除:"+ ceshinum + "条");

        String[] alist =  tlaglist.split(",");
//        System.out.println("这是更新页面新增tlag , 新增alist"+ alist);
        for ( String tlag : alist){
            if (tlag != null & !tlag.equals("")){
                blogtagsmapper.insertBlogTlag(blog.getId(),Integer.parseInt(tlag));
            }
        }
        return flag;
    }


}
