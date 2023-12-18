package Schoo_manager;

import java.util.ArrayList;
import java.util.Random;

public class SubjectDAO {
	private ArrayList<Subject> subList;
	private Random rd;
	
	public ArrayList<Subject> getSubList() {
		return subList;
	}

	public int size() {
		return subList.size();
	}

	public SubjectDAO() {
		subList = new ArrayList<Subject>();
		rd = new Random();
		new Random();
	}

	// 학생 과목 중복 확인
	private int subNoValue(int stuNo, String subName) {
		for (int i = 0; i < subList.size(); i += 1) {
			if (subList.get(i).getStuNo() == stuNo && subList.get(i).getSubName().equals(subName)) {
				return i;
			}
		}
		return -1;
	}

	// 학생 과목 추가
	public void addStudentSub(int stuNo) {
		String subName = Utils.getStr("과목 : ");
		int idx = subNoValue(stuNo, subName);
		int score = rd.nextInt(50) + 50;
		if (idx != -1) {
			subList.get(idx).setScore(score);
			return;
		}
		subList.add(new Subject(stuNo + "", subName, score + ""));
	}

	// 학생 과목 삭제
	public void delStudentSub(int stuNo) {
		if (subList.size() == 0) {
			System.out.println("과목 데이터가 존재하지 않스빈다");
			return;
		}
		String subName = Utils.getStr("과목 : ");
		int idx = subNoValue(stuNo, subName);
		if (idx == -1) {
			System.out.println("과목이 존재하지 않습니다");
			return;
		}
		subList.remove(idx);
	}

	// 학생 탈퇴 시 과목 all 삭제
	public void delStudentAllSub(int stuNo) {
		if (subList.size() == 0) {
			return;
		}
		for (int i = 0; i < subList.size(); i += 1) {
			if (subList.get(i).getStuNo() == stuNo) {
				subList.remove(i);
				i -= 1;
			}
		}
	}

	// 과목이름 입력 받고 학생 이름 오름차순으로 출력
	public void printSubject(StudentDAO stuDAO) {
		String subName = Utils.getStr("과목 : ");
		boolean ck = true;
		int cnt = 0;
		// 과목이 존재하는 학번 카운트를 찾고
		for (int i = 0; i < subList.size(); i += 1) {
			if (subList.get(i).getSubName().equals(subName)) {
				cnt += 1;
				ck = false;
			}
		}
		if (ck) {
			System.out.println("과목이 존재하지 않습니다");
			return;
		}
		ArrayList<Student> copy = new ArrayList<Student>();
		for (int i = 0; i < subList.size(); i += 1) { // 과목 리스트에서
			if (subList.get(i).getSubName().equals(subName)) { // 과목이름이 같은 학번을 찾아서
				for(int k=0 ; k<stuDAO.getStuList().size() ; k+=1) {
					if(subList.get(i).getStuNo() == stuDAO.getStuList().get(k).getStuNo()) { // 다시 학생 리스트에서 학번과 일치하는걸 찾아서 저장
						copy.add(stuDAO.getStuList().get(k));
						break;
					}
				}
			}
		}

		for (int i = 0; i < cnt; i += 1) {
			for (int k = 0; k < cnt; k += 1) {
				if (copy.get(i).getStuName().charAt(0) < copy.get(k).getStuName().charAt(0)) {
					Student info = copy.get(i);
					copy.set(i, copy.get(k));
					copy.set(k , info);
				}
			}
		}

		for (int i = 0; i < cnt; i += 1) {
			System.out.print(copy.get(i));
		}
	}

	// 파일 저장
	public String dataToFile() {
		String data = "";

		for (int i = 0; i < subList.size(); i += 1) {
			data += subList.get(i).saveData();
		}
		data = data.substring(0, data.length() - 1);
		System.out.println();
		return data;
	}

	// 파일 불러오기
	public void fileToData(String data) {
		if(data.length() == 0) return;
		String[] datas = data.split("\n");
		for (int i = 0; i < datas.length; i += 1) {
			String[] info = datas[i].split("/");
			subList.add(new Subject(info[0], info[1], info[2]));
		}
	}
}
