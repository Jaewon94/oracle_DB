package member;
//	create table member (
//		    name varchar2(100),
//		    age number
//		);
	
	// 자바 빈즈 형식의 클래스 작성하기
	// 1) private 멤버 필드
	//	- 테이블의 컬럼을 클래스의 멤버 필드로 작성
	// 2) public getter/setter
	// 3) 기본 생성자
public class MemberDTO {	// DTO (Data Transger Object) : 데이터를 전송하기 위한 클래스 (객체)
							// VO (Value Object) : 값을 담기 위한 객체, DTO와 혼용해서 쓰인다.
	
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
}
