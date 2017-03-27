package serviceTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.telincn.SpringBootApplicationMain;
import com.telincn.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringApplicationConfiguration(classes = SpringBootApplicationMain.class) // 指定我们SpringBoot工程的Application启动类
public class UnitTestUserService {
	
	@Autowired
	private UserService userServiceImpl;
	
	@Test
	public void test1(){
		System.out.println();
	}
	
	@Test
	public void test2(){
		
		System.out.println(userServiceImpl.createUser("admin", "123"));
	}
	
	@Test
	public void test3(){
		System.out.println(userServiceImpl.delUser("2"));
	}
	
	@Test
	public void test4(){
		System.out.println(userServiceImpl.updateUser("1", "admin", "123"));
	}
	
	@Test
	public void test5(){
		System.out.println(userServiceImpl.getAllUserExceptCurrentUser("1"));
	}
	
	@Test
	public void test6(){
		
		System.out.println(userServiceImpl.findByUsername("admin","123"));
	}
	
	@Test
	public void test7(){
		System.out.println(userServiceImpl.findByUserId("1321"));
	}
	

	
}
