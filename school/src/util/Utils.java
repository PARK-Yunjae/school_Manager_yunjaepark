package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Utils {
	private static final String CUR_PATH = System.getProperty("user.dir") + "//src//"+Utils.class.getPackageName()+"//";
	public static Scanner sc = new Scanner(System.in);
	
	// 에러시 콘솔에 남은 데이터 먹기
	public static void nextLine() {
		sc.nextLine();
	}
	// 문자 출력
	public static String getStr(String msg) {
		System.out.print(msg);
		return sc.next();
	}
	// 숫자 예외처리
	public static int getInt(String msg) {
		System.out.print(msg);
		int sel = -1;
		try {
			sel = sc.nextInt();
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요");
		}
		sc.nextLine();
		return sel;
	}
	// 파일 저장
	public static void fileSave(String data, String fileName) {
		try(FileWriter fw = new FileWriter(CUR_PATH + fileName)){
			fw.write(data);
			System.out.println(fileName +"저장 성공");
		}catch (Exception e) {
			System.out.println(fileName +"저장 실패");
		}
	}

	// 파일 로드
	public static String fileLoad(String fileName){
		String data = "";
		
		try(FileReader fr = new FileReader(CUR_PATH + fileName);
			BufferedReader br = new BufferedReader(fr)){
			while(true) {
				String line = br.readLine();
				if(line == null) {
					break;
				}
				data += line + "\n";
			}
			if(data.length()!= 0)
				data = data.substring(0, data.length()-1);		
			System.out.println(fileName + " 로드 성공");
		}catch (Exception e) {
			System.out.println(fileName + " 로드 실패");
		}
		return data;
	}
}
