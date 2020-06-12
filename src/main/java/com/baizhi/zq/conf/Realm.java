package com.baizhi.zq.conf;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

public class Realm extends AuthorizingRealm {



    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //1.查询用户有哪些权限
        String username = (String)principalCollection.getPrimaryPrincipal();

        //  根据用户主身份去查询该用户有哪些角色
        // admin sadmin supers
        //  根据用户主身份去查询角色对应有哪些权限

        //2.授予相关权限   模拟查询数据库查询到的数据信息
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if(username.equals("xiaohei")){
            //授予角色
            //info.addRole("admin");
            //授予角色集合
            info.addRoles(Arrays.asList("admin","sadmin","supers"));

            //授予权限
            //info.addStringPermission("user:query");
            //授予权限集合
            info.addStringPermissions(Arrays.asList("user:query","user:update"));

        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //从令牌中获取身份信息
        String username = (String) token.getPrincipal();

        //查询数据库
        //select * from user where username=#{username}
        // User("1","xiaohei","111111")
        //1 xiaohei  e0504e77b06f5a26faf37d044c65992b  ABCD

        AuthenticationInfo info=null;

        if(username.equals("xiaohei")){

            //将数据库查询到的数据封装到info中  密文数据
            info = new SimpleAuthenticationInfo("xiaohei","e0504e77b06f5a26faf37d044c65992b", ByteSource.Util.bytes("ABCD"),this.getName());
        }
        return info;
    }
}