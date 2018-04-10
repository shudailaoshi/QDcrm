/**
 * 
 */
package com.QDcrm.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import com.QDcrm.dao.DitchDao;
import com.QDcrm.dao.DphoneDao;
import com.QDcrm.dao.EmployeeDao;
import com.QDcrm.dao.impl.DitchDaoImpl;
import com.QDcrm.dao.impl.DphoneDaoImpl;
import com.QDcrm.dao.impl.EmployeeDaoImpl;
import com.QDcrm.model.Ditch;
import com.QDcrm.model.Dphone;
import com.QDcrm.model.Employee;
import com.QDcrm.model.Record;
import com.QDcrm.service.DphoneService;

/**
 * @author LiuXin
 * @date 2018-2-8 下午2:36:42
 * @version
 */
public class DphoneServiceImpl implements DphoneService {
	private DphoneDao dphoneDao = new DphoneDaoImpl();
	private DitchDao ditchDao=new DitchDaoImpl();
	private EmployeeDao employeeDao=new EmployeeDaoImpl();
	/*
	 * @param
	 * 
	 * @return
	 */
	@Override
	public String insert(Dphone dphone) {
		// TODO Auto-generated method stub
		dphone.setUuid(UUID.randomUUID().toString());
		System.out.println("^^在DphoneServiceImpl收到数据 ：" + dphone.toString()
				+ "收到结束!");
		boolean daoFlag = dphoneDao.insert(dphone);
		if (daoFlag) {
			return dphone.getUuid();
		} else {
			return "插入不成功,dao层执行有出错地方,请联系管理员";
		}
	}

	/*
	 * @param
	 * 
	 * @return
	 */
	@Override
	public String delete(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			boolean daoFlag = dphoneDao.delete(uuid);
			if (daoFlag) {
				return uuid;
			} else {
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DphoneServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	/*
	 * @param
	 * 
	 * @return
	 */
	@Override
	public String deleteByDitchUuid(String ditchUuid) {
		// TODO Auto-generated method stub
		if (ditchUuid != null && ditchUuid != "") {
			int num=dphoneDao.getListByDitchUuid(ditchUuid).size();
			boolean daoFlag = dphoneDao.deleteByDitchUuid(ditchUuid);
			if (daoFlag) {
				return ditchUuid + "渠道删除" +num+ "条";
			} else {
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DphoneServiceImpl deleteByDitchUuid方法中的ditchUuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	/*
	 * @param
	 * 
	 * @return
	 */
	@Override
	public ArrayList<Dphone> getList() {
		// TODO Auto-generated method stub
		ArrayList<Dphone> list=dphoneDao.getList();
		for (int i = 0; i < list.size(); i++) {
			String ditchUuid=list.get(i).getDitchUuid();
			String ditchName=ditchDao.getByUuid(ditchUuid).getName();
			Employee employee=employeeDao.getByUuid(list.get(i).getEmpDitUuid());
			list.get(i).setEmpDitName(employee.getName());
			list.get(i).setDitchName(ditchName);
		}
		return list;
	}

	/* 
	 * @param
	 * @return
	 */
	@Override
	public ArrayList<Dphone> getListByDitchUuid(String ditchUuid) {
		// TODO Auto-generated method stub
		if (ditchUuid != null && ditchUuid != "") {
			ArrayList<Dphone> dphoneList =dphoneDao.getListByDitchUuid(ditchUuid);
			for (int i = 0; i < dphoneList.size(); i++) {
				Ditch ditch = ditchDao.getByUuid(dphoneList.get(i).getDitchUuid());
				Employee employee=employeeDao.getByUuid(dphoneList.get(i).getEmpDitUuid());
				dphoneList.get(i).setEmpDitName(employee.getName());
				dphoneList.get(i).setDitchName(ditch.getName());
			}
			return dphoneList;
		} else {
			System.out
					.println("DphoneServiceImpl getListByDitchUuid方法中的ditchUuid为空，或格式不正确，请联系管理员");
			return new ArrayList<Dphone>();
		}
	}

	/* 
	 * @param
	 * @return
	 */
	@Override
	public ArrayList<Dphone> getListByPhoneNO(String phoneNO) {
		// TODO Auto-generated method stub
		if (phoneNO != null && phoneNO != "") {
			ArrayList<Dphone> dphoneList =dphoneDao.getListByPhoneNO(phoneNO);
			for (int i = 0; i < dphoneList.size(); i++) {
				Ditch ditch = ditchDao.getByUuid(dphoneList.get(i).getDitchUuid());
				Employee employee=employeeDao.getByUuid(dphoneList.get(i).getEmpDitUuid());
				dphoneList.get(i).setEmpDitName(employee.getName());
				dphoneList.get(i).setDitchName(ditch.getName());
			}
			return dphoneList;
		} else {
			System.out
					.println("DphoneServiceImpl getListByPhoneNO方法中的phoneNO为空，或格式不正确，请联系管理员");
			return new ArrayList<Dphone>();
		}
	}

}
