package controller;

import util.Utils;
import dao.StudentDAO;
import dao.SubjectDAO;

public class Controller {
	private StudentDAO stuDAO;
	private SubjectDAO subDAO;

	public StudentDAO getStuDAO() {
		return stuDAO;
	}
	
	public SubjectDAO getSubDAO() {
		return subDAO;
	}
	
	public Controller() {
		stuDAO = new StudentDAO();
		subDAO = new SubjectDAO();
		stuDAO.fileToData(Utils.fileLoad("student.txt"));
		subDAO.fileToData(Utils.fileLoad("subject.txt"));
	}


	private void mainMenu() {
		System.out.println("[1]학생추가"); // 학번(1001) 자동증가 : 학생id 중복 불가
		System.out.println("[2]학생삭제"); // 학생 id 입력후 삭제 과목도 같이 삭제
		System.out.println("[3]과목추가"); // 학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
		System.out.println("[4]과목삭제"); // 학번 입력후 과목삭제
		System.out.println("[5]전체학생목록"); // 점수로 출력(점수 오름차순)
		System.out.println("[6]과목별학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력 (오름차순 이름순)
		System.out.println("[7]파일저장");
		System.out.println("[8]파일로드");
		System.out.println("[0]종료");
	}

	public void run() {
		while (true) {
			mainMenu();
			int sel = Utils.getInt("선택 : ");
			// 학생 추가
			if (sel == 1) {
				stuDAO.addStudent();
			} 
			// 학생 삭제
			else if (sel == 2) {
				stuDAO.delStudent(getSubDAO());
			} 
			// 과목 추가
			else if (sel == 3) {
				stuDAO.addStudentSub(getSubDAO());
			} 
			// 과목 삭제
			else if (sel == 4) {
				stuDAO.delStudentSub(getSubDAO());
			} 
			// 전체 학생 목록
			else if (sel == 5) {
				stuDAO.printStudent(getSubDAO());
			} 
			// 과목별 학생 목록
			else if (sel == 6) {
				subDAO.printSubject(getStuDAO());
			} 
			// 파일 저장
			else if (sel == 7) {
				Utils.fileSave(stuDAO.dataToFile(), "student.txt");
				Utils.fileSave(subDAO.dataToFile(), "subject.txt");
			} 
			// 파일 로드
			else if (sel == 8) {
				stuDAO.fileToData(Utils.fileLoad("student.txt"));
				subDAO.fileToData(Utils.fileLoad("subject.txt"));
			}
			// 종료
			else if (sel == 0) {
				Utils.sc.close();
				System.out.println("종료");
				break;
			}
			// 선택 오류
			else {
				System.out.println("메뉴 선택 범위 오류");
			}
		}
	}
}
