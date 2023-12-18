package vo;

public class Subject {
	private int stuNo;
	private String subName;
	private int score;

	public int getStuNo() {
		return stuNo;
	}

	public String getSubName() {
		return subName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Subject(String stuNo, String subName, String score) {
		this.stuNo = Integer.parseInt(stuNo);
		this.subName = subName;
		this.score = Integer.parseInt(score);
	}

	@Override
	public String toString() {
		return subName + " " + score + "점 ";
	}

	public String saveData() {
		return stuNo + "/" + subName + "/" + score + "\n";
	}
}
