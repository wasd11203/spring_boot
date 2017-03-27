package com.telincn.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.telincn.entity.ExEntity;

/**
 * DAO层使用集成的JPA框架
 * 	1. 添加JPA 依赖支持(见pom.xml文件)
 * 	2. 修改application.properties文件(添加datasource 的配置)
 * 	3. 创建DAO接口(一定要是接口才可以继承)继承自org.springframework.data.repository下的CrudRepository接口
 * 		1. 其中 CrudRepository<T,ID> 中的T 处填写 实体类名,ID处填写 主键的类型
 * 		2. 在增、删、改 三个操作是 必须 要添加 @Transactional 注解的
 * 			1. 查询是不需要的@Transactional注解的
 * 		3. @Modifying 注解标识的方法返回值只能是void或者是int类型
 * 		4. @Query 注解中,nativeQuery = true 表示使用原生的Sql语句
 * 	4. 创建表对应的实体 类 (参照 ExEntity)
 * @author 99
 *
 */
public interface DAO extends CrudRepository<ExEntity, Integer> {
	@Transactional
    @Modifying
    @Query(value = "insert into demo (id,username,password) value(?1,?2,?3)",nativeQuery = true)
	public int save(String demoId,String username,String password);
	
	@Query(value="select t from ExEntity t where t.id =:demoId")
	public ExEntity findById(@Param("demoId")Integer demoId);
	
}
