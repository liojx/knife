package liojx.kn.thread.treadLocal.define;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自己实现ThreadLocal
 * @Author: liaosijun
 * @Time: 2020/8/7 15:36
 */
public class MyThreadLocal<T> {

	// 用Thread作为键，会在内存中留很多引用，换成int
	private static AtomicInteger atomicInteger = new AtomicInteger();

	/**
	 * ThreadLocalMap的哈希函数
	 * h(x) = 1640531527 * x %(2^32 - 1)
	 * 高德纳提出的一个哈希算法，保证哈希的平均性
	 */
	Integer threadLocalHash = atomicInteger.addAndGet(0x61c88647);

	// key值是线程，表明哪个线程持有哪个MyThreadLocal
//	private static HashMap<Thread,HashMap<MyThreadLocal<?>, Object>> map = new HashMap<>();
	private static HashMap<Thread,HashMap<Integer, Object>> map = new HashMap<>();

	/**
	 * 必须是同步的，因为所有的MyThreadLocal要获取值，都是在临界区
	 * @return
	 */
	private synchronized static HashMap<Integer, Object> getMap() {
		Thread thread = Thread.currentThread();
		if (!map.containsKey(thread)) {
			map.put(thread, new HashMap<Integer, Object>());
		}
		return map.get(thread);
	}
	protected T initialValue(){
		return null;
	}


	public T get(){
		HashMap<Integer, Object> mp = getMap();
		if (!mp.containsKey(this.threadLocalHash)) {
			mp.put(this.threadLocalHash, initialValue());
		}
		return (T) mp.get(this.threadLocalHash);
	}

	public void set(T v) {
		HashMap<Integer, Object> map = getMap();
		map.put(this.threadLocalHash, v);
	}
}
