package org.geo.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.geo.configure.CommonConfig;
import org.geo.configure.DBConfig;

/**
 * @author lenovo
 * ����common.properties�����ļ��������ֶ�Ϊwebserver
 *
 */
public class ContextFactory {
	private static Context env;
	
	static {
		
			/**
			 * context: context��һ��name-to-object�İ�??bindings),�������Ϊ��λ�Ŀ¼??
			 * ��ʹ��������Ŀ¼����ʱ���initial context�Ƕ��������ֿռ���������??
			 */
		try {
			CommonConfig ccf=new  DBConfig();
			//�˿ں�
			String queryPort=ccf.getInitParameter("WEBSERVER");
			
			if(queryPort==null){
				env = (Context) new InitialContext().lookup("java:comp/env");
				
			}else if(queryPort.equalsIgnoreCase("tomcat")){
				env = (Context) new InitialContext().lookup("java:comp/env");
				
			}else if(queryPort.equalsIgnoreCase("weblogic")){
				env = (Context) new InitialContext();
				
			}
			
		} catch(NamingException ne) {
			ne.printStackTrace();
		}
	}

	public static Context getContext() {
		return env;
	}

}
