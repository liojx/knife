package liojx.kn.fnI;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: liaosijun
 * @Time: 2020/8/21 15:09
 */
public class CreateFoodOrderImpl implements CreateOrder {
	@Override
	public Order createOrder(String info) {
		Order order = new Order();
		order.setOrderCode("9099XY012");
		order.setCreateTime(new Date());
		order.setCount(2);
		order.setTotalPrice(new BigDecimal(239.0));
		order.setProduct(info);
		return order;
	}
}
