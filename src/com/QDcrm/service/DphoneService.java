/**
 * 
 */
package com.QDcrm.service;

import java.util.ArrayList;

import com.QDcrm.model.Ditch;
import com.QDcrm.model.Dphone;
import com.QDcrm.model.Record;

/**
 * @author LiuXin
 * @date 2018-2-8 下午2:35:52
 * @version
 */
public interface DphoneService {
	String insert(Dphone dphone);

	String delete(String uuid);

	String deleteByDitchUuid(String ditchUuid);

	ArrayList<Dphone> getList();
	
	ArrayList<Dphone> getListByDitchUuid(String ditchUuid);
	
	ArrayList<Dphone> getListByPhoneNO(String phoneNO);
}
