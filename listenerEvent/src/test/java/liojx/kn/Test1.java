package liojx.kn;

import liojx.kn.event.TestEvent1;
import liojx.kn.publish.PublishEventService1;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author: liaosijun
 * @Time: 2021/1/6 15:34
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class Test1 {

	@Autowired
	private PublishEventService1 publisher1;

	@Test
	public void test() {
		TestEvent1 testEvent = new TestEvent1("hello word");
//		publisher1.publishEvent(testEvent);

	}

}
