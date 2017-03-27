package daoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.telincn.SpringBootApplicationMain;
import com.telincn.entity.User;
import com.telincn.mapper.UserDAO;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringApplicationConfiguration(classes = SpringBootApplicationMain.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class UnitTestUserDAO {
	
	@Autowired
	private UserDAO userDAO;
	
//	@Test
//	public void test1(){
//		System.out.println(userDAO.save("3", "AAA", "123"));
//	}
//	
//	@Test
//	public void test2(){
//		
//		System.out.println(userDAO.delByUserId("3"));
//	}
//	
//	@Test
//	public void test3(){
//		System.out.println(userDAO.updateUserById("admin", "123456", "1"));
//	}
//	
	@Test
	public void test4(){
		System.out.println(userDAO.findAllExceptCurrentUser("1"));
	}
//	
//	@Test
//	public void test5(){
//		
//		System.out.println(userDAO.findByUserId("1"));
//	}
//	
//	@Test
//	public void test6(){
//		
//		System.out.println(userDAO.findByUsername("admin"));
//	}
//	
//	
	@Test
	public void test7(){
		//如果 新增的用户记录 中存在重名现象。就报错
		User user1 = new User("2", "AAA", "123");
		System.out.println(userDAO.save(user1));
		user1 = new User("3", "AAA", "123");
		System.out.println(userDAO.save(user1));
	}
	@Test
	public void test8(){
		
		System.out.println(userDAO.findOne("1"));
	}
	@Test
	public void test9(){
		
		System.out.println(userDAO.findAll());
	}
	@Test
	public void test10(){
		//如果删除一个不存在的用户记录，那么将会抛异常
		userDAO.delete("2");
	}
	
	@Test
	public void test12(){
		
		System.out.println(userDAO.count());
	}
	@Test
	public void test13(){
		
		System.out.println(userDAO.findByUsername("admin"));
	}
	@Test
	public void test14(){
		//修改记录时，如果出现重名，也会抛出异常
		User user = new User("1", "admin", "123");
		System.out.println(userDAO.save(user));
	}
	@Test
	public void test11(){
		
		System.out.println(userDAO.exists("1"));
	}
}
