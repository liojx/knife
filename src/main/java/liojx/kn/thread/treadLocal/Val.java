package liojx.kn.thread.treadLocal;

/**
 * 存放于ThreadLocal里面的对象，因为基本类型无法调用方法
 * @Author: liaosijun
 * @Time: 2020/8/6 17:40
 */
public class Val<T> {

	private T v ;

	public void set(T t){
		v = t;
	}

	public T get() {
		return v;
	}
}
