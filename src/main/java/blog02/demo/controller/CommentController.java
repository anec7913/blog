package blog02.demo.controller;

import blog02.demo.mapperimp.Blogmapper;
import blog02.demo.pojo.Comment;
import blog02.demo.pojo.User;
import blog02.demo.serviceImp.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private Blogmapper blogmapper;

    @Autowired
    private CommentService commentService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable int blogId, Model model) {
        List<Comment> clist = commentService.commentsList(blogId);
        model.addAttribute("comments", clist);
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {
        int blogId = comment.getBlogId();
        comment.setBlog(blogmapper.selectBlogbyID(blogId));
        User user = (User) session.getAttribute("user");
        if (user != null) {
            comment.setAvatar(user.getAvatar());
            comment.setAdminComment(true);
        } else {
            comment.setAvatar(avatar);
        }
        int num = commentService.addcomment(comment);
        return "redirect:/comments/" + blogId;
    }

}
