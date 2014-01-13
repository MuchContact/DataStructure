package org.geo.configure;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

/**
 * ���ö�ȡ��
 * 
 * @author lemon
 * 
 */
public class CommonConfig {
	/**
	 * �����ļ���
	 */
	private String propFileName;
	/**
	 * �����ļ���ȡ���
	 */
	private Properties properties = null;

	/**
	 * ��ʼ�������ļ�����
	 */
	public CommonConfig() {
		this.propFileName = "common.properties";
		getPropInputStream();
	}

	/**
	 * ��ʼ�������ļ�����
	 * 
	 * @param propFileName
	 *            *.properties�����ļ�����
	 */
	public CommonConfig(String propFileName) {
		this.propFileName = propFileName;
		getPropInputStream();
	}

	/**
	 * ��ȡ�����ļ��еĲ���
	 * 
	 * @param parameterName
	 *            ��Ҫ��ȡ�Ĳ�������
	 * @return �������ò���
	 */
	public String getInitParameter(String parameterName) {
		String strRlt = "";
		byte[] temp = null;
		try {
			String strReturnValue = properties.getProperty(parameterName);
			// �ж�����ֵ�Ƿ�Ϊnull modify by cAn
			if (strReturnValue != null) {
				temp = strReturnValue.getBytes("ISO-8859-1");
				strRlt = new String(temp, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return strRlt;
	}

	/**
	 * ���������ļ��еĲ���
	 * 
	 * @param parameterName
	 *            ��������
	 * @param parameterValue
	 *            ��Ӧ������ֵ
	 */
	public synchronized void setInitParameter(String parameterName, String parameterValue) {
		properties.setProperty(parameterName, parameterValue);
	}

	/**
	 * ���� Properties ���е������б�����Ԫ�ضԣ�д�������
	 * 
	 * @return
	 */
	public synchronized boolean storeInitParameter() {
		try {
			String absolutePath = URLDecoder.decode(getClass().getResource("/").getPath()+propFileName,"utf-8");
			OutputStream fos = new FileOutputStream(absolutePath);
			properties.store(fos, "## stored ###");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * �Ƴ������ļ��еĲ���
	 * 
	 * @param parameterName
	 *            ��������
	 */
	public synchronized void removeInitParameter(String parameterName) {
		properties.remove(parameterName);
	}

	/**
	 * 
	 */
	private void getPropInputStream() {
		try {
			properties = new Properties();
			InputStream is = getClass().getClassLoader().getResourceAsStream(
					propFileName);
			properties.load(is);
			is.close();
		} catch (FileNotFoundException e) {
			System.err.println("�����ļ�"+this.propFileName+"�Ҳ�����");
			e.getMessage();
		} catch (IOException e) {
			System.err.println("��ȡ�����ļ�"+this.propFileName+"����");
			e.getMessage();
		}
	}
}
