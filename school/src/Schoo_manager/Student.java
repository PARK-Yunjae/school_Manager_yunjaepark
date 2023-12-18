package Schoo_manager;

public class Student {
	private int stuNo;
	private String stuName;
	private String stuId;
	
	public int getStuNo() {
		return stuNo;
	}

	public String getStuName() {
		return stuName;
	}

	public String getStuId() {
		return stuId;
	}

	public Student(String stuNo, String stuName, String stuId){
		this.stuNo = Integer.parseInt(stuNo);
		this.stuName = stuName;
		this.stuId = stuId;
	}

	@Override
	public String toString() {
	
		return stuNo + "\t"+ stuName +"\t" + stuId +"\n";
	}
	
	public String saveData() {
		return stuNo+"/"+stuName+"/"+stuId+"\n";
	}
}
