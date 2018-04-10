/**
 * 
 */
package com.QDcrm.query.service.impl;

import java.util.ArrayList;

import com.QDcrm.dao.DitchDao;
import com.QDcrm.dao.impl.DitchDaoImpl;
import com.QDcrm.model.Ditch;
import com.QDcrm.model.Record;
import com.QDcrm.query.dao.QuePageRecordDao;
import com.QDcrm.query.dao.impl.QuePageRecordDaoImpl;
import com.QDcrm.query.service.QuePageRecordService;

/**
 * @author LiuXin
 * @date 2018-1-31 下午3:42:45
 * @version 跟踪记录表分页查询
 */
public class QuePageRecordServiceImpl implements QuePageRecordService {
	QuePageRecordDao qprd = new QuePageRecordDaoImpl();
	DitchDao dd = new DitchDaoImpl();

	/*
	 * @param
	 * 
	 * @return
	 */
	@Override
	public ArrayList<Record> getPageByDitchUuid(String ditchUuid,
			int currentPage, int maxResult) {
		// TODO Auto-generated method stub

		if (ditchUuid != null && ditchUuid != "" && currentPage > 0
				&& maxResult > 0) {
			ArrayList<Record> recList = qprd.getPageByDitchUuid(ditchUuid,
					currentPage, maxResult);
			for (int i = 0; i < recList.size(); i++) {
				Ditch ditch = dd.getByUuid(ditchUuid);
				recList.get(i).setDitchName(ditch.getName());
			}
			return recList;
		} else {
			ArrayList<Record> list = new ArrayList<Record>();
			Record record = new Record();
			record.setUuid("传入数据有问题,请检查");
			list.add(record);
			System.out
					.println("QuePageRecordServiceImpl中的getPageByDitchUuid方法传入的数据有问题,请联系管理员");
			return list;
		}
	}

}
