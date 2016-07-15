package main;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		System.out.println("请输入要爬取图片的网址，不需要加http头");
		Scanner input = new Scanner(System.in);
		String web = input.nextLine();
		
		System.out.println("请输入要存储图片的文件夹路径");
		String outputPath = input.nextLine();
		input.close();
		
        List<String> list = TextHelper.get(web);
        FileHelper.downLoad(list, web, outputPath);
        System.out.println("爬取成功");
    }
}