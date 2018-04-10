/**
 * 
 */
package com.QDcrm.query.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.QDcrm.model.BackResult;
import com.QDcrm.model.Record;
import com.QDcrm.query.service.QuePageRecordService;
import com.QDcrm.query.service.impl.QuePageRecordServiceImpl;
import com.QDcrm.service.RecordService;
import com.QDcrm.service.impl.RecordServiceImpl;
import com.QDcrm.utility.T_DataControl;
import com.google.gson.Gson;

/**
 * @author LiuXin
 *@date 2018-1-31 下午3:57:58
 *@version 跟踪记录表分页查询
 */
public class QuePageRecordController extends HttpServlet{
	QuePageRecordService qprs=new QuePageRecordServiceImpl();
	RecordService rs = new RecordServiceImpl();
	BackResult backResult = new BackResult("信息值,默认", "请求值,默认", null);
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String qqiu = request.getParameter("qqiu");
		if(qqiu.equals("pageByDitchUid")){
			T_DataControl t_data = new T_DataControl();
			String str = t_data.getRequestPayload(request);
			Record record = new Record();
			if(str != null && str != "" && str.length() != 0){
				Map<String, Object> map = t_data.JsonStrToMap(str);
				String ditchUuid=(String) map.get("ditchUuid");
				int currentPage=Integer.valueOf((String) map.get("currentPage")).intValue();
				int maxResult=Integer.valueOf((String) map.get("maxResult")).intValue();
				ArrayList<Record> recList=qprs.getPageByDitchUuid(ditchUuid, currentPage, maxResult);
				int sum=rs.getListByDitchUuid(ditchUuid).size();
				if(sum%maxResult==0){
					backResult.setMessage("数据库总共页数:"+sum/maxResult);
				}
				backResult.setMessage("数据库总共页数:"+(sum/maxResult+1));
				backResult.setQingqiu(rs.getListByDitchUuid(ditchUuid).size()+"");
				backResult.setData(recList);
			}else{
				System.out.println("前台传入数据为空，请联前台传入post请求体数系管理员！");
				backResult.setMessage("数据库总共页数:0页");
				backResult.setQingqiu("数据总条数:0条");
				ArrayList<String> strList=new ArrayList<String>();
				strList.add("您请求的数据为空!");
				backResult.setData(strList);
			}
		}else{
			System.out.println("qqiu请求参数  " + qqiu + "  不规范");
		}
		Gson gson = new Gson();
		// 4 执行完qqiuChoice里面操作后的全局返回值backResult对象,转成json格式
		String back = gson.toJson(backResult);
		System.out.println("最后back值是：" + back);
		// 5 将json格式的back传给前台
		out.write(back);
		out.flush();
		out.close();
	}
}
