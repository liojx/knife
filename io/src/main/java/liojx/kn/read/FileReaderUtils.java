package liojx.kn.read;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.List;

/**
 * @author: liaosijun
 * @since: 2021/7/26 11:10
 * @description:
 */
public class FileReaderUtils {

	/**
	 * 获取每一行数据
	 * @param filePath
	 * @return
	 */
	public static List<String> fileToString(String filePath){
		File file = new File(filePath);
		BufferedReader reader;
		StringBuffer sb = new StringBuffer();
		List<String> list = Lists.newArrayList();
		try {
			FileInputStream fis = new FileInputStream(file);
			InputStreamReader isr = new InputStreamReader(fis, "GBK");
			reader = new BufferedReader(isr);
			String line;
			while((line = reader.readLine()) != null){
				sb.append(line).append("\\n");
				list.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
