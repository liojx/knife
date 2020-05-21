package liojx.kn.io;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.List;

/**
 * @Author: liaosijun
 * @Time: 2020/5/21 14:48
 */
@Slf4j
@Data
public class FileUtils {


	public static File createFileByPath(String path) {
		try {
			File file = new File(path);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

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

	@Data
	@ToString
	public static class Response{
		public int dirCount;
		public int fileCount;
		public List<File> allFiles = Lists.newArrayList();
		public List<File> files = Lists.newArrayList();
	}
}
