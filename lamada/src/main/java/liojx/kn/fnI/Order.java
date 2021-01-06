package liojx.kn.fnI;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: liaosijun
 * @Time: 2020/8/21 14:01
 */
public class Order {

	/** 订单编号 */
	private String orderCode;

	/** 订单创建时间 */
	private Date createTime;

	/** 订单总价 */
	private BigDecimal totalPrice;

	/** 商品数量 */
	private int count;

	/** 商品 */
	private String product;

	public Order() {
	}

	public Order(String orderCode, Date createTime, BigDecimal totalPrice, int count, String product) {
		this.orderCode = orderCode;
		this.createTime = createTime;
		this.totalPrice = totalPrice;
		this.count = count;
		this.product = product;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Order{" +
				"orderCode='" + orderCode + '\'' +
				", createTime=" + createTime +
				", totalPrice=" + totalPrice +
				", count=" + count +
				", product='" + product + '\'' +
				'}';
	}
}
