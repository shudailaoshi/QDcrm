package com.QDcrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.QDcrm.dao.DitchDao;
import com.QDcrm.dao.DphoneDao;
import com.QDcrm.dao.DweixinDao;
import com.QDcrm.dao.EmployeeDao;
import com.QDcrm.dao.RecordDao;
import com.QDcrm.dao.LogQDDao.LogQDDao;
import com.QDcrm.dao.LogQDDao.impl.LogQDDaoImpl;
import com.QDcrm.dao.impl.DitchDaoImpl;
import com.QDcrm.dao.impl.DphoneDaoImpl;
import com.QDcrm.dao.impl.DweixinDaoImpl;
import com.QDcrm.dao.impl.EmployeeDaoImpl;
import com.QDcrm.dao.impl.RecordDaoImpl;
import com.QDcrm.model.Department;
import com.QDcrm.model.Ditch;
import com.QDcrm.model.Dphone;
import com.QDcrm.model.Dweixin;
import com.QDcrm.model.Employee;
import com.QDcrm.model.LogQD;
import com.QDcrm.service.DitchService;
import com.QDcrm.service.DphoneService;
import com.QDcrm.service.DweixinService;
/*
 * 
 * @author 刘鑫
 * @date 2018-1-29 20:45
 */
public class DitchServiceImpl implements DitchService {
	private DitchDao ditchDao = new DitchDaoImpl();
	EmployeeDao employeeDao = new EmployeeDaoImpl();
	RecordDao recordDao = new RecordDaoImpl();
	Logger logger = Logger.getLogger(DitchServiceImpl.class);
	LogQDDao lgg = new LogQDDaoImpl();
	
	DphoneService dphoneService = new DphoneServiceImpl();
	private DphoneDao dphoneDao = new DphoneDaoImpl();
	DweixinService dweixinService=new DweixinServiceImpl();
	private DweixinDao dweixinDao=new DweixinDaoImpl();

	@Override
	public String insert(Ditch ditch) {
		// TODO Auto-generated method stub
		String flag = this.getDitchByName1(ditch);
		if (flag.equals("yes")) {
			return flag;
		} else {
			ditch.setUuid(UUID.randomUUID().toString());
			System.out.println("^^在DitchServiceImpl收到数据 ：" + ditch.toString()
					+ "收到结束!");
			boolean daoFlag = ditchDao.insert(ditch);
			  LogQD lg = new LogQD();
		      lg.setUuid(UUID.randomUUID().toString());
		      lg.setUserUuid(ditch.getUserUuid());
		      lg.setUserName(ditch.getUserName());
		      lg.setTableName("t_ditch");
		      lg.setTableNameChina("渠道表");
		      lg.setDataUuid(ditch.getUuid());
		      lg.setDataName(ditch.getName());
		      lg.setUserAction("新增");
		      Date date = new Date();
		      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      String da = sdf.format(date);
		      lg.setUpdateTime(da);
		      
		      Date createdate = new Date();
		      SimpleDateFormat sdfCreate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		      String createD = sdf.format(createdate);
		      lg.setUpdateTime(createD);
		      
		      lgg.insert(lg);
			if (daoFlag) {
			    //写入手机号码库
			  Dphone dphone = new Dphone();
			  dphone.setPhoneNO(ditch.getPhone());
			  dphone.setDitchUuid(ditch.getUuid());
			  dphone.setEmpDitUuid(ditch.getEmpUuid());
			  String result = dphoneService.insert(dphone);
			  
			    //写入微信号码库
			  Dweixin dweixin=new Dweixin();
			  dweixin.setWeixinNO(ditch.getWeixin());
			  dweixin.setDitchUuid(ditch.getUuid());
			  dweixin.setEmpDitUuid(ditch.getEmpUuid());
			  String result2=dweixinService.insert(dweixin);
				return ditch.getUuid();
			} else {
				logger.error("插入不成功,dao层执行有出错地方,请联系管理员");
				return "插入不成功,dao层执行有出错地方,请联系管理员";
			}
		}
	}

	@Override
	public String getDitchByName(Ditch ditch) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDitchByName1(Ditch ditch) {
		// TODO Auto-generated method stub
		String flag = "";
		List<Ditch> ditchList = ditchDao.getDitByName(ditch);
		for (Ditch ditch2 : ditchList) {
			String uuid = ditch2.getUuid();
			boolean flagSelf = uuid.equals(ditch.getUuid());
			boolean flagNotSelf = !flagSelf;
			if (flagNotSelf) {
				if (ditch2.getUuid() != null) {
					flag = "yes";
					return flag;
				}
			}
		}
		flag = "no";
		return flag;
	}

