package com.QDcrm.system.service.impl;

import com.QDcrm.system.dao.UserPKpassDao;
import com.QDcrm.system.dao.impl.UserPKpassDaoImpl;
import com.QDcrm.system.model.UserPK;
import com.QDcrm.system.service.UserPKpassService;
/*
 * 
 * @author 刘鑫
 * @date  ‎2018‎年‎1‎月‎24‎日‎ ‎17‎:‎08‎:‎38
 */
public class UserPKpassServiceImpl implements UserPKpassService {
	UserPKpassDao upd=new UserPKpassDaoImpl();
	
	@Override
	public boolean updatePassword(UserPK userPK) {
		// TODO Auto-generated method stub
		
		return upd.updateUserPassword(userPK);
	}

}
