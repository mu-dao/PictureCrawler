package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextHelper {
	//获取网页源代码，调用getTextImageSrc从源代码中获取图片链接地址
	public static List<String> get(String web){
        InputStreamReader reader = null;
        BufferedReader in = null;
        try {
            //URL url = new URL("http://" + web + "/?p=" + i);
        	URL url = new URL("http://" + web);
        	URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", "MSIE");
            connection.setConnectTimeout(10000);
            reader = new InputStreamReader(connection.getInputStream(), "utf-8");
            in = new BufferedReader(reader);
            String line = null; // 每行内容
            int lineCount = 0; //统计读了多少行
            StringBuilder content = new StringBuilder();//全部内容
            
            while ((line = in.readLine()) != null) {
                content.append(line);
                lineCount++;
            }
            if(lineCount >= 1){
            	return TextHelper.getTextImageSrc(content.toString());
            }
            return null;
        } catch(Exception e){
            return null;
        } finally{
            try{
                in.close();
                reader.close();
            } catch(Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isNullOrEmpty(Object object){
        return object == null || object.equals("");
    }
    /**
     * 获取富文本内容中的图片链接地址
     */
    public static List<String> getTextImageSrc(String text) {
        if (TextHelper.isNullOrEmpty(text)) return null;
        
        String regex = "<\\s*[I|i][m|M][g|G][^>]*>";
        Pattern pa = Pattern.compile(regex);
        Matcher ma = pa.matcher(text);
        List<String> list = new ArrayList<String>();
        
        while(ma.find()){
            list.add(ma.group());
        }
        //有图片文件
        if(list.size() != 0){
            List<String> imgSrcList = null;
            String str = null;
            for(String s : list){
            	ma = Pattern.compile("[s|S][R|r][c|C]=[\"|'](.*?)[\"|']").matcher(s);
                if(ma.find()){
                    str = ma.group();
                    if(imgSrcList == null)	imgSrcList = new ArrayList<String>();
                }else str = null;
                
                if(str != null){
                    str = str.replaceAll("[s|S][R|r][c|C]=[\"|']", "").replaceAll("[\"|']", "");
                    imgSrcList.add(str);
                }
            }
            if(imgSrcList != null && imgSrcList.size() != 0) return imgSrcList;
            return null;
        }
        return null;
    }
}