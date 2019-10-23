package blog02.demo;

import blog02.demo.Util.MD5Utils;
import blog02.demo.mapperimp.Blogmapper;
import blog02.demo.mapperimp.Commentmapper;
import blog02.demo.mapperimp.Typemapper;
import blog02.demo.mapperimp.Usermapper;
import blog02.demo.pojo.Blog;
import blog02.demo.pojo.Comment;
import blog02.demo.pojo.Type;
import blog02.demo.pojo.User;
import blog02.demo.serviceImp.CommentService;
import blog02.demo.serviceImp.UserServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private UserServiceImp userServiceImp ;

    @Autowired
    private Blogmapper blogmapper;

    @Autowired
    private Typemapper typemapper;

    @Test
    public void contextLoads() {

        Blog b = new Blog();

        b.setContent("12345");
        b.setAppreciation(true);
        b.setCommentabled(true);
        Date d = new Date();
        b.setCreateTime(d);
        b.setUpdateTime(d);
        b.setFirstPicture("https://c-ssl.duitang.com/uploads/item/201211/22/20121122024907_ifns5.thumb.700_0.jpeg");
        b.setDescription("123456");
        b.setFlag("转载");
        b.setPublished(true);
        b.setRecommend(true);
        b.setShareStatement(true);
        b.setTitle("111111");
        b.setTypeId((long)3);
        b.setUserId((long)1);

        int num =  blogmapper.insertBlog(b);
        System.out.println(b.getId());

    }


    @Test
    public void contextLoads02() {
        Blog b = new Blog();
        b.setContent("12345");
        b.setAppreciation(null);
        b.setCommentabled(null);
        Date d = new Date();
        b.setUpdateTime(d);
        b.setFirstPicture("https://c-ssl.duitang.com/uploads/item/201211/22/20121122024907_ifns5.thumb.700_0.jpeg");
        b.setDescription("123456");
        b.setFlag("转载");
        b.setPublished(null);
        b.setRecommend(null);
        b.setShareStatement(null);
        b.setTitle("111111");
        b.setTypeId((long)3);
        b.setId((long)17);
       int ss =  blogmapper.updateBlog(b);
        System.out.println(ss);
    }

    @Autowired
    private Commentmapper commentmapper;

    @Test
    public void contextLoads03() {
        Comment comment = new Comment();

//        comment.setNickname("大笨牛");
//        comment.setAvatar("http://b-ssl.duitang.com/uploads/item/201810/18/20181018162951_kgwzm.thumb.700_0.jpeg");
//        comment.setContent("写得好,真的是牛逼了!!!!!!!!!!!!!!");
//        comment.setEmail("121321@qq.com");
//        comment.setAdminComment(false);
//        comment.setBlogId(32);
//        Date d = new Date();
//        comment.setCreateTime(d);
//        int num = commentmapper.insertComment(comment);
//        System.out.println(num);

//        comment.setNickname("大笨象");
//        comment.setAvatar("http://b-ssl.duitang.com/uploads/item/201810/18/20181018162951_kgwzm.thumb.700_0.jpeg");
//        comment.setContent("课可以可以!!!!");
//        comment.setEmail("1314123123@qq.com");
//        comment.setAdminComment(true);
//        comment.setBlogId(32);
//        Date d = new Date();
//        comment.setCreateTime(d);
//        comment.setParentCommentId(1);
//        int num = commentmapper.insertComment(comment);
//        System.out.println(num);


        List<Comment> cl = commentmapper.selectCommentByBlogId(32);
        System.out.println(cl);

    }

    @Test
    public void contextLoads04() {
        List<Type> ulist =  typemapper.selectPublishTrueTypeAll();
        for (Type t : ulist){
            System.out.println(t.getName());
        }
    }

    @Autowired
    private CommentService commentService;

    @Test
    public void contextLoads05() {
        String mm = "hequn7913";
        System.out.println(MD5Utils.code("hequn7913"));
        // ee5259242267f819149807c955fd10c4
    }



}
