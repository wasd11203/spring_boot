package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.telincn.WebApplicationConfiguration;
import com.telincn.mapper.FunctionMapper;
import com.telincn.mapper.RoleMapper;
import com.telincn.mapper.UserMapper;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringBootTest(classes = WebApplicationConfiguration.class) 
@WebAppConfiguration
public class TestMapper {

	@Autowired  
    private UserMapper userMapper;  
	
	@Autowired  
    private RoleMapper roleMapper;  
	
	@Autowired
	private FunctionMapper functionMapper;
	
    @Test  
    public void testSys() {  
        System.out.println(userMapper.selectUserByName("aa"));  
    }
    
    @Test  
    public void testSource() {  
        System.out.println(roleMapper.loadSourceWithRoleAssociation());  
        List<Map<String, Object>> res = roleMapper.loadSourceWithRoleAssociation();
        Map<Object, ArrayList<Object>> map = new HashMap<Object, ArrayList<Object>>();
        
        for(Map<String, Object> items : res){
        	Object keyTmp = items.get("SOURCE_URL");
        	Object valueItemTmp = items.get("ROLE_NAME");
        	if(map.containsKey(keyTmp)){
        		map.get(keyTmp).add(valueItemTmp);
        	}else{
        		ArrayList<Object> list = new ArrayList<Object>();
        		list.add(valueItemTmp);
        		map.put(keyTmp, list);
        	}
        }
        System.out.println(map);
        
    }
	
    @Test
    public void testFunctionMapper(){
    	
    	System.out.println(functionMapper.loadFunction());
    }
    
}
