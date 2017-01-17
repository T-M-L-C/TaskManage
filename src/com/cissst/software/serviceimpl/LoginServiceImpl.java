package com.cissst.software.serviceimpl;

import com.cissst.software.dao.LoginMysqlDao;
import com.cissst.software.service.ILoginService;

public class LoginServiceImpl implements ILoginService {

	LoginMysqlDao LoginMysqlDao = new LoginMysqlDao();
	public boolean LoginUser(String loginname, String loginpwd, String authority) {
		
		return LoginMysqlDao.loginUser(loginname, loginpwd, authority);
	}

}
