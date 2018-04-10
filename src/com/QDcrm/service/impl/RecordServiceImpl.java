/**
 * 
 */
package com.QDcrm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.QDcrm.controller.RecordController;
import com.QDcrm.dao.DitchDao;
import com.QDcrm.dao.RecordDao;
import com.QDcrm.dao.LogQDDao.LogGXDao;
import com.QDcrm.dao.LogQDDao.impl.LogGXDaoImpl;
import com.QDcrm.dao.impl.DitchDaoImpl;
import com.QDcrm.dao.impl.RecordDaoImpl;
import com.QDcrm.model.Ditch;
import com.QDcrm.model.LogGX;
import com.QDcrm.model.Record;
import com.QDcrm.service.RecordService;

/**
 * @author LiuXin
 * @date 2018-1-30 下午4:37:51
 * @version 跟踪记录表service实现类
 */
public class RecordServiceImpl implements RecordService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.QDcrm.service.RecordService#insert(com.QDcrm.model.Record)
	 */
	private LogGXDao lgg = new LogGXDaoImpl();
	private RecordDao recordDao = new RecordDaoImpl();
	DitchDao ditchDao = new DitchDaoImpl();
	Logger logger = Logger.getLogger(RecordServiceImpl.class);
	@Override
	public String insert(Record record) {
		// TODO Auto-generated method stub
	  
		Ditch ditch = new Ditch();
		 ditch = ditchDao.getByUuid(record.getDitchUuid());
		if (ditch.getUuid() != null || ditch.getUuid() != "") {
			record.setUuid(UUID.randomUUID().toString());
			System.out.println("^^在RecordServiceImpl收到数据 ：" + record.toString()
					+ "收到结束!");
			boolean daoFlag = recordDao.insert(record);
			LogGX lg = new LogGX();
	        lg.setUuid(UUID.randomUUID().toString());
	        lg.setUserUuid(record.getUserUuid());
	        lg.setUserName(record.getUserName());
	        lg.setTableName("t_record");
	        lg.setTableNameChina("跟踪记录表");
	        lg.setDataUuid(record.getDitchUuid());
	        String ditName = "初始值***";
	         ditName = ditch.getName();
	        lg.setDataName(ditName);
	        lg.setUserAction("新增");
	        Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String da = sdf.format(date);
	        lg.setUpdateTime(da);
	        lg.setDataGxUuid(record.getUuid());
	        lg.setDataGxChina(record.getRemarkText());
	        lgg.insert(lg);
			if (daoFlag) {
				Date dateModify = new Date();
	            SimpleDateFormat sdfModify = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            String daModify = sdfModify.format(dateModify);
	        	recordDao.updateModifyDate(daModify,record.getDitchUuid());
				return record.getUuid();
			} else {
				logger.error("插入不成功,dao层执行有出错地方,请联系管理员");
				return "插入不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			return "该渠道不存在";
		}

	}

	@Override
	public String delete(Record re) {
		// TODO Auto-generated method stub
		if (re.getUuid() != null && re.getUuid() != "") {
			LogGX lg = new LogGX();
	        lg.setUuid(UUID.randomUUID().toString());
	        lg.setUserUuid(re.getUserUuid());
	        lg.setUserName(re.getUserName());
	        lg.setTableName("t_record");
	        lg.setTableNameChina("跟踪记录表");
	        List<Record> rec=recordDao.findIdShow(re.getUuid());
	        lg.setDataUuid(rec.get(0).getDitchUuid());
	        Ditch yx = ditchDao.getByUuid(rec.get(0).getDitchUuid());
	        lg.setDataName(yx.getName());
	        lg.setUserAction("删除");
	        Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String da = sdf.format(date);
	        lg.setUpdateTime(da);
	        lg.setDataGxUuid(rec.get(0).getUuid());
	        lg.setDataGxChina(rec.get(0).getRemarkText());
	        lgg.insert(lg);
			boolean daoFlag = recordDao.delete(re.getUuid());
			if (daoFlag) {
				return re.getUuid();
			} else {
				logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "RecordServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}

	}

	@Override
	public String deleteByDitchUuid(Record record) {
		// TODO Auto-generated method stub
		if (record.getDitchUuid() != null && record.getDitchUuid() != "") {
			LogGX lg = new LogGX();
	        lg.setUuid(UUID.randomUUID().toString());
	        lg.setUserUuid(record.getUserUuid());
	        lg.setUserName(record.getUserName());
	        lg.setTableName("t_record");
	        lg.setTableNameChina("跟踪记录表");
	        List<Record> rec=recordDao.findIdShow(record.getDitchUuid());
	        lg.setDataUuid(rec.get(0).getDitchUuid());
	        Ditch yx = ditchDao.getByUuid(rec.get(0).getDitchUuid());
	        lg.setDataName(yx.getName());
	        lg.setUserAction("删除");
	        Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String da = sdf.format(date);
	        lg.setUpdateTime(da);
	        lg.setDataGxUuid(rec.get(0).getUuid());
	        lg.setDataGxChina(rec.get(0).getRemarkText());
	        lgg.insert(lg);
			boolean daoFlag = recordDao.deleteByDitchUuid(record.getDitchUuid());
			if (daoFlag) {
				return record.getDitchUuid();
			} else {
				logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "RecordServiceImpl deleteByDitchUuid方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	@Override
	public String update(Record record) {
		// TODO Auto-generated method stub

		String uuid = record.getUuid();
		if (uuid != null && uuid != "") {
			boolean daoFlag = recordDao.update(record);
			LogGX lg = new LogGX();
	        lg.setUuid(UUID.randomUUID().toString());
	        lg.setUserUuid(record.getUserUuid());
	        lg.setUserName(record.getUserName());
	        lg.setTableName("t_record");
	        lg.setTableNameChina("跟踪记录表");
	        List<Record> rec=recordDao.findIdShow(record.getUuid());
	        lg.setDataUuid(rec.get(0).getDitchUuid());
	        Ditch yx = ditchDao.getByUuid(rec.get(0).getDitchUuid());
	        lg.setDataName(yx.getName());
	        lg.setUserAction("编辑");
	        Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String da = sdf.format(date);
	        lg.setUpdateTime(da);
	        lg.setDataGxUuid(record.getUuid());
	        lg.setDataGxChina(record.getRemarkText());
	        lgg.insert(lg);
			if (daoFlag) {
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

	@Override
	public Record getByUuid(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			return recordDao.getByUuid(uuid);
		} else {
			System.out
					.println("RecordServiceImpl getByUuid方法中的uuid为空，或格式不正确，请联系管理员");
			return new Record();
		}
	}

	@Override
	public ArrayList<Record> getList() {
		// TODO Auto-generated method stub

		ArrayList<Record> recList = recordDao.getList();
		for (int i = 0; i < recList.size(); i++) {
//			System.out.println("ditchUuid================"
//					+ recList.get(i).getDitchUuid());
			Ditch ditch = ditchDao.getByUuid(recList.get(i).getDitchUuid());
			//System.out.println("ditchName==============" + ditch.getName());
			recList.get(i).setDitchName(ditch.getName());
		}
		return recList;
	}

	@Override
	public ArrayList<Record> getListByDitchUuid(String ditchUuid) {
		// TODO Auto-generated method stub
		if (ditchUuid != null && ditchUuid != "") {
			ArrayList<Record> recList =recordDao.getListByDitchUuid(ditchUuid);
			for (int i = 0; i < recList.size(); i++) {
				Ditch ditch = ditchDao.getByUuid(recList.get(i).getDitchUuid());
				recList.get(i).setDitchName(ditch.getName());
			}
			return recList;
		} else {
			System.out
					.println("RecordServiceImpl getListByDitchUuid方法中的ditchUuid为空，或格式不正确，请联系管理员");
			return new ArrayList<Record>();
		}
	}

	/* 
	 * @param
	 * @return
	 */
	@Override
	public String getonoff(Record record) {
		// TODO Auto-generated method stub
		String uuid = record.getUuid();
		if (uuid != null && uuid != "") {
			String oac = record.getOpenAndclose();
			boolean daoFlag = recordDao.updateOnOff(uuid, oac);
			if (daoFlag) {
				return "操作成功";
			} else {
				logger.error("操作不成功,dao层执行有出错地方,请联系管理员");
				return "操作失败,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "RecordServiceImpl getonoff方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

}
