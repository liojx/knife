package liojx.kn.db;

import java.sql.*;
import java.util.List;

/**
 * @Author: liaosijun
 * @Time: 2020/12/6 18:43
 */
public class JDBCUtils {


//	static void batchInsert(List<FileVo> list){
//		Connection conn = DBtool.getConn();
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		try{
//			String sql = "insert into t_file (detect_directory,file_path,modify_time,save_time,file_directory_path,size_,type_,exist_) values" +
//					"(?,?,?,?,?,?,?,?)";
//			pst  = conn.prepareStatement(sql);
//			if(list != null && list.size()>0){
//				for(int i=0;i < list.size();i++){
//					FileVo vo = list.get(i);
//					pst.setString(1, vo.getDetectDirectory());
//					pst.setString(2, vo.getFilePath());
//					pst.setTimestamp(3,new Timestamp(vo.getLastModifyTime().getTime()));
//					pst.setTimestamp(4,new Timestamp(vo.getSaveTime().getTime()));
//					pst.setString(5, vo.getFileDirectoryPath());
//					pst.setLong(6, vo.getSize() == null ? 0:vo.getSize());
//					pst.setString(7, vo.getType());
//					pst.setString(8, vo.getExists());
//					pst.addBatch();
//				}
//			}
//			pst.executeBatch();
//		}catch(Exception e){
//			e.printStackTrace();
//			System.out.println( e.getMessage());
//		}finally{
//			try {
//				if(rs!=null)rs.close();
//				if(pst!=null)pst.close();
//				if(conn!=null)conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

}
