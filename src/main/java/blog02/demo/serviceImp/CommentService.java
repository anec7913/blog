package blog02.demo.serviceImp;

import blog02.demo.mapperimp.Commentmapper;
import blog02.demo.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private Commentmapper commentmapper;

    public List<Comment> commentsList(int blogid){
        List<Comment> cl = commentmapper.selectCommentByBlogId(blogid);
        for (Comment c : cl){
            if (c.getReplyComments().size()>=1){
                for (Comment r :c.getReplyComments()){
                    r.setParentComment(c);
                }
            }
        }
        return cl;
    }

    public int addcomment(Comment comment){
        return commentmapper.insertComment(comment);
    }
}
