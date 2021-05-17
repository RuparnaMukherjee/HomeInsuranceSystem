package com.cg.hims;

import static org.junit.Assert.assertEquals;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.hims.entities.UserTable;
import com.cg.hims.exceptions.UserNotFoundException;
import com.cg.hims.repository.IUserRepository;
import com.cg.hims.service.IUserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUser {
	@InjectMocks
	private IUserServiceImpl serviceobj;
	@Mock
	private IUserRepository user_repo;
	
	//	1. addNewUser() Service Layer Check
	@Test
	public void addNewUserTest()
	{
		UserTable user=new UserTable();
		user.setUserName("Project_HIMS");
		user.setPassword("Capgemini");
		Mockito.when(user_repo.save(user)).thenReturn(user);
		assertEquals(user,serviceobj.addNewUser(user));
	}
	
	//	2. signIn() Service Layer Check for Invalid UserName
	@Test
	public void testNegativesignIn() throws UserNotFoundException {
		UserTable user=new UserTable();
		user.setUserName("Project_HIMS");
		user.setPassword("Capgemini");
		
		Executable ex=()->{
			serviceobj.signIn("HIMS_Project","Capg");
		};
		
		Assertions.assertThrows(UserNotFoundException.class, ex);
	}
	
	//	3. signIn() Service Layer Check for Wrong Password
	@Test
	public void testNegativesignIn1() throws UserNotFoundException {
		UserTable user=new UserTable();
		user.setUserName("Project_HIMS");
		user.setPassword("Capgemini");
		
		String testReturn="Password didnt match";
		
		Mockito.when(user_repo.existsById("Project_HIMS")).thenReturn(true);
		Mockito.when(user_repo.findById("Project_HIMS")).thenReturn(Optional.of(user));

		
		Assertions.assertEquals(testReturn, serviceobj.signIn("Project_HIMS","Capg"));
	}
	
	//	4. signIn() Service Layer Check for Correct Credentials
	@Test
	public void testPositivesignIn() throws UserNotFoundException {
		UserTable user=new UserTable();
		user.setUserName("Project_HIMS");
		user.setPassword("Capgemini");
		
		String testReturn= "Logged In SuccessFully";
		
		Mockito.when(user_repo.existsById("Project_HIMS")).thenReturn(true);
		Mockito.when(user_repo.findById("Project_HIMS")).thenReturn(Optional.of(user));

		
		Assertions.assertEquals(testReturn, serviceobj.signIn("Project_HIMS","Capgemini"));
	}

}
