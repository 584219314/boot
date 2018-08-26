package boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import boot.task.CrawTask;
/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppTest {
	@Autowired
	private CrawTask crawTask;
	
	@Test
	public void craw(){
		crawTask.process();
	}
}
	