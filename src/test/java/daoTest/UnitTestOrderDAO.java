package daoTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.telincn.SpringBootApplicationMain;
import com.telincn.entity.Order;
import com.telincn.mapper.OrderDAO;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！ 
@SpringApplicationConfiguration(classes = SpringBootApplicationMain.class) // 指定我们SpringBoot工程的Application启动类
@WebAppConfiguration
public class UnitTestOrderDAO {
	
	@Autowired
	private OrderDAO orderDAO;
	
//	@Test
//	public void test1(){
//		System.out.println(orderDAO.save("3", "AAA"));
//	}
//	
//	@Test
//	public void test2(){
//		
//		System.out.println(orderDAO.delByOrderId("3"));
//	}
//	
//	@Test
//	public void test3(){
//		System.out.println(orderDAO.updateOrderById("admin", "1"));
//	}
//	
//	@Test
//	public void test4(){
//		System.out.println(orderDAO.findAllOrder());
//	}
//	
//	@Test
//	public void test5(){
//		System.out.println(orderDAO.findByOrderId("1"));
//	}

	@Test
	public void test7(){
		Order order = new Order("2", "AAA");
		System.out.println(orderDAO.save(order));
	}
	@Test
	public void test8(){
		
		System.out.println(orderDAO.findOne("2"));
	}
	@Test
	public void test9(){
		
		System.out.println(orderDAO.findAll());
	}
	@Test
	public void test10(){
		
		orderDAO.delete("2");
	}
	
	@Test
	public void test12(){
		
		System.out.println(orderDAO.count());
	}

	@Test
	public void test14(){
		Order order = new Order("2", "AAA");
		System.out.println(orderDAO.save(order));
	}
	@Test
	public void test11(){
		
		System.out.println(orderDAO.exists("1"));
	}
	
}
