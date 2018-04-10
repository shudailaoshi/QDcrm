/**
 * 
 */
package com.QDcrm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.QDcrm.dao.RecordDao;
import com.QDcrm.model.Ditch;
import com.QDcrm.model.Record;
import com.QDcrm.utility.DBUtility;
/**
 * @author LiuXin
 * @date 2018-1-30 下午4:27:35
 * @version 跟踪记录表dao实现类
 */
public class RecordDaoImpl implements RecordDao {

	private Connection connection;
	boolean daoFlag = false;

	public RecordDaoImpl() {
		System.out.println("connection对象在RecordDaoImpl中连接");
	}
	
	@Override
	public boolean insert(Record record) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();

		try {
			preparedStatement = connection
					.prepareStatement("insert into t_record (ditchUuid,recordDate,remarkText,uuid,createPeople) values (?,?,?,?,?)");
			preparedStatement.setString(1, record.getDitchUuid());
			preparedStatement.setString(2, record.getRecordDate());
			preparedStatement.setString(3, record.getRemarkText());
			preparedStatement.setString(4, record.getUuid());
			preparedStatement.setString(5, record.getUserName());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行RecordDaoImpl中的添加insert");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("^^在执行RecordDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
			e.printStackTrace();
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null;
		connection = DBUtility.open();
		try {
			PSdelete = connection
					.prepareStatement("delete from t_record where uuid='"
							+ uuid + "'");
			PSdelete.executeUpdate();
			System.out.println("^^在执行RecordDaoImpl中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("^^在执行RecordDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}
	}

	@Override
	public boolean deleteByDitchUuid(String ditchUuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null;
		connection = DBUtility.open();
		try {
			PSdelete = connection
					.prepareStatement("delete from t_record where ditchUuid='"
							+ ditchUuid + "'");
			PSdelete.executeUpdate();
			System.out.println("^^在执行DitchDaoImpl中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行RecordDaoImpl中deleteByDitchUuid,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}
	}

	@Override
	public boolean update(Record record) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();

		try {
			preparedStatement = connection
					.prepareStatement("update t_record set recordDate=?,remarkText=? where uuid=?");
			preparedStatement.setString(1, record.getRecordDate());
			preparedStatement.setString(2, record.getRemarkText());
			preparedStatement.setString(3, record.getUuid());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行RecordDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("^^在执行RecordDaoImpl中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public Record getByUuid(String uuid) {
		// TODO Auto-generated method stub
		Record recordResult = new Record();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_record where uuid='"
					+ uuid + "'");
			while (rs.next()) {
				Record record = new Record();
				record.setUuid(rs.getString("uuid"));
				record.setDitchUuid(rs.getString("ditchUuid"));
				record.setRecordDate(rs.getString("recordDate"));
				record.setRemarkText(rs.getString("remarkText"));
				recordResult = record;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("RecordDaoImpl的查询单个部门失败");
			Record record = new Record();
			record.setUuid("DitchDaoImpl失败返回的uuid");
			return record;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
		return recordResult;

	}

	@Override
	public ArrayList<Record> getList() {
		// TODO Auto-generated method stub
		ArrayList<Record> recordList = new ArrayList<Record>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_record");
			while (rs.next()) {
				Record record = new Record();
				record.setUuid(rs.getString("uuid"));
				record.setDitchUuid(rs.getString("ditchUuid"));
				record.setRecordDate(rs.getString("recordDate"));
				record.setRemarkText(rs.getString("remarkText"));
				recordList.add(record);
			}
			return recordList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("RecordDaoImpl的查询跟踪记录表列表失败");
			Record record = new Record();
			record.setUuid("RecordDaoImpl查询失败返回的uuid");
			ArrayList<Record> listRecord = new ArrayList<Record>();
			listRecord.add(record);
			return listRecord;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
	}

	@Override
	public ArrayList<Record> getListByDitchUuid(String ditchUuid) {
		// TODO Auto-generated method stub
		ArrayList<Record> recordList = new ArrayList<Record>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_record where ditchUuid='"+ditchUuid+"'");
			while (rs.next()) {
				Record record = new Record();
				record.setUuid(rs.getString("uuid"));
				record.setDitchUuid(rs.getString("ditchUuid"));
				record.setRecordDate(rs.getString("recordDate"));
				record.setRemarkText(rs.getString("remarkText"));
				recordList.add(record);
			}
			return recordList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("RecordDaoImpl的查询跟踪记录表列表失败");
			Record record = new Record();
			record.setDitchUuid("RecordDaoImpl查询失败返回的uuid");
			ArrayList<Record> listRecord = new ArrayList<Record>();
			listRecord.add(record);
			return listRecord;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
	}

	/* 
	 * @param
	 * @return
	 */
	@Override
	public boolean updateOnOff(String uuid, String oac) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		connection = DBUtility.open();
		try {
			ps=connection.prepareStatement("update t_record set openAndclose='"+oac+"' where uuid='"+uuid+"'");
			ps.executeUpdate();
			System.out.println("在执行RecordDaoImpl中的修改update开关");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行RecordDaoImpl中updateOnOff,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, ps, connection);
		}
	}

	@Override
	public List<Record> findIdShow(String uuid) {
		// TODO Auto-generated method stub
		ArrayList<Record> yxstuList = new ArrayList<Record>();
	    Statement statement = null;//finally关闭数据库连接  
	    ResultSet rs = null;//关闭数据库连接get和getlist会用到
	    try {
	      connection = DBUtility.open();//打开数据库连接
	         statement = connection.createStatement();
	         rs = statement.executeQuery("SELECT * FROM t_record WHERE uuid='"+uuid+"'");
	        while (rs.next()) {
	        	Record record = new Record();
	        	record.setUuid(rs.getString("uuid"));
	            record.setDitchUuid(rs.getString("ditchUuid"));
	            record.setRecordDate(rs.getString("recordDate"));
	            record.setRemarkText(rs.getString("remarkText"));
	          yxstuList.add(record);
	        }
	        
	        return yxstuList;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("getList查询失败");
	        Record X = new Record();
	        X.setUuid("EmployeeDaoImpl的getList失败返回的uuid");
	        ArrayList<Record> XList = new ArrayList<Record>();
	        XList.add(X);
	        return XList;
	    }finally{   
	      DBUtility.close(rs, statement, connection);   
	     }//finally关闭jdbc与数据库连接
	}

	@Override
	public boolean updateModifyDate(String modifyDate, String uuid) {
		PreparedStatement preparedStatement = null;
	    connection = DBUtility.open();

	    try {
	        preparedStatement = connection
	                .prepareStatement("UPDATE t_ditch SET modifyDate='"+modifyDate+"' WHERE uuid='"+uuid+"'");
	        preparedStatement.executeUpdate();
	        System.out.println("^^在执行RecordDaoImpl中的添加updateModifyDate");
	        daoFlag = true;
	        return daoFlag;
	    } catch (SQLException e) {
	        // TODO Auto-generated catch block
	        System.out.println("^^在执行RecordDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
	        e.printStackTrace();
	        daoFlag = false;
	        return daoFlag;
	    } finally {
	        ResultSet rs = null;
	        DBUtility.close(rs, preparedStatement, connection);
	    }
	}
	
}
