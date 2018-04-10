/**
 * 
 */
package com.QDcrm.dao;

import java.util.ArrayList;
import java.util.List;

import com.QDcrm.model.Ditch;
import com.QDcrm.model.Record;

/**
 * @author LiuXin
 * @date 2018-1-30 下午4:24:57
 * @version 跟踪记录表dao
 */
public interface RecordDao {
	// 新增
	public boolean insert(Record record);

	// 删除
	public boolean delete(String uuid);

	// 根据ditchUuid删除
	public boolean deleteByDitchUuid(String ditchUuid);

	// 修改
	public boolean update(Record record);

	// 单个查询
	public Record getByUuid(String uuid);

	// 列表查询
	public ArrayList<Record> getList();

	// 根据ditchUuid查询
	public ArrayList<Record> getListByDitchUuid(String ditchUuid);

	// 设置开关
	public boolean updateOnOff(String uuid, String oac);
	
	//根据ID查询
	List<Record> findIdShow(String uuid);
	//修改的modifyDate
	boolean updateModifyDate(String modifyDate,String uuid);
}