	@Override
	public String delete(Ditch di) {
		// TODO Auto-generated method stub
		if (di.getUuid() != null && di.getUuid() != "") {
			List<Ditch> yxstudent = ditchDao.findIdlist(di.getUuid());
	    	LogQD lg = new LogQD();
	    	lg.setUuid(UUID.randomUUID().toString());
	        lg.setUserUuid(di.getUserUuid());
	        lg.setUserName(di.getUserName());
	        lg.setTableName("t_ditch");
	        lg.setTableNameChina("渠道表");
	        lg.setDataUuid(yxstudent.get(0).getUuid());
	        lg.setDataName(yxstudent.get(0).getName());
	        lg.setUserAction("删除");
	        Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String da = sdf.format(date);
	        lg.setUpdateTime(da);
	        lgg.insert(lg);
			
			boolean daoFlag = ditchDao.delete(di.getUuid());
			boolean daoFlag2 = recordDao.deleteByDitchUuid(di.getUuid());
			boolean daoFlag3 = dphoneDao.deleteByDitchUuid(di.getUuid());
			boolean daoFlag4=dweixinDao.deleteByDitchUuid(di.getUuid());
			if (daoFlag && daoFlag2) {
				return di.getUuid();
			} else {
				logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DitchServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	@Override
	public String update(Ditch ditch) {
		// TODO Auto-generated method stub
		String flag = this.getDitchByName1(ditch);
		if (flag.equals("yes")) {
			return flag;
		} else {
		    //变更负责人写入日志start
		  Ditch ditchOne = ditchDao.getByUuid(ditch.getUuid());
		    boolean flagOne = ditchOne.getEmpUuid().equals(ditch.getEmpUuid());
		    if((!flagOne) && ditchOne != null){
		        LogQD lg = new LogQD();
		        lg.setUuid(UUID.randomUUID().toString());
		        lg.setUserUuid(ditch.getUserUuid());
                lg.setUserName(ditch.getUserName());
                lg.setTableName("t_ditch");
                lg.setTableNameChina("渠道表");
		        lg.setDataUuid(ditchOne.getUuid());
		        lg.setDataName(ditchOne.getName());
		        lg.setUserAction("变更负责人");
		        Date date = new Date();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String da = sdf.format(date);
		        lg.setUpdateTime(da);
		        
		        //写入原来XXX负责人变成了现在XXX负责人
		        String strText = "由";
		         strText = strText+ "("+ ditchOne.getEmpName()+")，变为(";
		        //查出更改的负责人
		        Employee employee = employeeDao.getByUuid(ditch.getEmpUuid());
		        strText = strText +employee.getName()+ ")";
		        lg.setDataGxChina(strText);
		        lgg.insert(lg);
		    }
		  
		    //变更负责人写入日志end
			String uuid = ditch.getUuid();
			if (uuid != null && uuid != "") {
				boolean daoFlag = ditchDao.update(ditch);
				LogQD lg = new LogQD();
		    	lg.setUuid(UUID.randomUUID().toString());
		        lg.setUserUuid(ditch.getUserUuid());
		        lg.setUserName(ditch.getUserName());
		        lg.setTableName("t_ditch");
		        lg.setTableNameChina("渠道表");
		        lg.setDataUuid(ditch.getUuid());
		        lg.setDataName(ditch.getName());
		        lg.setUserAction("编辑");
		        Date date = new Date();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String da = sdf.format(date);
		        lg.setUpdateTime(da);
		        lgg.insert(lg);
				if (daoFlag) {
				  //修改手机库记录，先删后新增
				  boolean daoFlagDphone = dphoneDao.deleteByDitchUuid(uuid);
				  Dphone dphone = new Dphone();
	              dphone.setPhoneNO(ditch.getPhone());
	              dphone.setDitchUuid(ditch.getUuid());
	              dphone.setEmpDitUuid(ditch.getEmpUuid());
	              String result = dphoneService.insert(dphone);
	              //修改微信库记录，先删后新增
	              boolean daoFlagDweixin=dweixinDao.deleteByDitchUuid(uuid);
	              Dweixin dweixin=new Dweixin();
	              dweixin.setWeixinNO(ditch.getWeixin());
	              dweixin.setDitchUuid(ditch.getUuid());
	              dweixin.setEmpDitUuid(ditch.getEmpUuid());
	              String result2=dweixinService.insert(dweixin);
					return uuid;
				} else {
					logger.error("修改不成功,dao层执行有出错地方,请联系管理员");
					return "修改不成功,dao层执行有出错地方,请联系管理员";
				}
			} else {
				String msg = "DitchServiceImpl update方法中的uuid为空，或格式不正确，请重新选择";
				System.out.println(msg);
				return msg;
			}
		}
	}

	@Override
	public Ditch getByUuid(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			return ditchDao.getByUuid(uuid);
		} else {
			System.out
					.println("DitchServiceImpl getByUuid方法中的uuid为空，或格式不正确，请联系管理员");
			return new Ditch();
		}
	}

	@Override
	public ArrayList<Ditch> getList() {
		// TODO Auto-generated method stub
		ArrayList<Ditch> ditchList = ditchDao.getList();
		for (int i = 0; i < ditchList.size(); i++) {
			Employee employee = employeeDao.getByUuid(ditchList.get(i)
					.getEmpUuid());
			ditchList.get(i).setEmpName(employee.getName());
		}
		return ditchList;
	}

	/* 
	 * @param
	 * @return
	 */
	@Override
	public String getonoff(Ditch ditch) {
		// TODO Auto-generated method stub
		String uuid = ditch.getUuid();
		if (uuid != null && uuid != "") {
			String oac = ditch.getOpenAndclose();
			boolean daoFlag = ditchDao.updateOnOff(uuid, oac);
			if (daoFlag) {
				return "操作成功";
			} else {
				logger.error("操作不成功,dao层执行有出错地方,请联系管理员");
				return "操作失败,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DitchServiceImpl getonoff方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

}
