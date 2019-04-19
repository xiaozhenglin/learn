package jdbc;
//package com.songsf.learn.util;
//
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import org.apache.commons.beanutils.BeanUtils;
//
// 
//public class ResultSetMapper<T> {
//	@SuppressWarnings("unchecked")
//	public List<T> mapRersultSetToObject(ResultSet rs, Class outputClass) {
//		List<T> outputList = new ArrayList<>();
//		try {
//			// make sure resultset is not null
//			if (rs != null) {
//				// check if outputClass has 'Entity' annotation
//				if (outputClass.isAnnotationPresent(Entity.class)) {
//					// get the resultset metadata
//					ResultSetMetaData rsmd = rs.getMetaData();
//					// get all the attributes of outputClass
//					Field[] fields = outputClass.getDeclaredFields();
//					while (rs.next()) {
//						//创建类实例
//						T bean = (T) outputClass.newInstance();
//						for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {
//							// getting the SQL column name
//							String columnName = rsmd.getColumnName(_iterator + 1);
//							// reading the value of the SQL column
//							Object columnValue = rs.getObject(_iterator + 1);
//							// iterating over outputClass attributes to check if
//							// any attribute has 'Column' annotation with
//							// matching 'name' value
//							for (Field field : fields) {
//									Column column = field.getAnnotation(Column.class);
//									if (column.name().equalsIgnoreCase(columnName) && columnValue != null) {
//										BeanUtils.setProperty(bean, field.getName(), columnValue);
//										break;
//									}
//							}
//						}
//						if (outputList == null) {
//							return null;
//						}
//						outputList.add(bean);
//					}
//				} else {
//					//非实体类型
//					if(outputClass.getName().equals("java.lang.Integer") || outputClass.getTypeName().equals("java.lang.String")) {
//						while(rs.next()){
//							Object object = rs.getObject(1);
//							outputList.add((T)object.toString());
//						}
//					}
//				}
//				return outputList;
//			} 
//			return null;
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			e.printStackTrace();
//		}
//		return outputList;
//	}
//}
