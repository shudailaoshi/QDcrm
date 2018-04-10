/**
 * 
 */
package com.QDcrm.query.service;

import java.util.ArrayList;

import com.QDcrm.model.Ditch;

/**
 * @author LiuXin
 * @date 2018-2-1 下午2:49:56
 * @version 我的渠道列表查询
 */
public interface MyDitchService {
	ArrayList<Ditch> getListByEmpUuid(String empUuid);
}
