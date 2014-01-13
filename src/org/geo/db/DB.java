package org.geo.db;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.geo.configure.CommonConfig;
import org.geo.configure.DBConfig;

public class DB {
	
	private static DataSource pool;
	
	static {
		
			/**
			 * context: context��һ��name-to-object�İ�(bindings),�������Ϊ��λ�Ŀ¼��
			 * ��ʹ��������Ŀ¼����ʱ���initial context�Ƕ��������ֿռ��������ڡ�
			 */
			Context env = null;
			try {
				
				env = ContextFactory.getContext();
				CommonConfig ccf=new  DBConfig();
				//�˿ں�
				String jdbc=ccf.getInitParameter("JDBC");
				//��ȡ���ӳ���Դ
				pool = (DataSource)env.lookup(jdbc);

				if(pool==null)
					System.err.println("'DBPool' is an unknown DataSource");
			} catch(NamingException ne) {
				ne.printStackTrace();
			}
	}

	public static DataSource getPool() {
		return pool;
	}	
}
