package com.cc.realm;


import com.cc.model.entity.Users;
import com.cc.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author : cc
 * @date : 2018-09-25  11:18
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权方法
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 认证方法
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        Users user = userService.findUserByName(usernamePasswordToken.getUsername());
        if(null == user){
            return null;
        }else {
            String password = user.getPassword();
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, this.getClass().getName());
            return info;
        }
    }
}
