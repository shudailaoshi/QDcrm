/**
 * 
 */
package com.QDcrm.query.dao;

import java.util.ArrayList;

import com.QDcrm.model.Ditch;

/**
 * @author LiuXin
 * @date 2018-2-1 下午2:37:59
 * @version 我的渠道列表查询
 */
public interface MyDitchDao {
	// 根据empUuid查询
	public ArrayList<Ditch> getListByEmpUuid(String empUuid);
}
