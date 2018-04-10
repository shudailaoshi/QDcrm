package com.QDcrm.service;

import java.util.ArrayList;

import com.QDcrm.model.Department;
import com.QDcrm.model.Ditch;

/*
 * @author 刘鑫
 * @date 2018-1-29 20：30
 */
public interface DitchService {
	String insert(Ditch ditch);
	
	public String getDitchByName(Ditch ditch);
	
	public String getDitchByName1(Ditch ditch);
	
	String delete(Ditch di);
	
	String update(Ditch ditch);
	
	Ditch getByUuid(String uuid);
	
	ArrayList<Ditch> getList();
	
	String getonoff(Ditch ditch);
}
