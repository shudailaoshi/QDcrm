package com.QDcrm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.QDcrm.dao.DitchDao;
import com.QDcrm.model.Ditch;
import com.QDcrm.utility.DBUtility;

/*
 * @author 刘鑫
 * @date 2018-1-29 20：19
 */
public class DitchDaoImpl implements DitchDao {
	private Connection connection;
	boolean daoFlag = false;

	public DitchDaoImpl() {
		System.out.println("connection对象在DitchDaoImpl连接!");
	}

	@Override
	public boolean insert(Ditch ditch) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		try {
			preparedStatement = connection
					.prepareStatement("insert into t_ditch(name,sex,phone,weixin,company,job,empUuid,rank,source,remark,uuid,openAndclose,moneyRate,createDate) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, ditch.getName());
			preparedStatement.setString(2, ditch.getSex());
			preparedStatement.setString(3, ditch.getPhone());
			preparedStatement.setString(4, ditch.getWeixin());
			preparedStatement.setString(5, ditch.getCompany());
			preparedStatement.setString(6, ditch.getJob());
			preparedStatement.setString(7, ditch.getEmpUuid());
			preparedStatement.setString(8, ditch.getRank());
			preparedStatement.setString(9, ditch.getSource());
			preparedStatement.setString(10, ditch.getRemark());
			preparedStatement.setString(11, ditch.getUuid());
			preparedStatement.setString(12, "open");
			preparedStatement.setString(13, ditch.getMoneyRate());
			Date dateModify = new Date();
		    SimpleDateFormat sdfModify = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String daModify = sdfModify.format(dateModify);
		    preparedStatement.setString(14, daModify);
			preparedStatement.executeUpdate();
			System.out.println("^^在执行DitchDaoImpl中的添加insert");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("^^在执行DitchDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
			e.printStackTrace();
			daoFlag = false;
			return daoFlag;

		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}

	}

	@Override
	public List<Ditch> getDitByName(Ditch ditch) {
		// TODO Auto-generated method stub
		List<Ditch> ditchList = new ArrayList<Ditch>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_ditch where name='"
					+ ditch.getName() + "'");
			while (rs.next()) {
				Ditch dit = new Ditch();
				dit.setUuid(rs.getString("uuid"));
				ditchList.add(dit);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DepartmentDaoImpl中的getdMByName查询失败");
		} finally {
			DBUtility.close(rs, statement, connection);
		}

		return ditchList;
	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null;
		connection = DBUtility.open();

		try {
			PSdelete = connection
					.prepareStatement("delete from t_ditch where uuid='" + uuid
							+ "'");
			PSdelete.executeUpdate();
			System.out.println("^^在执行DitchDaoImpl中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("^^在执行DitchDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}
	}

	@Override
	public boolean update(Ditch ditch) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();

		try {
			preparedStatement = connection
					.prepareStatement("update t_ditch set name=?,sex=?,phone=?,weixin=?,company=?,job=?,empUuid=?,rank=?,source=?,remark=?,moneyRate=? where uuid=?");
			preparedStatement.setString(1, ditch.getName());
			preparedStatement.setString(2, ditch.getSex());
			preparedStatement.setString(3, ditch.getPhone());
			preparedStatement.setString(4, ditch.getWeixin());
			preparedStatement.setString(5, ditch.getCompany());
			preparedStatement.setString(6, ditch.getJob());
			preparedStatement.setString(7, ditch.getEmpUuid());
			preparedStatement.setString(8, ditch.getRank());
			preparedStatement.setString(9, ditch.getSource());
			preparedStatement.setString(10, ditch.getRemark());
			preparedStatement.setString(11, ditch.getMoneyRate());
			preparedStatement.setString(12, ditch.getUuid());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行DitchDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("^^在执行DitchDaoImpl中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public Ditch getByUuid(String uuid) {
		// TODO Auto-generated method stub
		Ditch ditchResult = new Ditch();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT t_employee.name AS empName,t_ditch.* from t_ditch LEFT JOIN t_employee ON t_ditch.empUuid = t_employee.uuid where t_ditch.uuid='"
					+ uuid + "'");
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
				
				ditch.setEmpName(rs.getString("empName"));
				
				ditchResult = ditch;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DitchDaoImpl的查询单个部门失败");
			Ditch ditch = new Ditch();
			ditch.setUuid("DitchDaoImpl失败返回的uuid");
			return ditch;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
		return ditchResult;
	}

	@Override
	public ArrayList<Ditch> getList() {
		// TODO Auto-generated method stub
		ArrayList<Ditch> ditchList = new ArrayList<Ditch>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_ditch");
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
			System.out.println("DitchDaoImpl的查询部门列表失败");
			Ditch ditch = new Ditch();
			ditch.setUuid("DitchDaoImpl查询失败返回的uuid");
			ArrayList<Ditch> listDitch = new ArrayList<Ditch>();
			listDitch.add(ditch);
			return listDitch;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
	}

	/* 
	 * @param   String uuid, String oac
	 * @return   boolean
	 */
	@Override
	public boolean updateOnOff(String uuid, String oac) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		connection = DBUtility.open();
		try {
			ps=connection.prepareStatement("update t_ditch set openAndclose='"+oac+"' where uuid='"+uuid+"'");
			ps.executeUpdate();
			System.out.println("在执行DitchDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行DitchDaoImpl中updateOnOff,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, ps, connection);
		}
	}

	@Override
	public List<Ditch> findIdlist(String uuid) {
		// TODO Auto-generated method stub
		ArrayList<Ditch> yxstuList = new ArrayList<Ditch>();
	    Statement statement = null;//finally关闭数据库连接  
	    ResultSet rs = null;//关闭数据库连接get和getlist会用到
	    try {
	      connection = DBUtility.open();//打开数据库连接
	         statement = connection.createStatement();
	         rs = statement.executeQuery("SELECT * FROM t_ditch WHERE uuid='"+uuid+"'");
	        while (rs.next()) {
	        	Ditch yxstudent = new Ditch();
	          yxstudent.setUuid(rs.getString("uuid"));
	          yxstudent.setName(rs.getString("name"));
	          yxstudent.setCreateDate(rs.getString("createDate"));
	          yxstudent.setModifyDate(rs.getString("modifyDate"));
	          yxstudent.setSex(rs.getString("sex"));
	          yxstudent.setPhone(rs.getString("phone"));
	          yxstudent.setWeixin(rs.getString("weixin"));
	          

	          yxstudent.setEmpUuid(rs.getString("empUuid"));
	          yxstudent.setRank(rs.getString("rank"));
	          yxstudent.setSource(rs.getString("source"));
	          
	          yxstudent.setOpenAndclose(rs.getString("openAndclose"));
	          
	          yxstuList.add(yxstudent);
	        }
	        
	        return yxstuList;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("getList查询失败");
	        Ditch X = new Ditch();
	        X.setUuid("DitchDaoImpl的getList失败返回的uuid");
	        ArrayList<Ditch> XList = new ArrayList<Ditch>();
	        XList.add(X);
	        return XList;
	    }finally{   
	      DBUtility.close(rs, statement, connection);   
	     }//finally关闭jdbc与数据库连接
	}

}
