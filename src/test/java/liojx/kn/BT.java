package liojx.kn;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liaosijun
 * @Time: 2020/8/11 16:29
 */
public class BT {
	static class User{
		private long id;
		private String name;
		private Integer age;
		private Double height;

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getAge() {
			return age;
		}

		public void setAge(Integer age) {
			this.age = age;
		}

		public Double getHeight() {
			return height;
		}

		public void setHeight(Double height) {
			this.height = height;
		}
	}
	public static void main(String[] args) {
		List<User> list = new ArrayList<>();
		User user1 = new User();
		user1.setAge(15);
		user1.setName("zhang san");
		user1.setHeight(1.78);
		user1.setId(1);

		User user2 = new User();
		user2.setAge(13);
		user2.setName("li gui");
		user2.setHeight(1.45);
		user2.setId(2);

		User user3 = new User();
		user3.setAge(14);
		user3.setName("mu yan");
		user3.setHeight(1.67);
		user3.setId(3);
		list.add(user1);
		list.add(user2);
		list.add(user3);

		list.stream().forEach(a -> System.out.println(a.getName()));
		list.stream().forEach((User a) -> {
			System.out.println(a.getName());
		});
	}
}
