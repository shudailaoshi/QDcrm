/**
 * 
 */
package com.QDcrm.service.impl;

import java.util.ArrayList;
import java.util.UUID;

import com.QDcrm.dao.DitchDao;
import com.QDcrm.dao.DweixinDao;
import com.QDcrm.dao.EmployeeDao;
import com.QDcrm.dao.impl.DitchDaoImpl;
import com.QDcrm.dao.impl.DweixinDaoImpl;
import com.QDcrm.dao.impl.EmployeeDaoImpl;
import com.QDcrm.model.Ditch;
import com.QDcrm.model.Dphone;
import com.QDcrm.model.Dweixin;
import com.QDcrm.model.Employee;
import com.QDcrm.service.DweixinService;

/**
 * @author LiuXin
 *@date 2018-2-8 下午4:42:06
 *@version 
 */
public class DweixinServiceImpl implements DweixinService {
	private DitchDao ditchDao=new DitchDaoImpl();
	private DweixinDao dweixinDao=new DweixinDaoImpl();
	private EmployeeDao employeeDao=new EmployeeDaoImpl();
	/* 
	 * @param
	 * @return
	 */
	@Override
	public String insert(Dweixin dweixin) {
		// TODO Auto-generated method stub
		dweixin.setUuid(UUID.randomUUID().toString());
		System.out.println("^^在DweixinServiceImpl收到数据 ：" + dweixin.toString()
				+ "收到结束!");
		boolean daoFlag = dweixinDao.insert(dweixin);
		if (daoFlag) {
			return dweixin.getUuid();
		} else {
			return "插入不成功,dao层执行有出错地方,请联系管理员";
		}
	}
	/* 
	 * @param
	 * @return
	 */
	@Override
	public String delete(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			boolean daoFlag = dweixinDao.delete(uuid);
			if (daoFlag) {
				return uuid;
			} else {
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DweixinServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}
	/* 
	 * @param
	 * @return
	 */
	@Override
	public String deleteByDitchUuid(String ditchUuid) {
		// TODO Auto-generated method stub
		if (ditchUuid != null && ditchUuid != "") {
			int num=dweixinDao.getListByDitchUuid(ditchUuid).size();
			boolean daoFlag = dweixinDao.deleteByDitchUuid(ditchUuid);
			if (daoFlag) {
				return ditchUuid + "渠道删除" +num+ "条";
			} else {
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DweixinServiceImpl deleteByDitchUuid方法中的ditchUuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}
	/* 
	 * @param
	 * @return
	 */
	@Override
	public ArrayList<Dweixin> getList() {
		// TODO Auto-generated method stub
		ArrayList<Dweixin> list=dweixinDao.getList();
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
	public ArrayList<Dweixin> getListByDitchUuid(String ditchUuid) {
		// TODO Auto-generated method stub
		if (ditchUuid != null && ditchUuid != "") {
			ArrayList<Dweixin> dweixinList =dweixinDao.getListByDitchUuid(ditchUuid);
			for (int i = 0; i < dweixinList.size(); i++) {
				Ditch ditch = ditchDao.getByUuid(dweixinList.get(i).getDitchUuid());
				Employee employee=employeeDao.getByUuid(dweixinList.get(i).getEmpDitUuid());
				dweixinList.get(i).setEmpDitName(employee.getName());
				dweixinList.get(i).setDitchName(ditch.getName());
			}
			return dweixinList;
		} else {
			System.out
					.println("DweixinServiceImpl getListByDitchUuid方法中的ditchUuid为空，或格式不正确，请联系管理员");
			return new ArrayList<Dweixin>();
		}
	}
	/* 
	 * @param
	 * @return
	 */
	@Override
	public ArrayList<Dweixin> getListByWeixinNO(String weixinNO) {
		// TODO Auto-generated method stub
		if (weixinNO != null && weixinNO != "") {
			ArrayList<Dweixin> dweixinList =dweixinDao.getListByWeixinNO(weixinNO);
			for (int i = 0; i < dweixinList.size(); i++) {
				Ditch ditch = ditchDao.getByUuid(dweixinList.get(i).getDitchUuid());
				Employee employee=employeeDao.getByUuid(dweixinList.get(i).getEmpDitUuid());
				dweixinList.get(i).setEmpDitName(employee.getName());
				dweixinList.get(i).setDitchName(ditch.getName());
			}
			return dweixinList;
		} else {
			System.out
					.println("DweixinServiceImpl getListByWeixinNO方法中的weixinNO为空，或格式不正确，请联系管理员");
			return new ArrayList<Dweixin>();
		}
	}

}
