package app.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import unicorn.dao.api.UserDAO;
import unicorn.service.api.UserService;
import unicorn.service.impl.UserServiceImpl;

import static org.mockito.Mockito.when;
import static app.tests.DataInit.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserDAO userDAO;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private ModelMapper mapper;

    @Before
    public void setUp(){
        DataInit.setUp();
        userService.setMapper(mapper);
    }
    
    @Test
    public void testGetUserByEmail(){
        when(userDAO.getUserByEmail(user.getEmail())).thenReturn(user);
        assertEquals(userService.getUserByEmail(user.getEmail()).getId(), userDTO.getId());
    }
}
