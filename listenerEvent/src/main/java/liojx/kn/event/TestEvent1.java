package liojx.kn.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Author: liaosijun
 * @Time: 2021/1/6 15:30
 */
public class TestEvent1 extends ApplicationEvent {
	/**
	 * Create a new ApplicationEvent.
	 *
	 * @param source the object on which the event initially occurred (never {@code null})
	 */
	public TestEvent1(Object source) {
		super(source);
	}
}
