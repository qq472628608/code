package fjw_blog;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogTest {
	private static Logger logger = LogManager.getLogger(LogTest.class);
	
	public static void main(String[] args) {
		logger.debug("aksdjflkad");
		logger.error("sdkfalksdf");
	}
}
