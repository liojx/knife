package liojx.kn.use.io;

import liojx.kn.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Optional;

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
	public static void main(String[] args) {
		FileUse fileUse = new FileUse();
//		fileUse.deleteSomeRepeatFile();
		fileUse.delJavaFile();
	}
}
