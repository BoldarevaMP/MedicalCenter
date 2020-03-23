package unicorn.service.impl;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import unicorn.dao.api.UserDAO;
import unicorn.dto.UserDTO;
import unicorn.entity.User;
import unicorn.service.api.UserService;

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
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDAO.create(mapper.map(userDTO, User.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void update(UserDTO userDTO) {
        User user = userDAO.getById(userDTO.getId());
        userDAO.update(mapper.map(userDTO, User.class));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(UserDTO userDTO) {
        userDAO.delete(mapper.map(userDTO, User.class));
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
}
