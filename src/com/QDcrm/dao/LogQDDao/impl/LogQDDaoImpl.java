package com.QDcrm.dao.LogQDDao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.QDcrm.dao.LogQDDao.LogQDDao;
import com.QDcrm.model.LogQD;
import com.QDcrm.utility.DBUtility;

public class LogQDDaoImpl implements LogQDDao {
	
	private Connection connection;
	boolean daoFlag = false;
	
	public LogQDDaoImpl(){
		System.out.println("connection对象在LogQDDaoImpl连接!");
	}

	@Override
	public boolean delete(String uuid) {
		PreparedStatement preparedStatement = null; //关闭数据库连接insert和update和delete用到
		connection = DBUtility.open();//打开数据库连接
        try {
			preparedStatement = connection
			  .prepareStatement("DELETE FROM t_logqd WHERE uuid = ? ");
			preparedStatement.setString(1, uuid);
			preparedStatement.executeUpdate();
			System.out.println("^^在执行LogQDDaoImpl中的删除delete");
		      daoFlag = true;
		      return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("^^在执行LogQDDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
		    daoFlag = false;
		    return daoFlag;
		}finally{
	      ResultSet rs = null; 
	      DBUtility.close(rs, preparedStatement, connection);   
	     }//finally关闭jdbc与数据库连接  
	}// end method delete

	@Override
	public ArrayList<LogQD> getList() {
		 ArrayList<LogQD> logQDList = new ArrayList<LogQD>();
		 Statement statement = null;//finally关闭数据库连接  
		    ResultSet rs = null;//关闭数据库连接get和getlist会用到
		    try {
		      connection = DBUtility.open();//打开数据库连接
		         statement = connection.createStatement();
//		         rs = statement.executeQuery("SELECT * FROM t_logqd");
		         rs = statement.executeQuery("select * from t_logqd");
		        while (rs.next()) {
		          LogQD logQD = new LogQD();
		          logQD.setUuid(rs.getString("uuid"));
		          logQD.setUserUuid(rs.getString("userUuid"));
		          logQD.setUserName(rs.getString("userName"));             
		          logQD.setTableName(rs.getString("tableName"));
		          logQD.setTableNameChina(rs.getString("tableNameChina"));
		          logQD.setDataUuid(rs.getString("dataUuid"));
		          logQD.setDataName(rs.getString("dataName"));
		          logQD.setUserAction(rs.getString("userAction"));
		          logQD.setUpdateTime(rs.getString("updateTime"));
		          logQD.setDataGxChina(rs.getString("dataGxChina"));
		          
		          logQDList.add(logQD);
		        }
		        
		        return logQDList;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("LogQDDaoImpl的getList查询失败");
		        LogQD LogQDX = new LogQD();
		        LogQDX.setUuid("LogQDDaoImpl的getList失败返回的uuid");
		        ArrayList<LogQD> logQDListX = new ArrayList<LogQD>();
		        logQDListX.add(LogQDX);
		        return logQDListX;
		    }finally{   
		      DBUtility.close(rs, statement, connection);   
		     }//finally关闭jdbc与数据库连接
	}

	@Override
	public boolean insert(LogQD logd) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; //关闭数据库连接insert和update和delete用到
	    try {
	      connection = DBUtility.open();//打开数据库连接
	       preparedStatement = connection
	          .prepareStatement("insert into t_logqd(uuid,userUuid,userName,tableName,tableNameChina,dataUuid,dataName,userAction,updateTime,dataGxChina) values (?,?,?,?,?,?,?,?,?,?)");
	      // Parameters start with 1
	      preparedStatement.setString(1, logd.getUuid());
	      preparedStatement.setString(2, logd.getUserUuid());
	      preparedStatement.setString(3, logd.getUserName());
	      preparedStatement.setString(4, logd.getTableName());
	      preparedStatement.setString(5, logd.getTableNameChina());
	      preparedStatement.setString(6, logd.getDataUuid());
	      preparedStatement.setString(7, logd.getDataName());
	      
	      preparedStatement.setString(8, logd.getUserAction());
	      preparedStatement.setString(9, logd.getUpdateTime());
	      
	      preparedStatement.setString(10, logd.getDataGxChina());
	      preparedStatement.executeUpdate();

	      System.out.println("^^在执行LogQDDaoImpl中的insert添加");
	      daoFlag = true;
	      return daoFlag;
	    } catch (SQLException e) {
	      System.out.println("^^在执行LogQDDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
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
		PreparedStatement preparedStatement = null; //关闭数据库连接insert和update和delete用到
		connection = DBUtility.open();//打开数据库连接
        try {
			preparedStatement = connection
			  .prepareStatement("DELETE FROM t_logqd WHERE uuid = ? ");
			preparedStatement.setString(1, uuid);
			preparedStatement.executeUpdate();
			System.out.println("^^在执行LogQDDaoImpl中的删除delete");
		      daoFlag = true;
		      return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("^^在执行LogQDDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
		    daoFlag = false;
		    return daoFlag;
		}finally{
	      ResultSet rs = null; 
	      DBUtility.close(rs, preparedStatement, connection);   
	     }//finally关闭jdbc与数据库连接  
	}


}
