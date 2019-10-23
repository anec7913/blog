package blog02.demo.serviceImp;

import blog02.demo.Util.MD5Utils;
import blog02.demo.mapperimp.Usermapper;
import blog02.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp {

    @Autowired
    private Usermapper usermapper;

    public User UserAuth(String username,String password){
        User u = usermapper.selectUserbyUsername(username);
        if (u!=null){
            if(u.getPassword().equals(MD5Utils.code(password))){
//            if(u.getPassword().equals(password)){
                return u;
            }
            return null;
        }
        return null;
    }
}
