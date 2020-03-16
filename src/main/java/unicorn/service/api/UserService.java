package unicorn.service.api;

import unicorn.dto.UserDTO;

public interface UserService {

    void create (UserDTO userDTO);

    UserDTO getUserByEmail (String email);
}