package com.athome.mygmall.shiro.realm;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class DatabaseRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// TODO Auto-generated method stub
		// 能进入这里，表示账号已经验证通过
		String username = (String) principalCollection.getPrimaryPrincipal();
		// 通过ShiroDAO获取角色和权限
		Set<String> permissions = new ShiroDAO().listPermissions(username);
		Set<String> roles = new ShiroDAO().listRoles(username);

		// 授权对象
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		// 把获取的居然色和权限放进去
		simpleAuthorizationInfo.setStringPermissions(permissions);
		simpleAuthorizationInfo.setRoles(roles);

		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		// 获取账号密码
		UsernamePasswordToken t = (UsernamePasswordToken) token;
		String userName = token.getPrincipal().toString();
		String password = new String(t.getPassword());
		// 获取数据库中的密码
		String passwordInDB = new ShiroDAO().getPassword(userName);

		// 如果为空就是账号不存在，如果不相同就是密码错误，但是都抛出AuthenticationException，而不是抛出具体错误原因，免得给破解者提供帮助信息
		if (null == passwordInDB || !passwordInDB.equals(password))
			throw new AuthenticationException();

		// 认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
		SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName, password, getName());
		return a;
	}
}
