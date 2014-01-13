package org.geo.db;

import java.sql.Connection;
import java.sql.DriverManager;

import org.geo.configure.CommonConfig;
import org.geo.configure.DBConfig;

public class LocalDB {
	
	public static Connection getConnection(){

		Connection c=null;
		
		CommonConfig ccf=new  DBConfig();
		//���������ļ���ȡ��ͬ�������ݿ�����ӷ�ʽ
		String queryPort=ccf.getInitParameter("LOCAL_DB_TYPE");
		
		if(queryPort.equalsIgnoreCase("mysql")){
			c = getMySQLConnection();
			
		}else if(queryPort.equalsIgnoreCase("oracle")){
			c = getOracleConnection();
			
		}else{
			c = null;
		}
		
		return c;
	}
	/**
	 * @return
	 */
	public static Connection getMySQLConnection(){
		
		Connection c=null;
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			//��urlָ��������Դ�������� ��getConnection��������Ϊ��URL���û���������
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/address", "root", "mysql"); 
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return c;
	}
	/**
	 * @return
	 */
	public static Connection getOracleConnection(){
		
		Connection c=null;
		try { 
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance(); 
			//��urlָ��������Դ�������� ��getConnection�����Ӳ�������ΪURL��thin��ʽ���ӣ�...@ip:port:���ݿ����ƣ�,�û���������
			 c = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.116:1521:orcl", "GIS520200000000", "GIS520200000000"); 
			
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return c;
	}
	public static void main(String arg[]){
		LocalDB db=new LocalDB();
		System.out.println(db.getConnection().toString());
		
	}
}
