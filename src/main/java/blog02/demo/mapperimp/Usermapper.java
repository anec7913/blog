package blog02.demo.mapperimp;

import blog02.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Usermapper {

    List<User> selectUserAll();

    User selectUserbyID(int id);

    User selectUserbyUsername(String username);
}
