package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.UserDAO;
import unicorn.dto.TreatmentDTO;
import unicorn.dto.UserDTO;
import unicorn.entity.Treatment;
import unicorn.entity.User;
import unicorn.entity.enums.Role;
import unicorn.service.api.UserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of service for user handling
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = Logger.getLogger(EventServiceImpl.class);
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void create(UserDTO userDTO) {
        if (userDTO.getIdentKey().equals("Doctor2020")) {
            userDTO.setRole(Role.ROLE_DOCTOR);
        } else if (userDTO.getIdentKey().equals("Nurse2020")) {
            userDTO.setRole(Role.ROLE_NURSE);
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDAO.create(mapper.map(userDTO, User.class));
        logger.info("User is created.");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userDAO.getUserByEmail(email);
        if (user != null) {
            logger.info(String.format("Successfully got user with login %s", user.getEmail()));
            return mapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public List<UserDTO> getAll() {
        List<User> userList = userDAO.getAll();
        return userList.stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }

    public void setMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
}
