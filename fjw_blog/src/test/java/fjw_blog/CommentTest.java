package fjw_blog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjw.domain.Article;
import com.fjw.domain.Comment;
import com.fjw.domain.User;
import com.fjw.mapper.ArticleMapper;
import com.fjw.mapper.CommentMapper;
import com.fjw.mapper.UserMapper;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CommentTest {
	@Setter@Autowired
	private CommentMapper commentDAO;
	@Setter@Autowired
	private ArticleMapper articleDAO;
	@Setter@Autowired
	private UserMapper userDAO;
	@Test
	public void testSave() {
		Comment comment = new Comment();
		comment.setText("这是一条评论3");
		Date date = new Date();
		comment.setTime(date);
		Comment parent = commentDAO.get(1L);
		comment.setParent(parent);
		Article article = articleDAO.get(1L);
		User user = userDAO.get(1L);
		comment.setArticle(article);
		comment.setUser(user);
		System.out.println(comment);
		commentDAO.save(comment);
	}
	@Test
	public void testDelete() {
		commentDAO.delete(2L);
	}
	@Test
	public void testGet() {
		System.out.println(commentDAO.get(1L));
	}
	@Test
	public void testUpdate() {
		Comment comment = commentDAO.get(3L);
		comment.setText("这是一条评论4");
		commentDAO.update(comment);
	}
	@Test
	public void testListFour() {
		System.out.println(commentDAO.listFour());
	}
	@Test
	public void testList() {
		System.out.println(commentDAO.list(1L));
	}
}


