package blog02.demo.mapperimp;


import blog02.demo.pojo.Tlag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Tlagmapper {

    Tlag selecttlagbyId(int id);

    String returnbyTagname(int id);

    List<Tlag> selectTlagAll();

    List<Tlag> selectPublishTrueTlagAll();

    List<Tlag> selectTlagAllBlogNumber();

    List<Tlag> selectTlagBlogByTlagID();

    List<Tlag> selectTlagByBlogID(int id);

    Tlag selectTlagByName(String name);

    int insertTlag(Tlag tlag);

    int updateTlag(Tlag tlag);

    int deleteTlag(Tlag tlag);
}
