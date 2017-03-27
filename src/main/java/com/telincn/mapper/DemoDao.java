package com.telincn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.telincn.entity.Demo;

@Mapper
public interface DemoDao {
	public int countAll();
	public List<Demo> findByPage(Map<String, Integer> pageInfo);
	public void save(Demo demo);
}
