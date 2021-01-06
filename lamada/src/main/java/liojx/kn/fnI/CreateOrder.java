package liojx.kn.fnI;

/**
 * @Author: liaosijun
 * @Time: 2020/8/21 14:00
 */

/**
 * 创建订单的接口
 */
@FunctionalInterface // 表明这个接口属于函数式接口，一个接口中仅一个方法，当然静态方法、Object方法、default方法除外
public interface CreateOrder {

	/**
	 * 创建订单信息
	 * @param info          创建订单的参数
	 * @return              返回订单信息
	 */
	Order createOrder(String info);

	/**
	 * 当我欲再创建一个抽象方法的时候,
	 * @FunctionalInterface 会红色波浪线提示错误：Multiple non-overriding abstract methods
	 * found in interface liojx.kn.fnI.CreateOrder
	 * 意为：有多个非重写的抽象方法
	 * 注释
	 */
//	Order getOrderById(Long id);

	/**
	 * 而重写Object的toString方法是可以通过函数式接口验证的
	 * @return
	 */
	@Override
	String toString();

	/**
	 * default方法也可
	 * 每一个实现订单创建的接口，都可以获得，订单商品的统一商家
	 */
	default String getFactory() {
		return "侏罗纪恐龙制造公司";
	}

	/**
	 * 静态方法也可以通过验证
	 * 每一个实现订单创建的接口，都可以获得订单商品销售总监的联系方式
	 */
	static String getSalesmanPhone() {
		return "13888888888";
	}

}
