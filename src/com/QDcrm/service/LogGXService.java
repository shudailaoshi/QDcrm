package com.QDcrm.service;

import java.util.ArrayList;
import java.util.List;

import com.QDcrm.model.LogGX;
import com.QDcrm.model.Record;

public interface LogGXService {
	public String delete(LogGX rc);
	
	public ArrayList<LogGX> getList();
	
	public String deleteBatch(List<String> uuidList);
}
