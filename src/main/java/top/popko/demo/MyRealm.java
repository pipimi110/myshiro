package top.popko.demo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.security.Key;

public class MyRealm extends AuthorizingRealm {
    public MyRealm() {
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        if (!"admin".equals(username)) {
            throw new UnknownAccountException("账户不存在!");
        } else {
            return new SimpleAuthenticationInfo(username, "123456", this.getName());
        }
    }

    public static void main(String[] args) {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128); //设置key长度
//生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
//加密
        String encrptText =  aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
//解密
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());
//        Assert.assertEquals(text, text2);
    }
}
