package liojx.kn.listener;

import liojx.kn.event.TestEvent1;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: liaosijun
 * @Time: 2021/1/6 15:33
 */
@Component
public class TestListener1 implements ApplicationListener<TestEvent1> {
	@Override
	public void onApplicationEvent(TestEvent1 event) {
		System.out.println("事件触发..." );
	}
}
