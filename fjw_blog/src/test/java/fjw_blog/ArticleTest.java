package fjw_blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjw.domain.Article;
import com.fjw.domain.Kind;
import com.fjw.mapper.ArticleMapper;
import com.fjw.mapper.KindMapper;
import com.fjw.mapper.UserMapper;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ArticleTest {
	@Setter@Autowired
	private ArticleMapper articleDAO;
	@Setter@Autowired
	private KindMapper kindDAO;
	@Setter@Autowired
	private UserMapper userDAO;
	@Test
	public void testSave() {
		Article article = new Article();
		article.setTitle("这是一篇文章2");
		String text = "";
		for (int i = 0; i < 100; i++) {
			text += "alkdjflkajdflkajl;dkfjalskdjflkajdfadfaohgkajhsdfjkasdf";
		}
		article.setText(text);
		article.setLove(0);
		article.setSummary("笨啊空间的发卡机大佛阿卡京东方家里看的设计费拉的积分了爱上江东父老安静的李开复加水电费拉打飞机反甲啊了快递放假埃里克骄傲的");
		article.setImage("/image/jadfkla.png");
		Kind kind = kindDAO.get(1);
		article.setKind(kind);
		articleDAO.save(article);
	}
	@Test
	public void testDelete() {
		articleDAO.delete(2L);
	}
	@Test
	public void testGet() {
		System.out.println(articleDAO.get(1L));
	}
	@Test
	public void testUpdate() {
		Article article = articleDAO.get(1L);
		article.setTitle("这是一篇文章1");
		articleDAO.update(article);
	}
	@Test
	public void testList() {
		System.out.println(articleDAO.list());
	}
	@Test
	public void testQuery() {
		String keywords = "%李开复%";
		System.out.println(articleDAO.query(keywords));
	}
	@Test
	public void testSaveUserAndArticle() {
		articleDAO.saveUserAndArticle(1L, 1L);
	}
	
}
