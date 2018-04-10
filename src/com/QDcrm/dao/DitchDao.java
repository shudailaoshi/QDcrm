package com.QDcrm.dao;

import java.util.ArrayList;
import java.util.List;

import com.QDcrm.model.Ditch;

/*
 * @author 刘鑫
 * @date 2018-01-29 20:17
 */
public interface DitchDao {
	//新增
	public boolean insert(Ditch ditch);
	//校验重名
	public List<Ditch> getDitByName(Ditch ditch);
	//删除
	public boolean delete(String uuid);
	//修改
	public boolean update(Ditch ditch);
	//查询单个
	public Ditch getByUuid(String uuid);
	//列表查询
	public ArrayList<Ditch> getList();
	//设置开关
	public boolean updateOnOff(String uuid,String oac);
	//根据uuid查询
	List<Ditch> findIdlist(String uuid);
}
