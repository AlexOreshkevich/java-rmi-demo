package dao;

import model.User;

import java.util.List;

public interface UserDao extends CrudDao<Long, User> {

    List<User> findAll();
}
