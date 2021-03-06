package dao;

import models.User;

import java.util.ArrayList;

public interface UserDAO {
    User getByid(int id);
    User login(String username);
    void deleteById(int id);
    void editUser(User user);
    void register(String username,String password) throws Exception;
    ArrayList<User> getAllUsers();
}
