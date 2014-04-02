package org.thread.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MBaseDownload implements IDownload{
	/**  
     * 文件后缀名  
     *   
     * @param path  
     * @return  
     */  
    public String getFileExtName(String path) {   
        return path.substring(path.lastIndexOf("."));   
    }

	/* (non-Javadoc)
	 * 默认采用单线程下载
	 */
	@Override
	public void download(String saveFilePath, String url) {
		try {   
            // 要写入的文件   
            File file = new File(saveFilePath + getFileExtName(url));
            FileOutputStream fout = new FileOutputStream(file);
            URL ul = new URL(url);   
            URLConnection conn = ul.openConnection();   
            conn.setConnectTimeout(2000);// 请求超时时间   
            // int len = conn.getContentLength();   
            InputStream in = conn.getInputStream();   
            byte[] by = new byte[1024];   
            int temp = 0;   
            while ((temp = in.read(by)) != -1) {
            	fout.write(by,0,temp);   
            }
            fout.flush();
            fout.close();   
            in.close();   
        } catch (MalformedURLException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
        } catch (IOException e) {   
            // TODO Auto-generated catch block   
            e.printStackTrace();  
        }   
		
	}
}
