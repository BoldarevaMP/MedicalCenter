package app.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import unicorn.dao.api.UserDAO;
import unicorn.entity.enums.Role;
import unicorn.service.impl.UserServiceImpl;

import static app.tests.DataInit.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @Mock
    private BCryptPasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private ModelMapper mapper;

    @Before
    public void setUp() {
        DataInit.setUpUserDoctor();
        DataInit.setUpUserDTODoctor();
        DataInit.setUpUserDTONurse();
        DataInit.setUpUserNurse();
        userService.setMapper(mapper);
    }

    @Test
    public void testCreateUserDoctor() {
        when(encoder.encode(userDTODoctor.getPassword())).thenReturn(userDTODoctor.getPassword());
        userService.create(userDTODoctor);
        assertEquals(Role.ROLE_DOCTOR, userDTODoctor.getRole());
    }

    @Test
    public void testCreateUserNurse() {
        when(encoder.encode(userDTONurse.getPassword())).thenReturn(userDTONurse.getPassword());
        userService.create(userDTONurse);
        assertEquals(Role.ROLE_NURSE, userDTONurse.getRole());
    }


    @Test
    public void testGetUserByEmail() {
        when(userDAO.getUserByEmail(userDoctor.getEmail())).thenReturn(userDoctor);
        assertEquals(userService.getUserByEmail(userDoctor.getEmail()).getId(), userDTODoctor.getId());
    }

    @Test(expected = NullPointerException.class)
    public void testGetUserByEmailNull() {
        when(userDAO.getUserByEmail(userDoctor.getEmail())).thenReturn(null);
        assertEquals(userService.getUserByEmail(userDoctor.getEmail()).getId(), userDTODoctor.getId());
    }
}
