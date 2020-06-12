import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

public class TestShiro {

    @Test
    public void contextLoads() {
        Subject subject = null;

        try {
            //初始化安全管理器工厂
            IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

            //通过安全管理器工厂创建一个安全管理器
            SecurityManager securityManager = factory.createInstance();

            //将安全管理器放入安全工具类   自动使用
            SecurityUtils.setSecurityManager(securityManager);

            //通过安全工具类获取主体对象
            subject = SecurityUtils.getSubject();

            //创建一个认证的令牌   token=身份信息+凭证信息
            AuthenticationToken token = new UsernamePasswordToken("xiaohei","111111");

            //认证
            subject.login(token);

        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            System.out.println("未知的账号异常");
            //UnknownAccountException    未知的账号异常   用户不存在
            //IncorrectCredentialsException  不正确的凭证异常    密码错误
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            System.out.println("不正确的凭证异常");
            //UnknownAccountException    未知的账号异常   用户不存在
            //IncorrectCredentialsException  不正确的凭证异常    密码错误
        }

        //判断是否认证成功
        boolean authenticated = subject.isAuthenticated();

        //判断认证是否成功
        if(authenticated){
            /**基于角色的访问控制 授权*/

            //判断该主体是否有该角色
            //boolean b = subject.hasRole("admin");

            //判断该主体是否含有这些角色
            //boolean[] b = subject.hasRoles(Arrays.asList("admin", "supers","user"));

            //判断该主体是否含有所有角色
            boolean allRoles = subject.hasAllRoles(Arrays.asList("admin", "supers"));

            /**基于权限的访问控制 授权*/

            //判断该主体是否含有该权限
            //boolean b = subject.isPermitted("user:delete");

            //判断该主体是否含有这些权限
            //boolean[] b = subject.isPermitted("user:query", "user:update");

            //判断该主体是否含有所有权限
            boolean permittedAll = subject.isPermittedAll("user:query", "user:update");

            System.out.println("角色授权:"+allRoles);
            System.out.println("权限授权:"+permittedAll);

        }

    }

}
