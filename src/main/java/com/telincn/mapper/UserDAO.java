package com.telincn.mapper;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.telincn.entity.User;

/**
 * 处理 用户 的持久层类
 * @author 99
 *
 */
public interface UserDAO extends CrudRepository<User, String> {
	
	/**
	 * 根据用户名查询指定的用户:
     * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现查询指定用户信息,并将其封装为一个User对象返回
     * 	注: 该方法交由 jpa 负责实现
     * 最近一次修改时间: 2016.11.30
	 * 修改备注: 新增方法 并 使用
     * @param username 要查询的用户的用户名
     * @return 封装了指定的用户信息的对象
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	
	/**
	 * 查询除指定的用户外的所有用户:
	 * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现查询除指定用户外的所有用户信息,将其封装为一个List集合返回
	 * 
	 * 最近一次修改时间: 2016.11.30
	 * 修改备注: 启用方法
	 * @param userId 要排除的用户id
	 * @return 除指定用户外的所有用户信息的List集合
	 */
	@Query(value = "select t from User t where t.id !=:userId")
	public List<User> findAllExceptCurrentUser(@Param("userId")String userId);
	
	/**
	 * 新增用户:
	 * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现新增记录,返回插入的记录数
	 * 
	 * 最近一次修改时间: 2016.11.30
	 * 修改备注: 未使用当前方法
	 * @param userId 新增的用户id
	 * @param username 新增的用户名
	 * @param password 新增的用户密码
	 * @return
	 * 
	 */
//	@Transactional
//    @Modifying
//    @Query(value = "insert into user (id,username,password) value(?1,?2,?3)",nativeQuery = true)
//	public int save(String userId,String username,String password);
	
	/**
	 * 删除指定的用户:
	 * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现删除记录,返回删除的记录数
	 * 
	 * 最近一次修改时间: 2016.11.30
	 * 修改备注: 未使用当前方法
	 * @param userId 要删除的用户id
	 * @return 删除的记录数
	 */
//	@Transactional
//  @Modifying
//  @Query(value = "delete from user where id=?1",nativeQuery = true)
//	public int delByUserId(String userId);

	/**
	 * 更新指定的用户:
	 * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现更新记录,返回更新的记录数
	 * 
	 * 最近一次修改时间: 2016.11.30
	 * 修改备注: 未使用当前方法
	 * @param username 最新的用户名
	 * @param password 最新的用户密码 
	 * @param userId 要更新的用户id
	 * @return 更新的记录数
	 */
//    @Transactional
//    @Modifying
//    @Query(value = "update user t set t.username = ?1,t.password= ?2 where t.id = ?3",nativeQuery = true)
//    public int updateUserById( String username, String password,String userId);

    
    /**
     * 根据用户名查询指定的用户:
     * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现查询指定用户信息,并将其封装为一个User对象返回
     * 
     * 最近一次修改时间: 2016.11.30
	 * 修改备注: 未使用当前方法
     * @param username 要查询的用户的用户名
     * @return 封装了指定的用户信息的对象
     */
//    @Query(value = "select t from User t where t.username=:username")
//    public User findByUsername(@Param("username")String username);
    
    /**
     * 根据用户id查询指定用户:
     * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现查询指定用户信息,并将其封装为一个User对象返回
     * 
     * 最近一次修改时间: 2016.11.30
	 * 修改备注: 未使用当前方法
     * @param userId 要查询的用户id
     * @return 封装了指定的用户信息的对象
     */
//    @Query(value = "select t from User t where t.id=:userId")
//    public User findByUserId(@Param("userId")String userId);
	
}
