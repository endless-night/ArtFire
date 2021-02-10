package artfire.dao.mapper;

import artfire.entity.User;

public interface UserMapper {

     User checkUser(String username, String password);

}
