package main;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.net.URL;
//import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		System.out.println("������Ҫ��ȡͼƬ����ַ������Ҫ��httpͷ");
		Scanner input = new Scanner(System.in);
		String web = input.nextLine();
		
		System.out.println("������Ҫ�洢ͼƬ���ļ���·��");
		String outputPath = input.nextLine();
		input.close();
		
        List<String> list = TextHelper.get(web);
        FileHelper.downLoad(list, web, outputPath);
        System.out.println("��ȡ�ɹ�");
    }
}