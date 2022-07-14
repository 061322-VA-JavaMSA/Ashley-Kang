package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.daos.UserHibernate;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.services.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private static UserHibernate uht;
	
	@InjectMocks
	private static UserService sut;
	
	@BeforeAll
	public static void setup() {
		sut = new UserService();
	}
	
	
	@Test
	public void getUserByNameExists() throws UserNotFoundException {
		User expected = new User();
		expected.setId(1);
		expected.setRole(User.Role.EMPLOYEE);
		expected.setUser_name("Olivia");
		expected.setUser_pass("p4ssword");
		expected.setUsername("Olivia89");
		
		Mockito.when(uht.getUserByName("Olivia89")).thenReturn(expected);
		
		User actual = sut.getUserByName("Olivia89");
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void getUserByNameNotExists() throws UserNotFoundException {
		
		Mockito.when(uht.getUserByName("Emily768")).thenReturn(null);
		
		assertThrows(UserNotFoundException.class, ()->sut.getUserByName("Emily768"));
	}
	
	
	
	@Test
	public void getUserByIdExists() throws UserNotFoundException {
		User expected = new User();
		expected.setId(3);
		expected.setRole(User.Role.MANAGER);
		expected.setUser_name("Ashley");
		expected.setUser_pass("password");
		expected.setUsername("Ashley99");
		
		Mockito.when(uht.getUserByID(3)).thenReturn(expected);
		
		User actual = sut.getUserByID(3);
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void getUserByIdNotExists() throws UserNotFoundException {
		
		Mockito.when(uht.getUserByID(7)).thenReturn(null);
		
		assertThrows(UserNotFoundException.class, ()->sut.getUserByID(7));
	}
	
	
	@Test
	public void updateUNNotExists() throws UserNotFoundException {	
		assertThrows(UserNotFoundException.class, ()->sut.updateUN("Emily786", "Emily345"));
	}
	
	@Test
	public void updatePNotExists() throws UserNotFoundException {	
		assertThrows(UserNotFoundException.class, ()->sut.updateP("Emily786", 9));
	}
	
	@Test
	public void updateNNotExists() throws UserNotFoundException {	
		assertThrows(UserNotFoundException.class, ()->sut.updateN("Emily786", 9));
	}
}
