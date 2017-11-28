package dao;

import models.User;

public interface UserDAO {
    public User getByid(int id);
    public User login(String username,String password);
    public void deleteById(int id);
    public void editUser(User user);
    public void register(String username,String password);
}
