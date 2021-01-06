package liojx.kn.io.use;

import com.google.common.collect.Lists;
import liojx.kn.db.DButils;
import liojx.kn.io.FileUtils;
import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: liaosijun
 * @Time: 2020/5/21 15:26
 */
public class FileUse {

	/**
	 * 背景： eclipse 里面有plugins 和 features目录，拷贝了网上的一个jasper插件进去不生效，想把刚拷贝进去的文件删除，没有删除目录
	 *
	 */
	void deleteSomeRepeatFile(){
		FileUtils.Response eclipse = new FileUtils.Response();
		File fileByPath = FileUtils.createFileByPath("D:\\program files\\eclipse\\eclipse");
		FileUtils.listAllFilesByRootFile(fileByPath,eclipse);

		FileUtils.Response plugins = new FileUtils.Response();
		File fileByPath1 = FileUtils.createFileByPath("D:\\download\\plu");
		FileUtils.listAllFilesByRootFile(fileByPath1,plugins);
		int i =0;
		for (File file : eclipse.getFiles()) {
			for (File f : plugins.getFiles()) {
				if (file.getName().equals(f.getName()) && file.length() == f.length()) {
					file.delete();
					i++;
				}
			}
		}
		System.out.println("删除重复文件个数：" + i);
	}


	void delJavaFile(){
		FileUtils.Response response = new FileUtils.Response();
		File fileByPath = FileUtils.createFileByPath("D:\\evercreative_workspace\\sw-charge-starter");
		FileUtils.listAllFilesByRootFile(fileByPath,response);
		List<File> allFiles = response.getAllFiles();
		if (allFiles != null) {
			allFiles.forEach(f -> {
				if  (f.getName().endsWith(".java")) {
					f.delete();
					response.fileCount++;
				}
			});
		}
		System.out.println("删除java文件" + response.getFileCount() + "个！");

	}

	public static class O{
		public String code;
		public String value;
		public Long id;

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "O{" +
					"code='" + code + '\'' +
					", value='" + value + '\'' +
					", id=" + id +
					'}';
		}
	}
//	public static void main(String[] args) {
//		FileUse fileUse = new FileUse();
////		fileUse.deleteSomeRepeatFile();
////		fileUse.delJavaFile();
//		File f = FileUtils.createFileByPath("C:\\Users\\Liao_\\Desktop\\g.txt");
//		List<String> list = FileUtils.fileToString(f.getPath());
//		List<O> oList = Lists.newArrayList();
//		if (Objects.nonNull(list)) {
//			for(String s : list) {
//				String[] s1 = s.split("\\s+");
//				String[] s2 = s1[0].split(",");
//				O o = new FileUse.O();
//				o.setCode(s2[0]);
//				o.setValue(s2[1]);
//				o.setId(Long.parseLong(s1[1]));
//				oList.add(o);
//			}
//		}
//		for (int i = 0; i< oList.size(); i++){
//			O o = oList.get(i);
//			String a = "INSERT INTO swo_dictionary ( code, show_value,real_value,creat_at,creat_by) values(";
//			String b = ");";
//			System.out.println(a + "'" +
//					o.getCode() + "',"
//			+ "'" + o.getValue() + "',"
//			+ "'" + o.getValue() + "',"
//			+ "now(),"
//			+"1" + b);
//		}
//	}

	@ToString
	@Data
	static class ZJ{
		private String name;
		private String city;
		private String company;
		private String jobTitle;
		private String goodAt;
		private String business;
		private String personalIntro;
		private String abilityToHelp;
		private String harvest;
	}

	/**
	 * 把文本的内容封装到对象来
	 */
	static List<ZJ> getBytxt(){
		String path = "C:\\Users\\Liao_\\Desktop\\专家intro.txt";
		List<String> list = FileUtils.fileToString(path);
		List<String> abc = Lists.newArrayList();
		list.forEach(s -> {
			if (!StringUtils.isEmpty(s)) {
				abc.add(s);
			}
		});
		List<List<String>> bbc = Lists.newArrayList();
		List<String> o = null;
		for (int i = 0; i < abc.size(); i ++ ){
			if (i % 9 == 0 ) {
				o = Lists.newArrayList();
				bbc.add(o);
			}
			o.add(abc.get(i));
		}
		String a = "【姓名】";
		String b = "【所在城市】";
		String c = "【所在单位】";
		String d = "【所在职位】";
		String e = "【擅长领域】";
		String f = "【公司业务】";
		String g = "【个人简介】";
		String h = "【我能提供的帮助】";
		String ii = "【本次培训想收获什么】";
		List<ZJ> zjArrayList = Lists.newArrayList();
		bbc.forEach(s -> {
			ZJ zj = new ZJ();
			for(String ax : s) {
				if (ax.contains(a)) {
					zj.setName(ax.replace(a, ""));
				} else if (ax.contains(b)) {
					zj.setCity(ax.replace(b, ""));
				} else if (ax.contains(c)) {
					zj.setCompany(ax.replace(c, ""));
				} else if (ax.contains(d)) {
					zj.setJobTitle(ax.replace(d, ""));
				}else if (ax.contains(e)) {
					zj.setGoodAt(ax.replace(e, ""));
				}else if (ax.contains(f)) {
					zj.setBusiness(ax.replace(f, ""));
				}else if (ax.contains(g)) {
					zj.setPersonalIntro(ax.replace(g, ""));
				}else if (ax.contains(h)) {
					zj.setAbilityToHelp(ax.replace(h, ""));
				}else if (ax.contains(ii)) {
					zj.setHarvest(ax.replace(ii, ""));
				}
			}
			zjArrayList.add(zj);
		});
		return zjArrayList;
	}

	static String getInsertSql () {
		StringBuffer sql = new StringBuffer("INSERT INTO tech_vp (name, city,company,goodAt, business,personal_intro,ability_to_help,harvest,job_title) VALUES");
		List<ZJ> bytxt = getBytxt();
		for(int i = 0;i<bytxt.size(); i++) {
			ZJ o = bytxt.get(i);
			sql.append("(")
					.append("'").append(o.getName()).append("',")
					.append("'").append(o.getCity()).append("',")
					.append("'").append(o.getCompany()).append("',")
					.append("'").append(o.getGoodAt()).append("',")
					.append("'").append(o.getBusiness()).append("',")
					.append("'").append(o.getPersonalIntro()).append("',")
					.append("'").append(o.getAbilityToHelp()).append("',")
					.append("'").append(o.getHarvest()).append("',")
					.append("'").append(o.getJobTitle()).append("')")
					.append(i == bytxt.size() - 1 ? ";" : ",");
		}
		System.out.println(sql.toString());
		return sql.toString();
	}


	static void insert() {
		Connection conn = DButils.getMySqlConnection();
		Statement stmt = null;
		try {
			String sql = getInsertSql();
			stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		insert();
	}
}
