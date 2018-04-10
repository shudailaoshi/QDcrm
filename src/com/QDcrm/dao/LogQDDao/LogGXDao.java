package com.QDcrm.dao.LogQDDao;

import java.util.ArrayList;
import java.util.List;

import com.QDcrm.model.LogGX;
import com.QDcrm.model.Record;

public interface LogGXDao {
	public boolean delete(LogGX rc);
	
	public ArrayList<LogGX> getList();
	
	public boolean insert(LogGX rc);
	
	public boolean deleteBatch(String uuid);
}
