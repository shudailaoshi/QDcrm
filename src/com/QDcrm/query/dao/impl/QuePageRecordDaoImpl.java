/**
 * 
 */
package com.QDcrm.query.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.QDcrm.model.Record;
import com.QDcrm.query.dao.QuePageRecordDao;
import com.QDcrm.utility.DBUtility;

/**
 * @author LiuXin
 * @date 2018-1-31 下午2:53:06
 * @version 跟踪记录表分页查询
 */
public class QuePageRecordDaoImpl implements QuePageRecordDao {
	private Connection connection = null;

	public QuePageRecordDaoImpl() {
		System.out.println("connection在QuePageRecordDaoImpl中连接");
	}

	/*
	 * @param String ditchUuid, int currentPage, int maxResult
	 * 
	 * @return ArrayList<Record>
	 */
	@Override
	public ArrayList<Record> getPageByDitchUuid(String ditchUuid,
			int currentPage, int maxResult) {
		// TODO Auto-generated method stub
		ArrayList<Record> recordList = new ArrayList<Record>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_record where ditchUuid='"
							+ ditchUuid + "' order by recordDate desc LIMIT "
							+ (currentPage - 1) * maxResult + "," + maxResult
							+ "");
			while (rs.next()) {
				Record record = new Record();
				record.setUuid(rs.getString("uuid"));
				record.setDitchUuid(rs.getString("ditchUuid"));
				record.setRecordDate(rs.getString("recordDate"));
				record.setRemarkText(rs.getString("remarkText"));
				record.setCreatePeople(rs.getString("createPeople"));
				recordList.add(record);
			}
			return recordList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("QuePageRecordDaoImpl的分页查询跟踪记录表列表失败");
			Record record = new Record();
			record.setDitchUuid("RecordDaoImpl查询失败返回的ditchUuid");
			ArrayList<Record> listRecord = new ArrayList<Record>();
			listRecord.add(record);
			return listRecord;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
	}

}
