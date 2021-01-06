package liojx.kn;

import liojx.kn.fnI.CreateFoodOrderImpl;
import liojx.kn.fnI.CreateOrder;
import liojx.kn.fnI.Order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
	    CreateOrder cfo1 = new CreateFoodOrderImpl();
	    System.out.println(cfo1.createOrder("霸王龙两只"));

	    CreateOrder cfo2 = new CreateOrder() {
		    @Override
		    public Order createOrder(String info) {
			    return new Order("9099XY013", new Date(), new BigDecimal(892.3), 2, info);
		    }
	    };
	    System.out.println(cfo2.createOrder("三角龙两只"));

	    CreateOrder cfo3 = (String info) -> {
	    	return new Order("9099XY014", new Date(), new BigDecimal(32.3), 2, info);
	    };
	    System.out.println(cfo3.createOrder("甲龙两只"));

	    /**
	     * 创建一个Predicate，判断它是否是苹果
	     */
	    Predicate<String> predicate  =  (String pram)  -> {
	    	return "apple".equals(pram) ? true : false;
	    };

	    System.out.println(predicate.test("apple"));  // 返回true

	    Predicate<String> pre1 = (String param) -> {
	    	return param.length() >= 5;
	    };

	    Predicate<String> pre2 = (String param) -> {
		    return "L".equals(param.charAt(5) + "");
	    };

	    Predicate<String> pre3 = pre1.and(pre2);
	    System.out.println(pre1.test("apple")); // true
	    System.out.println(pre2.test("alibaLba")); //true
	    System.out.println(pre3.test("appleLhb")); // true
	    System.out.println(pre3.test("applehhb")); // false

	    Predicate<String> pre4 = pre1.negate();
	    System.out.println(pre4.test("aaaa"));  // true
	    System.out.println(pre4.test("aaaaa"));  // false

	    Consumer<Order> con = (Order order) -> {
		    System.out.println(order.toString());
	    };
	    con.accept(new Order("9099XY015", new Date(), new BigDecimal(32.3), 2, "风神翼龙"));

	    Consumer<Order> con2 = (Order o) -> {
		    System.out.println(o.getTotalPrice().add(new BigDecimal(200)));
	    };

	    con = con.andThen(con2); // andThen 的前面对象方法先执行，参数对象的方法后执行
	    con.accept(new Order("9099XY015", new Date(), new BigDecimal(32.3), 2, "风神翼龙"));


	    Function<String, Integer> fun = (String t) -> {
	    	String []  arr = t.split("");
	    	return  Integer.parseInt(arr[1]) + 200;
	    };

	    System.out.println(fun.apply("23123"));  // 203

	    Function<Integer, String> fun2 = (Integer t) -> {
	        return String.valueOf(t);
	    };

	    Function fun3 = fun.compose(fun2);
	    System.out.println(fun3.apply(8999993));  // 209  先执行fun2 再执行fun
	    System.out.println(fun3.apply(8999993) instanceof Integer);  // true
	    Function fun4 = fun.andThen(fun2);
//	    System.out.println(fun4.apply(8999993));  // 先执行fun 在执行fun2
	    // 报错 Exception in thread "main"
	    // java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
	    // 由于fun执行完了返回的是String对象，紧接着把fun返回的对象作为fun2的参数对象，fun2参数需要Integer，故报类型错误
	    System.out.println(fun4.apply("8999993"));  // 209 先执行fun 在执行fun2
	    System.out.println(fun4.apply("8999993") instanceof Integer); // false

	    // identity 传进来什么就返回什么，类似于 t-> t，实际代码也是这样
	    Stream<String> stream = Stream.of("a","bb","ccc","dddd","eeeee","ffffff");
	    Stream<String> stream1 = Stream.of("a","bb","ccc","dddd","eeeee","ffffff");
	    Map<String, Integer> collect = stream.collect(Collectors.toMap(Function.identity(), String::length));
	    Map<String, Integer> collect2 = stream1.collect(Collectors.toMap(t -> t, String::length));
	    System.out.println(collect);  // {bb=2, a=1, ccc=3, ffffff=6, eeeee=5, dddd=4}
	    System.out.println(collect2);  // {bb=2, a=1, ccc=3, ffffff=6, eeeee=5, dddd=4}

	    Supplier<String> sup = () -> {
	    	Stream<String> st = Stream.of("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
				    "q","r","s","t","u","v","w","x","y","z");
		    Random r = new Random();
		    double v = 0;

		    List<String> list = st.collect(Collectors.toList());
		    StringBuffer sb = new StringBuffer();
		    int len = 10, word = 3;
		    for (int i = 0; i < word; i++){
		    	for (int j = 0 ;j < len; j++ ){
				    int a = -1;
				    while(a > 25 || a < 0) {
					    v = r.nextDouble();
					    a = (int)(v*100);
				    }
				    sb.append(list.get(a));
			    }
		    	sb.append(" ");
		    }
		    return sb.toString();
	    };

	    System.out.println(sup.get());


	    UnaryOperator<Integer> unaryOperator = (Integer i) -> {
	    	return i + 23;
	    };
	    System.out.println(unaryOperator.apply(30));

	    BinaryOperator<Integer> binaryOperator = (Integer i1, Integer i2) -> {
	    	return i1 > i2 ? i1 : i2;
	    };
	    System.out.println(binaryOperator.apply(50,70)); // 70

	    Stream<String> stream2 = Stream.of("l","s","j");
//	    stream2.forEach((String a) -> {
//	    	System.out.println(a);
//	    });
//	    stream2.forEach((String a) -> System.out.println(a));  // 可以省略大括号
//
//	    stream2.forEach((a)-> System.out.println(a)); // 省略了参数类型，省略了大括号

	    LambdaTest<Integer, Integer> lambdaTest1 = (x, y) -> {
	    	return x + y;
	    };
	    LambdaTest<Integer, Integer> lambdaTest2 = (x, y) -> x + y;  // 这里省略return关键字

	    System.out.println(lambdaTest1.test(1,2)); // 3
	    System.out.println(lambdaTest2.test(1, 2)); // 3

    }

	interface LambdaTest<T, R> {
		R test(T t, T t2);
	}
}