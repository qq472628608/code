package fjw_blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjw.domain.Kind;
import com.fjw.mapper.KindMapper;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class KindTest {
	@Setter@Autowired
	private KindMapper kindDAO;
	@Test
	public void testSave() {
		Kind kind = new Kind();
		kind.setName("mysql");
		kind.setSn(2);
		kindDAO.save(kind);
	}
	@Test
	public void testDelete() {
		kindDAO.delete(2L);
	}
	@Test
	public void testGet() {
		System.out.println(kindDAO.get(3));
	}
	@Test
	public void testUpdate() {
		Kind kind = kindDAO.get(3);
		kind.setName("js");
		kindDAO.update(kind);
	}
	@Test
	public void testList() {
		System.out.println(kindDAO.list());
	}
}
