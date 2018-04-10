/**
 * 
 */
package com.QDcrm.query.service.impl;

import java.util.ArrayList;

import com.QDcrm.dao.EmployeeDao;
import com.QDcrm.dao.impl.EmployeeDaoImpl;
import com.QDcrm.model.Ditch;
import com.QDcrm.model.Employee;
import com.QDcrm.model.Record;
import com.QDcrm.query.dao.MyDitchDao;
import com.QDcrm.query.dao.impl.MyDitchDaoImpl;
import com.QDcrm.query.service.MyDitchService;

/**
 * @author LiuXin
 * @date 2018-2-1 下午2:51:38
 * @version
 */
public class MyDitchServiceImpl implements MyDitchService {
	MyDitchDao myDitchDao=new MyDitchDaoImpl();
	private EmployeeDao employeeDao = new EmployeeDaoImpl();
	/*
	 * @param String empUuid
	 * 
	 * @return ArrayList<Ditch>
	 */
	@Override
	public ArrayList<Ditch> getListByEmpUuid(String empUuid) {
		// TODO Auto-generated method stub
		if (empUuid != null && empUuid != "") {
			ArrayList<Ditch> ditchList =myDitchDao.getListByEmpUuid(empUuid);
			for (int i = 0; i < ditchList.size(); i++) {
				Employee employee= employeeDao.getByUuid(empUuid);
				ditchList.get(i).setEmpName(employee.getName());
			}
			return ditchList;
		} else {
			System.out
					.println("MyDitchServiceImpl getListByEmpUuid方法中的empUuid为空，或格式不正确，请联系管理员");
			return new ArrayList<Ditch>();
		}
	}

}
