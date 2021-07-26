package liojx.kn.io;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * @Author: liaosijun
 * @Time: 2020/5/21 14:48
 */
@Slf4j
@Data
public class FileUtils {

	/**
	 * 创建path的文件
	 * @param path
	 * @return
	 */
	public static File createFileByPath(String path) {
		try {
			File file = new File(path);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 列出所有文件，封装到response对象中
	 * @param rootFile       根文件
	 * @param response      接收对象
	 */
	public static void listAllFilesByRootFile(File rootFile, Response response) {
		try {
			if (rootFile.isDirectory()) {
				File[] filelist = rootFile.listFiles();
				for (int i = 0; i < filelist.length; i++) {
					File f = filelist[i];
					if (f.isFile()) {
						response.getAllFiles().add(f);
						response.setFileCount(response.getFileCount() + 1);
						response.getFiles().add(f);
					}
					listAllFilesByRootFile(f, response);
				}
				response.setDirCount(response.getDirCount() + 1);
				response.getAllFiles().add(rootFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取每一行数据
	 * @param filePath
	 * @return
	 */
	public static List<String> fileToString(String filePath){
		File file = new File(filePath);
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		List<String> list = Lists.newArrayList();
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "GBK");
			reader = new BufferedReader(isr);
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line + "\\n");
				list.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("result -----> \n" + sb.toString());
		return list;
	}

	@Data
	@ToString
	public static class Response{
		public int dirCount;
		public int fileCount;
		public List<File> allFiles = Lists.newArrayList();
		/**
		 * @Description: 装所有文件
		 */
		public List<File> files = Lists.newArrayList();
	}
}
