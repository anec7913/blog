package blog02.demo.mapperimp;

import blog02.demo.pojo.BlogTags;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Blogtagsmapper {

    List<BlogTags> selectBlogTlagByBlogID(int id);

    List<BlogTags> selectBlogTlagByTlagID(int id);

    int insertBlogTlag(long blogId,long tagsId);

    int deleteByBlogId(long id);
}
