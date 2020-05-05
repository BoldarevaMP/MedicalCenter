package unicorn.service.api;

import unicorn.dto.UserDTO;

import java.util.List;

public interface UserService {

    void create(UserDTO userDTO);

    UserDTO getUserByEmail(String email);

    List<UserDTO> getAll();
}
