package blog02.demo.mapperimp;


import blog02.demo.pojo.Blog;
import blog02.demo.pojo.searchObj;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Blogmapper {

    List<Blog> selectBlogAll();

    Blog selectBlogbyID(int id);

    List<Blog> selectBlogByPublished();

    int selectBlogByPublishedCount();

    List<Blog> selectBlogByRecommend();

    List<Blog> selectBlogPublishedByRecommend();

    List<Blog> selectBlogPublishedByTypeID(int id);

    List<Blog> selectBlogByTlagID(int id);

    List<Blog> selectBlogPublishedByTlagID(int id);

    List<Blog> selectBlogByTypeID(int id);

    List<String> selectYearList();

    List<Blog> selectBlogByYear(String year);

    int selectBlogCount();

    List<Blog> searchBlog(searchObj search);

    List<Blog> searchBlogPublished(String query);

    int insertBlog(Blog blog);

    int deleteBlogById(long id);

    int updateBlog(Blog blog);

}