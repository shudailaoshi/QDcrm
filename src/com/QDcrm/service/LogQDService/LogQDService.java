package com.QDcrm.service.LogQDService;

import java.util.ArrayList;
import java.util.List;

import com.QDcrm.model.LogQD;

public interface LogQDService {

	ArrayList<LogQD> getList();

	String delete(String uuid);
	
	public String deleteBatch(List<String> uuidList);

}
