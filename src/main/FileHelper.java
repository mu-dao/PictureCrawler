package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHelper {
	/**
     * ��������ͼƬ��ָ����Ŀ¼
     * @param webFileUrl
     * @param path
     * @return
     */
	public static void downLoad(List<String> list, String web, String outputPath){
		int pictureNumber = 0;
        if (list == null || list.size() == 0) return;
        for(String s : list){
        	String type = s.substring(s.lastIndexOf("."));//��ȡͼƬ��ʽ
        	if(s.indexOf("http://") == -1){
       			s = "http://" + web + s;
       		}
       		FileHelper.downloadWebFile(s, pictureNumber + type, outputPath);
       		pictureNumber++;   	
        }
	}
	
    public static  boolean downloadWebFile(String webFileUrl, String fileName,String parentPath){
        InputStream in = null;
        OutputStream out = null;
        try{
            //���·���к�������,�����ת�����
            Pattern pa = Pattern.compile("[\\u4e00-\\u9fa5]", Pattern.DOTALL);
            Matcher ma = pa.matcher(webFileUrl);
            String t = null;
            while(ma.find()){
                t = ma.group();
                webFileUrl = webFileUrl.replace(t, URLEncoder.encode(t, "UTF-8"));
            }
            //����URL
            URL url = new URL(webFileUrl);
            //������ 
            URLConnection con = url.openConnection();  
            //��������ʱΪ5s
            con.setConnectTimeout(5*1000);  
            //������  
            in = con.getInputStream();
           
            // 1K�����ݻ���  
            byte[] bs = new byte[1024];  
            // ��ȡ�������ݳ���  
            int len;  
            // ������ļ���  
            File sf = new File(parentPath);
            if(!sf.exists()) sf.mkdirs();
            out = new FileOutputStream(parentPath + fileName);
            // ��ʼ��ȡ
            while((len = in.read(bs)) != -1){
              out.write(bs, 0, len);
            }
            return true;
        } catch(Exception e){
            return false;
        } finally{
            try{
            	in.close();
                out.close();
            } catch(Exception e){
                return false;
            }
        }
    }
}