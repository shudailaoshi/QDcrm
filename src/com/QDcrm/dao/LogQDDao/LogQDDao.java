package com.QDcrm.dao.LogQDDao;

import java.util.ArrayList;

import com.QDcrm.model.LogQD;

public interface LogQDDao {

	boolean delete(String uuid);

	ArrayList<LogQD> getList();
	
	boolean insert(LogQD logd);
	
	public boolean deleteBatch(String uuid);

}
