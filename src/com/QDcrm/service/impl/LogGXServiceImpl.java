package com.QDcrm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.QDcrm.dao.LogQDDao.LogGXDao;
import com.QDcrm.dao.LogQDDao.LogQDDao;
import com.QDcrm.dao.LogQDDao.impl.LogGXDaoImpl;
import com.QDcrm.dao.LogQDDao.impl.LogQDDaoImpl;
import com.QDcrm.model.LogGX;
import com.QDcrm.model.LogQD;
import com.QDcrm.model.Record;
import com.QDcrm.service.LogGXService;
import com.QDcrm.service.LogQDService.impl.LogQDServiceImpl;

public class LogGXServiceImpl  implements LogGXService{

	private LogGXDao logQDDao = new LogGXDaoImpl();
	Logger logger = Logger.getLogger(LogQDServiceImpl.class);
	
	@Override
	public ArrayList<LogGX> getList() {
		ArrayList<LogGX> logQDList = logQDDao.getList();
		return logQDList;
	}// end method getList()

	@Override
	public String delete(LogGX rc) {
		if (rc.getUuid() != null && rc.getUuid() != "") {
			boolean daoFlag = logQDDao.delete(rc);
			if (daoFlag) {
				return rc.getUuid();
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
			String msg = "LogGXService deleteBatch方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

}
