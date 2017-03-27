package com.telincn.mapper;

import org.springframework.data.repository.CrudRepository;

import com.telincn.entity.Order;

/**
 * 处理 订单 的持久层类
 * @author 99
 *
 */
public interface OrderDAO extends CrudRepository<Order, String> {

	
	
	
	/**
	 * 新增订单:
	 * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现新增记录,返回插入的记录数
	 * @param orderId 新增的订单id
	 * @param ordername 新增的订单名
	 * @return 插入的记录数
	 */
//	@Transactional
//    @Modifying
//    @Query(value = "insert into user_order (id,order_name) value(?1,?2)",nativeQuery = true)
//	public int save(String orderId,String ordername);
	
	/**
	 * 删除指定的订单:
	 * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现删除记录,返回删除的记录数
	 * @param orderId 要删除的订单id
	 * @return 删除的记录数
	 */
//	@Transactional
//    @Modifying
//    @Query(value = "delete from user_order where id=?1",nativeQuery = true)
//	public int delByOrderId(String orderId);

	/**
	 * 更新指定的订单:
	 * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现更新记录,返回更新的记录数
	 * @param ordername 最新的订单名
	 * @param orderId 要更新的订单id
	 * @return 更新的记录数
	 */
//    @Transactional
//    @Modifying
//    @Query(value = "update user_order t set t.order_name= ?1 where t.id = ?2",nativeQuery = true)
//    public int updateOrderById( String ordername, String orderId);
	
    /**
     * 查询所有的订单:
     * 	执行sql,将得到的结果集,封装为List集合,并返回该List集合
     * @return 所有订单的List集合
     */
//    @Query(value = "select t from Order t")
//    public List<Order> findAllOrder();

    /**
     * 查询指定的订单:
     * 	根据传入的参数,将参数映射到sql中,执行sql语句,实现查询指定订单记录,返回得到的sql记录,并封装为一个订单对象返回
     * @param orderId 要查询的订单id
     * @return 指定的订单对象
     */
//    @Query(value = "select t from Order t where t.id=:orderId")
//    public Order findByOrderId(@Param("orderId")String orderId);

}