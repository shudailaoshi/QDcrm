package com.QDcrm.dao.LogQDDao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.QDcrm.dao.LogQDDao.LogGXDao;
import com.QDcrm.model.LogGX;
import com.QDcrm.model.LogQD;
import com.QDcrm.model.Record;
import com.QDcrm.utility.DBUtility;

public class LogGXDaoImpl implements LogGXDao{

	private Connection connection;
	boolean daoFlag = false;
	
	public LogGXDaoImpl(){
		System.out.println("connection对象在LogGXDaoImpl连接!");
	}
	
	@Override
	public boolean delete(LogGX rc) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; //关闭数据库连接insert和update和delete用到
		connection = DBUtility.open();//打开数据库连接
        try {
			preparedStatement = connection
			  .prepareStatement("DELETE FROM t_loggx WHERE uuid = ? ");
			preparedStatement.setString(1, rc.getUuid());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行LogGXDaoImpl中的删除delete");
		      daoFlag = true;
		      return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("^^在执行LogGXDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
		    daoFlag = false;
		    return daoFlag;
		}finally{
	      ResultSet rs = null; 
	      DBUtility.close(rs, preparedStatement, connection);   
	     }//finally关闭jdbc与数据库连接  
	}

	@Override
	public ArrayList<LogGX> getList() {
		// TODO Auto-generated method stub
		ArrayList<LogGX> logQDList = new ArrayList<LogGX>();
		 Statement statement = null;//finally关闭数据库连接  
		    ResultSet rs = null;//关闭数据库连接get和getlist会用到
		    try {
		      connection = DBUtility.open();//打开数据库连接
		         statement = connection.createStatement();
//		         rs = statement.executeQuery("SELECT * FROM t_logqd");
		         rs = statement.executeQuery("select * from t_loggx");
		        while (rs.next()) {
		        	LogGX logQD = new LogGX();
		          logQD.setUuid(rs.getString("uuid"));
		          logQD.setUserUuid(rs.getString("userUuid"));
		          logQD.setUserName(rs.getString("userName"));             
		          logQD.setTableName(rs.getString("tableName"));
		          logQD.setTableNameChina(rs.getString("tableNameChina"));
		          logQD.setDataUuid(rs.getString("dataUuid"));
		          logQD.setDataName(rs.getString("dataName"));
		          logQD.setUserAction(rs.getString("userAction"));
		          logQD.setUpdateTime(rs.getString("updateTime"));
		          logQD.setDataGxUuid(rs.getString("dataGxUuid"));
		          logQD.setDataGxChina(rs.getString("dataGxChina"));
		          
		          logQDList.add(logQD);
		        }
		        
		        return logQDList;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("LogGXDaoImpl的getList查询失败");
		        LogGX LogQDX = new LogGX();
		        LogQDX.setUuid("LogGXDaoImpl的getList失败返回的uuid");
		        ArrayList<LogGX> logQDListX = new ArrayList<LogGX>();
		        logQDListX.add(LogQDX);
		        return logQDListX;
		    }finally{   
		      DBUtility.close(rs, statement, connection);   
		     }//finally关闭jdbc与数据库连接
	}

	@Override
	public boolean insert(LogGX gx) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; //关闭数据库连接insert和update和delete用到
	    try {
	      connection = DBUtility.open();//打开数据库连接
	       preparedStatement = connection
	          .prepareStatement("insert into t_loggx(uuid,userUuid,userName,tableName,tableNameChina,dataUuid,dataName,userAction,updateTime,dataGxUuid,dataGxChina) values (?,?,?,?,?,?,?,?,?,?,?)");
	      // Parameters start with 1
	      preparedStatement.setString(1, gx.getUuid());
	      preparedStatement.setString(2, gx.getUserUuid());
	      preparedStatement.setString(3, gx.getUserName());
	      preparedStatement.setString(4, gx.getTableName());
	      preparedStatement.setString(5, gx.getTableNameChina());
	      preparedStatement.setString(6, gx.getDataUuid());
	      preparedStatement.setString(7, gx.getDataName());
	      
	      preparedStatement.setString(8, gx.getUserAction());
	      preparedStatement.setString(9, gx.getUpdateTime());
	      preparedStatement.setString(10, gx.getDataGxUuid());
	      preparedStatement.setString(11, gx.getDataGxChina());
	      preparedStatement.executeUpdate();

	      System.out.println("^^在执行LogGXDaoImpl中的insert添加");
	      daoFlag = true;
	      return daoFlag;
	    } catch (SQLException e) {
	      System.out.println("^^在执行LogGXDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
	      e.printStackTrace();
	      daoFlag = false;
	      return daoFlag;
	    }finally{
	      ResultSet rs = null; 
	      DBUtility.close(rs, preparedStatement, connection);   
	     }//finally关闭jdbc与数据库连接  
	}

	@Override
	public boolean deleteBatch(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; //关闭数据库连接insert和update和delete用到
		connection = DBUtility.open();//打开数据库连接
        try {
			preparedStatement = connection
			  .prepareStatement("DELETE FROM t_loggx WHERE uuid = ? ");
			preparedStatement.setString(1, uuid);
			preparedStatement.executeUpdate();
			System.out.println("^^在执行LogGXDaoImpl中的删除delete");
		      daoFlag = true;
		      return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("^^在执行LogGXDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
		    daoFlag = false;
		    return daoFlag;
		}finally{
	      ResultSet rs = null; 
	      DBUtility.close(rs, preparedStatement, connection);   
	     }//finally关闭jdbc与数据库连接  
	}


}
