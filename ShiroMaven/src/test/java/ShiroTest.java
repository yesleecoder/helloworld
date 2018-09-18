import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;
@Data
public class ShiroTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    @Before
    public void adduser(){
        simpleAccountRealm.addAccount("admin","lee","users");
    }
    @Test
    public void shiroTest(){
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken("admin","lee");
        subject.login(token);
        System.out.println("isAuthenticated "+subject.isAuthenticated());
        subject.logout();
        System.out.println("isAuthenticated "+subject.isAuthenticated());
    }

}
