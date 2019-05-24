package fjw_blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjw.domain.User;
import com.fjw.mapper.UserMapper;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest {
	@Setter@Autowired
	private UserMapper userDAO;
	@Test
	public void testSave() {
		User user = new User();
		user.setUsername("472628608@qq.com");
		user.setPassword("rabbit2533");
		user.setName("用户1");
		user.setAge(18);
		user.setGender(1);
		user.setSchool("南信大滨江");
		userDAO.save(user);
	}
	@Test
	public void testDelete() {
		userDAO.delete(3L);
	}
	@Test
	public void testGet() {
		System.out.println(userDAO.get(1L));
	}
	@Test
	public void testUpdate() {
		User user = userDAO.get(4L);
		user.setImage("/image/fanjiawei.png");
		userDAO.update(user);
	}
	@Test
	public void testList() {
		System.out.println(userDAO.list());
	}
}
