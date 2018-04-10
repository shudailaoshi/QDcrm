/**
 * 
 */
package com.QDcrm.query.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.QDcrm.model.Ditch;
import com.QDcrm.model.Record;
import com.QDcrm.query.dao.MyDitchDao;
import com.QDcrm.utility.DBUtility;

/**
 * @author LiuXin
 * @date 2018-2-1 下午2:41:05
 * @version 我的渠道列表查询
 */
public class MyDitchDaoImpl implements MyDitchDao {
	private Connection connection;

	public MyDitchDaoImpl() {
		System.out.println("connection对象在MyDitchDaoImpl中连接");
	}

	/*
	 * @param String empUuid
	 * 
	 * @return ArrayList<Ditch>
	 */
	@Override
	public ArrayList<Ditch> getListByEmpUuid(String empUuid) {
		// TODO Auto-generated method stub
		ArrayList<Ditch> ditchList = new ArrayList<Ditch>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_ditch where empUuid='"
							+ empUuid + "'");
			while (rs.next()) {
				Ditch ditch = new Ditch();
				ditch.setCompany(rs.getString("company"));
				ditch.setEmpUuid(rs.getString("empUuid"));
				ditch.setJob(rs.getString("job"));
				ditch.setName(rs.getString("name"));
				ditch.setOpenAndclose(rs.getString("openAndclose"));
				ditch.setPhone(rs.getString("phone"));
				ditch.setRank(rs.getString("rank"));
				ditch.setRemark(rs.getString("remark"));
				ditch.setSex(rs.getString("sex"));
				ditch.setSource(rs.getString("source"));
				ditch.setUuid(rs.getString("uuid"));
				ditch.setWeixin(rs.getString("weixin"));
				ditch.setMoneyRate(rs.getString("moneyRate"));
				ditch.setCreateDate(rs.getString("createDate"));
                ditch.setModifyDate(rs.getString("modifyDate"));
				ditchList.add(ditch);
			}
			return ditchList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MyDitchDaoImpl的根据empUuid查询渠道列表失败");
			Ditch ditch = new Ditch();
			ditch.setEmpUuid("MyDitchDaoImpl查询失败返回的empUuid");
			ArrayList<Ditch> listDitch = new ArrayList<Ditch>();
			listDitch.add(ditch);
			return listDitch;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
	}

}
