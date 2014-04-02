package org.thread.download;

import java.io.IOException;

import org.junit.Test;

public class MultiThreadDownloadTest {
	@Test 
	public void download(){
		MultiThreadDownload mydown = new MultiThreadDownload();   
	    String path = "http://www.java3z.com/cwbwebhome/dir1/dir5/mon664.zi";   
	      
	    mydown.download("D:\\My Documents\\Downloads\\b", path);   
	    
	}
}
