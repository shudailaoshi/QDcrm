package com.QDcrm.service.LogQDService.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.QDcrm.dao.LogQDDao.LogQDDao;
import com.QDcrm.dao.LogQDDao.impl.LogQDDaoImpl;
import com.QDcrm.model.Employee;
import com.QDcrm.model.LogQD;
import com.QDcrm.service.LogQDService.LogQDService;

public class LogQDServiceImpl implements LogQDService {
	
	private LogQDDao logQDDao = new LogQDDaoImpl();
	Logger logger = Logger.getLogger(LogQDServiceImpl.class);
	
	@Override
	public ArrayList<LogQD> getList() {
		ArrayList<LogQD> logQDList = logQDDao.getList();

		return logQDList;
	}// end method getList()

	@Override
	public String delete(String uuid) {
		if (uuid != null && uuid != "") {
			boolean daoFlag = logQDDao.delete(uuid);
			if (daoFlag) {
				return uuid;
			} else {
				logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "EmployeeServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}// end method delete

	@Override
	public String deleteBatch(List<String> uuidList) {
		// TODO Auto-generated method stub
		int i=0;
		boolean daoFlag=true;
		if (uuidList != null && uuidList.size() >0) {
			for(String uuid:uuidList){
				daoFlag=logQDDao.deleteBatch(uuid);
				i=i+1;
				if(daoFlag==false){
					break;
				}
			}
			if (i>0) {
				return "删除成功，批量删除了"+i+"条";
			} else {
				logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除失败！";
			}
		} else {
			String msg = "LogQDService deleteBatch方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

}
