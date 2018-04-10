/**
 * 
 */
package com.QDcrm.query.service;

import java.util.ArrayList;

import com.QDcrm.model.Record;

/**
 * @author LiuXin
 *@date 2018-1-31 下午3:41:25
 *@version 跟踪记录表分页查询
 */
public interface QuePageRecordService {
	ArrayList<Record> getPageByDitchUuid(String ditchUuid,
			int currentPage, int maxResult);
}
