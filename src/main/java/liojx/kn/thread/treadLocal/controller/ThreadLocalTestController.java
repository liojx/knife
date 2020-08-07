package liojx.kn.thread.treadLocal.controller;

import com.google.common.collect.Sets;
import liojx.kn.thread.treadLocal.Val;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;

/**
 * 用threadLocal 来异步计算，并获取最终结果
 * @Author: liaosijun
 * @Time: 2020/8/6 17:43
 */
@RestController
@RequestMapping(value = "/tl")
public class ThreadLocalTestController {

	// 存放所有线程计算的累计结果，就是总任务的总结果
	private static HashSet<Val<Integer>> set = Sets.newHashSet();

	// threadLocal初始化添加到set中去的时候， set这个全局变量处于临界区
	// 临界区 ： 所有线程共享的内存区
	private synchronized static void addSet(Val<Integer> v) {
		set.add(v);
	}

	private static ThreadLocal<Val<Integer>> r = new ThreadLocal<Val<Integer>>() {
		/**
		 * 初始化方法，在threadLocal只执行一次，并且是懒加载，当有人来获取值的时候才执行
		 * @return
		 */
		@Override
		protected Val<Integer> initialValue() {
			Val<Integer> v = new Val<>();
			v.set(0);
			// set 对象处于临界区，不同的线程访问它时会出问题，所以这里加锁，相比于每个子任务的加锁，这个只加初始的锁，快多了。
			// 相比于类AddTestController 的s_add方法，如果要计算10000次，那么就有10000把锁，而这里如果并发100个用户的话，
			// 执行10000次也只加了100把锁。显然时间上快很多。
			addSet(v);
			return v;
		};
	};

	@RequestMapping(value = "/add")
	public void add() throws InterruptedException {
		Thread.sleep(100);
		Val<Integer> v = r.get();
		v.set(v.get() + 1);
	}

	@RequestMapping(value = "/read")
	public Integer read(){
		return set.stream().map(x -> x.get()) // x 为Val对象，x.get()为int
				.reduce((a, x) -> a + x)  // 累加set每一个值
				.get(); //返回int结果
	}
}
