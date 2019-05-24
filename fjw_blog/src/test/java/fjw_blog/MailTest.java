package fjw_blog;

import org.junit.Test;

import com.fjw.mail.MailUtil;
import com.fjw.util.IdentifyCodeUtil;

public class MailTest {
	String code = IdentifyCodeUtil.getIndetifyCode();
	String target = "qq472628608@163.com";
	String subject = "欢迎注册FjwBlog";
	String text = "您的验证码为："+code;	
	
	@Test
	public void testMail() {
		MailUtil.sendMail(target, subject, text);
	}
}

