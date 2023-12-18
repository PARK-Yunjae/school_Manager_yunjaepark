package School_박윤재_스태틱;

import java.util.ArrayList;

public class StudentDAO {
	private ArrayList<Student> stuList;
	private int maxStuNo;

	
	public ArrayList<Student> getStuList() {
		return stuList;
	}

	public void setStuList(ArrayList<Student> stuList) {
		this.stuList = stuList;
	}

	public StudentDAO() {
		stuList = new ArrayList<Student>();
		maxStuNo = 1001;
	}

	// 학생 추가
	public void addStudent() {
		String id = Utils.getStr("ID : ");
		Utils.nextLine();
		if (stuIdValue(id) != -1) {
			System.out.println("중복 ID가 존재합니다");
			return;
		}
		String name = Utils.getStr("이름 : ");
		Utils.nextLine();
		stuList.add(new Student("" + (maxStuNo++), name, id));
	}

	// Id 중복
	private int stuIdValue(String id) {
		for (int i = 0; i < stuList.size(); i += 1) {
			if (stuList.get(i).getStuId().equals(id)) {
				return i;
			}
		}
		return -1;
	}

	// 학번 중복
	private int stuNoValue(int stuNo) {
		for (int i = 0; i < stuList.size(); i += 1) {
			if (stuList.get(i).getStuNo() == stuNo) {
				return i;
			}
		}
		return -1;
	}

	// 학생 삭제
	public void delStudent(SubjectDAO subDAO) {
		if (stuList.size() == 0) {
			System.out.println("데이터가 없습니다");
			return;
		}
		String id = Utils.getStr("ID : ");
		Utils.nextLine();
		int idx = stuIdValue(id);
		if (idx == -1) {
			System.out.println("ID가 존재하지 않습니다");
			return;
		}
		subDAO.delStudentAllSub(stuList.get(idx).getStuNo());
		stuList.remove(idx);
		System.out.println(id + " 삭제 완료");
	}

	// 학생 과목 추가
	public void addStudentSub(SubjectDAO subDAO) {
		int stuNo = Utils.getInt("학번 : ");
		int idx = stuNoValue(stuNo);
		if (idx == -1) {
			System.out.println("ID가 존재하지 않습니다");
			return;
		}
		subDAO.addStudentSub(stuNo);
	}

	// 학생 과목 삭제
	public void delStudentSub(SubjectDAO subDAO) {
		int stuNo = Utils.getInt("학번 : ");
		int idx = stuNoValue(stuNo);
		if (idx == -1) {
			System.out.println("ID가 존재하지 않습니다");
			return;
		}
		subDAO.delStudentSub(stuNo);
	}

	// 전체 학생 목록 - 점수 오름차순
	public void printStudent(SubjectDAO subDAO) {
		ArrayList<Double> score = new ArrayList<Double>();
		ArrayList<Student> copy = new ArrayList<Student>();

		for (int i = 0; i < stuList.size(); i += 1) {
			copy.add(stuList.get(i));
		}

		for (int i = 0; i < copy.size(); i += 1) {
			int cnt = 0;
			for (int k = 0; k < subDAO.getSubList().size(); k += 1) {
				if (stuList.get(i).getStuNo() == subDAO.getSubList().get(k).getStuNo()) {
					if (cnt == 0) {
						score.add(1.0 * subDAO.getSubList().get(k).getScore());
					} else {
						score.set(i, score.get(i) + subDAO.getSubList().get(k).getScore());
					}
					cnt += 1;
				}
			}
			if(cnt == 0) {
				score.add(0.0);
			}
			else {
				score.set(i, cnt == 0 ? 0 : 1.0 * score.get(i) / cnt);
			}
		}

		for (int i = 0; i < score.size(); i += 1) {
			for (int k = i; k < score.size(); k += 1) {
				if (score.get(i) < score.get(k)) {
					double temp = score.get(i);
					score.set(i, score.get(k));
					score.set(k, temp);

					Student data = copy.get(i);
					copy.set(i, copy.get(k));
					copy.set(k, data);
				}
			}
		}

		System.out.println("학번\t이름\t점수");
		System.out.println("------------------------");
		for (int i = 0; i < copy.size(); i += 1) {
			System.out.print(copy.get(i));
			for (int k = 0; k < subDAO.getSubList().size(); k += 1) {
				if (copy.get(i).getStuNo() == subDAO.getSubList().get(k).getStuNo()) {
					System.out.print(subDAO.getSubList().get(k).toString());
				}
			}
			if (score.get(i) > 0) {
				System.out.printf("\n%.2f\n", score.get(i));
			}
			System.out.println("------------------------");
		}
	}

	// 파일 저장
	public String dataToFile() {
		String data = "";

		for (int i = 0; i < stuList.size(); i += 1) {
			data += stuList.get(i).saveData();
		}
		data = data.substring(0, data.length() - 1);

		return data;
	}

	// 파일 불러오기
	public void fileToData(String data) {
		if(data.length() == 0) return;
		String[] datas = data.split("\n");
		for (int i = 0; i < datas.length; i += 1) {
			String[] info = datas[i].split("/");
			stuList.add(new Student(info[0], info[1], info[2]));
			if (maxStuNo < Integer.parseInt(info[0])) {
				maxStuNo = Integer.parseInt(info[0]);
			}
		}
		maxStuNo += 1;
	}
}
