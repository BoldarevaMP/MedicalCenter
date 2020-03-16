package unicorn.dao.api;


import unicorn.entity.User;

public interface UserDAO extends GenericDAO <User> {
    User getUserByEmail (String email);
}
