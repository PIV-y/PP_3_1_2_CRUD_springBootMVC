package mvc_hiber.dao;

import mvc_hiber.model.User;

import java.util.List;

public interface UserDao {

    void dropData();

    void saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void changeByID(User user);

    User getUserById(long id);

}
