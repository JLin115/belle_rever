package home.login.model;

import home.register.model.*;;

public interface LoginService {
	public MemberBean checkPermission(int mpid);
	public MemberBean checkIDPassword(String userId, String password) ;
	
}
