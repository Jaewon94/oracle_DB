package quiz;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

// 전체 목록 
// 검색	(이름으로 검색, 포함된다면 모두 출력, 정렬되면 다른 순서로 출력)
// 추가	(상품 등록)
// 삭제	(등록된 상품 코드 제거)
// 수정	(등록된 상품 코드 제거)
// 정렬	(날짜순 정렬, 고유번호 정렬(기본값), 수량 기준 정렬, 상품명 정렬)
// 종료

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> list;
		ProductDTO dto = null;
		String keyword;
		int row, idx;

		int menu;

		while (true) {
			System.out.println("1. 전체 목록 검색");
			System.out.println("2. 단일 품목 검색");
			System.out.println("3. 추가");
			System.out.println("4. 수정");
			System.out.println("5. 삭제");
			System.out.println("6. 전체 삭제");
			System.out.println("7. 정렬");
			System.out.println("0. 종료");
			System.out.print("선택 >> ");

			menu = Integer.parseInt(sc.nextLine());

			switch (menu) {
			case 1:
				list = dao.selectAll();
				list.forEach(d -> System.out.println(d));
				break;
			case 2:
				System.out.print("검색어를 입력하세요 : ");
				keyword = sc.nextLine();

				// select * from product where name like '%keyword%'
				list = dao.select(keyword);
				list.forEach(d -> System.out.println(d));
				break;
			case 3:
				// dao.insert(idx, name, price, expiryDate, memo); 변수가 많아지면 이렇게 하기 어려워짐
				dto = makeDTO(sc);
				row = dao.insert(dto);
				System.out.println(row != 0 ? "추가 성공" : "추가 실패");
				break;
			case 4:
				
				break;
			case 5:
				System.out.print("삭제할 idx번호 : ");
				idx = Integer.parseInt(sc.nextLine());
				row = dao.delete(idx);
				System.out.println(row != 0 ? "삭제 성공" : "삭제 실패");
				break;
			case 6:

				break;
			case 7:

				break;
			case 0:

				break;

			default:
				System.out.println("메뉴를 잘못 입력하였습니다.");
				break;
			}
		}
	}
	static ProductDTO makeDTO(Scanner sc) {
		System.out.println("신규 상품을 생성합니다.");
		ProductDTO dto = new ProductDTO();
		System.out.print("상품 번호 : ");
		dto.setIdx(Integer.parseInt(sc.nextLine()));
		
		System.out.println("상품 이름 : ");
		dto.setName(sc.nextLine());
		
		System.out.print("상품 가격 : ");
		dto.setPrice(Integer.parseInt(sc.nextLine()));
		try {
		System.out.print("상품 유통기한 : ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String inputDate = sc.nextLine();	// 문자열로 입력받는다.
		java.util.Date date = sdf.parse(inputDate);
		dto.setExpiryDate(new Date(date.getTime()));	// java.util.Date의 long을 추출하여 sql.Date로 변환
														// 문자열에서 서식에 맞는 java.util.Date로 변환
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		System.out.print("상품 설명 :");
		dto.setMemo(sc.nextLine());
		
		return dto;
	}
}
