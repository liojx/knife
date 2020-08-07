package liojx.kn.thread.treadLocal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: liaosijun
 * @Time: 2020/8/6 14:56
 */
@RestController
@RequestMapping(value = "/thrTst")
public class AddTestController {

	private static int c = 0;

	@RequestMapping(value = "/read")
	public int read() {
		return c;
	}

	@RequestMapping(value = "/add")
	public void add() throws InterruptedException {
		Thread.sleep(10);
		c = c + 1;
		System.out.println("thread-ID = " + Thread.currentThread().getId() + ",c = " + c);
	}

	@RequestMapping(value = "/s_add")
	public synchronized void s_add() throws InterruptedException {
		Thread.sleep(10);
		c = c + 1;
		System.out.println("thread-ID = " + Thread.currentThread().getId() + ",c = " + c);
	}

	private static ThreadLocal<Integer> d = ThreadLocal.withInitial(() -> 0);

	@RequestMapping(value = "/local_add")
	public  void local_add() throws InterruptedException {
		Thread.sleep(100);
		d.set(d.get() + 1);
	}

	@RequestMapping(value = "/l_read")
	public int l_read() {
		return d.get();
	}
}
