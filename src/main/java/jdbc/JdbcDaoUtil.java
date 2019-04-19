package jdbc;
//package com.songsf.learn.util;
//
//import com.chehaha.callcenter.config.CallCenterConfig;
//import com.chehaha.common.BizException;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class JdbcDaoUtil {
//
//    //建立连接
//    public static Connection getConnect(){
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			throw new Exception("123","加载驱动出错");
//		}
//		//连接参数
//		String url =CallCenterConfig.getUrl();
//		String user = CallCenterConfig.getUsername();
//		String pwd = CallCenterConfig.getPassword();
//		Connection conn;
//		try {
//			conn = DriverManager.getConnection(url, user, pwd);
//		} catch (SQLException e) {
//			throw new BizException("123","连接数据库出错");
//		}
//		return conn;
//	}
//
//	//获取操作sql的PreparedStatement对象
//	public static PreparedStatement  getStatement(Connection conn, String sql){
//		PreparedStatement ps = null;
//		try {
//			ps = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			throw new BizException("123","建立操作sql对象失败");
//		}
//		return ps;
//	}
//
//	//关闭连接 最优经验是按照ResultSet，Statement，Connection的顺序执行close；
//	public static void close(Connection con,PreparedStatement sta,ResultSet rs) {
//		try {
//			if(rs !=null) rs.close();
//			if(sta !=null)sta.close();
//			if(con !=null)con.close();
//		} catch (Exception e) {
//			throw new BizException("123","关闭连接失败");
//		}
//	}
//
//	//创建jdbc连接,执行查询sql语句,
//	public static <T> List<T> getResultSet(String sql, Class<T> clazz)  {
//		List<T> list = new ArrayList<>();
//		Connection conn = getConnect();
//		PreparedStatement ps = getStatement(conn,sql);
//		ResultSet rs = null;
//		try {
//			//执行查询
//			rs = ps.executeQuery();
//			if(rs == null) {
//				return null;
//			}
//		} catch (SQLException e) {
//			throw new BizException("123","执行查询语句失败");
//		}
//		ResultSetMapper resultSetMapper = new ResultSetMapper<>();
//		list = resultSetMapper.mapRersultSetToObject(rs, clazz);
//		close(conn, ps, rs);
//		return list;
//	}
//
//	//修改添加删除
//    public  static boolean  update(String sql)  {
//		Connection conn = getConnect();
//		PreparedStatement ps  = getStatement(conn,sql);
//		boolean flag = true;
//		int i = 0;
//		try {
//			i = ps.executeUpdate();
//		} catch (SQLException e) {
//			throw new BizException("123","执行非查询语句失败");
//		}
//		if(i == 0) {
//			flag = false;
//		}
//		close(conn,ps,null);
//		return flag;
//	}
//
//
//}
