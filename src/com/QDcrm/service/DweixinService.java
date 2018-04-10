/**
 * 
 */
package com.QDcrm.service;

import java.util.ArrayList;

import com.QDcrm.model.Dphone;
import com.QDcrm.model.Dweixin;

/**
 * @author LiuXin
 * @date 2018-2-8 下午4:37:24
 * @version
 */
public interface DweixinService {
	String insert(Dweixin dweixin);

	String delete(String uuid);

	String deleteByDitchUuid(String ditchUuid);
	
	ArrayList<Dweixin> getList();
	
	ArrayList<Dweixin> getListByDitchUuid(String ditchUuid);
	
	ArrayList<Dweixin> getListByWeixinNO(String weixinNO);
}
