package blog02.demo.mapperimp;

import blog02.demo.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Commentmapper {

    List<Comment> selectCommentByBlogId(int blodid);

    Comment selectParentCommentByParentCommentId(int ParentCommentId);

    int insertComment(Comment comment);

}
