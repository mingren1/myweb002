package com.athome.mygmall.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroTest {
	
	public static Logger log = LoggerFactory.getLogger(ShiroTest.class);
	

	public static void main(String[] args) {
		Subject currentUser = SecurityUtils.getSubject();
		
		Session session = currentUser.getSession();
		session.setAttribute( "someKey", "aValue" );
		
		if ( !currentUser.isAuthenticated() ) {
		    //collect user principals and credentials in a gui specific manner
		    //such as username/password html form, X509 certificate, OpenID, etc.
		    //We'll use the username/password example here since it is the most common.
		    //(do you know what movie this is from? ;)
		    UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
		    //this is all you have to do to support 'remember me' (no config - built in!):
		    token.setRememberMe(true);
		    currentUser.login(token);
		}
		
		try {
		    AuthenticationToken token1 = null;
			currentUser.login( token1 );
		    //if no exception, that's it, we're done!
		} catch ( UnknownAccountException uae ) {
		    //username wasn't in the system, show them an error message?
		} catch ( IncorrectCredentialsException ice ) {
		    //password didn't match, try again?
		} catch ( LockedAccountException lae ) {
		    //account for that username is locked - can't login.  Show them a message?
		}
		
		if ( currentUser.hasRole("schwartz") ) {
		    log.info("May the Schwartz be with you!");
		} else {
		    log.info( "Hello, mere mortal." );
		}
		
		
	}
}
