package liojx.kn.thread.treadLocal.define;

/**
 * @Author: liaosijun
 * @Time: 2020/8/7 15:36
 */
public class UseMyThreadLocal {

	private static MyThreadLocal<Long> v = new MyThreadLocal<Long>(){
		@Override
		protected Long initialValue(){
			return Thread.currentThread().getId();
		}
	};

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new Thread(() ->{
				System.out.println(v.get());
			}).start();
		}
	}
}
