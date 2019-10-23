package blog02.demo.mapperimp;

import blog02.demo.pojo.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Typemapper {

    Type selecttypebyID(int id);

    List<Type> selectTypeAll();

    List<Type> selectPublishTrueTypeAll();

    Type selectTypeByName(String name);

    int insertType(Type type);

    int updateType(Type type);

    int deleteType(Type type);
}
