package org.thread.download;

import java.io.*;
import java.net.*;

import org.junit.Test;

public class MBaseDownloadTest {
    /**  
     * @param args  
     * @throws IOException  
     */  
    @Test public void download(){   
        // TODO Auto-generated method stub   
        MBaseDownload mydown = new MBaseDownload();   
        String path = "http://www.java3z.com/cwbwebhome/dir1/dir5/mon664.zip";   
           
        mydown.download("D:\\My Documents\\Downloads\\b", path);   
           
    } 
  
  
}
