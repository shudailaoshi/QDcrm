/**
 * 
 */
package com.QDcrm.query.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.QDcrm.model.BackResult;
import com.QDcrm.model.Ditch;
import com.QDcrm.query.service.MyDitchService;
import com.QDcrm.query.service.impl.MyDitchServiceImpl;
import com.google.gson.Gson;

/**
 * @author LiuXin
 * @date 2018-2-1 下午3:15:33
 * @version 我的渠道列表查询
 */
public class MyDitchController extends HttpServlet {
	MyDitchService mds = new MyDitchServiceImpl();
	BackResult backResult = new BackResult("信息值,默认", "请求值,默认", null);

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String qqiu = request.getParameter("qqiu");
		String empUuid = request.getParameter("empUuid");
		if (qqiu.equals("list") && empUuid != null && empUuid != "") {
			ArrayList<Ditch> ditchList = mds.getListByEmpUuid(empUuid);
			backResult.setMessage("信息值：成功");
			backResult.setQingqiu("list查询列表");
			backResult.setData(ditchList);
		} else {
			System.out.println("前台传入数据为空，请联系前台传入get请求体！");
			backResult.setMessage("信息值：失败");
			backResult.setQingqiu("list查询列表");
			backResult.setData(new ArrayList<String>());
		}
		Gson gson = new Gson();

		String back = gson.toJson(backResult);
		System.out.println("最后back值是：" + back);

		out.write(back);
		out.flush();
		out.close();
	}
}
