package com.zczp.cms.shiro;


import com.zczp.util.JwtUtil;
import com.zczp.vo_cancer.JwtToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Realm 的一个配置管理类 allRealm()方法得到所有的realm
 */
@Component
public class ShiroRealmConfig extends AuthorizingRealm{


    @Resource
    private JwtUtil jwtUtil;

    /**
     * 配置所有自定义的realm,方便起见,应对可能有多个realm的情况
     */
    public List<Realm> allRealm() {
        List<Realm> realmList = new LinkedList<>();
        AuthorizingRealm jwtRealm = jwtRealm();
        realmList.add(jwtRealm);
        return Collections.unmodifiableList(realmList);
    }


    /**
     * 自定义 JWT的 Realm
     * 重写 Realm 的 supports() 方法是通过 JWT 进行登录判断的关键
     */
    private AuthorizingRealm jwtRealm() {
        AuthorizingRealm jwtRealm = new AuthorizingRealm() {
            /**
             * 注意坑点 : 必须重写此方法，不然Shiro会报错
             * 因为创建了 JWTToken 用于替换Shiro原生 token,所以必须在此方法中显式的进行替换，否则在进行判断时会一直失败
             */
            @Override
            public boolean supports(AuthenticationToken token) {
                return token instanceof JwtToken;
            }

            @Override
            protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
                return new SimpleAuthorizationInfo();
            }

            /**
             * 校验 验证token逻辑
             */
            @Override
            protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
                String jwtToken = (String) token.getCredentials();
                if (jwtToken==""||jwtToken==null)
                    throw new AuthenticationException("没有token");
                String userName=jwtUtil.getUserNameByToken(jwtToken);
                String password=jwtUtil.getPasswordByToken(jwtToken);
                if (StringUtils.isEmpty(userName))
                    throw new AuthenticationException("user account not exits , please check your token");
                if (StringUtils.isEmpty(password))
                    throw new AuthenticationException("password is invalid , please check your token");
                if (!jwtUtil.verifyUserToken(jwtToken))
                    throw new AuthenticationException("token is invalid , please check your token");
                return new SimpleAuthenticationInfo(token, token, getName());
            }
        };
        jwtRealm.setCredentialsMatcher(credentialsMatcher());
        return jwtRealm;
    }

    /**
     * 注意坑点 : 密码校验 , 这里因为是JWT形式,就无需密码校验和加密,直接让其返回为true(如果不设置的话,该值默认为false,即始终验证不通过)
     */
    private CredentialsMatcher credentialsMatcher() {
        return (token, info) -> true;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}