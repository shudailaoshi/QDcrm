/**
 * 
 */
package com.QDcrm.service;

import java.util.ArrayList;

import com.QDcrm.model.Ditch;
import com.QDcrm.model.Record;

/**
 * @author LiuXin
 *@date 2018-1-30 下午4:36:03
 *@version 跟踪记录表service
 */
public interface RecordService {
	String insert(Record record);
	
	String delete(Record uuid);
	
	String deleteByDitchUuid(Record record);
	
	String update(Record record);
	
	Record getByUuid(String uuid);
	
	ArrayList<Record> getList();
	
	ArrayList<Record> getListByDitchUuid(String ditchUuid);
	
	String getonoff(Record record);
}
