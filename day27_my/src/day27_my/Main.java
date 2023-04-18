package day27_my;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	 public static void main(String[] args) {
		ProductSalesDAO dao = new ProductSalesDAO();
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		list = dao.allResult();
		for (HashMap<String, Object> hashMap : list) {
			System.out.println(hashMap);
		}
		
		System.out.println();
		list = dao.registeredResult();
		for (HashMap<String, Object> hashMap : list) {
			System.out.println(hashMap);
		}
		
		System.out.println();
		list = dao.unregisteredResult();
		for (HashMap<String, Object> hashMap : list) {
			System.out.println(hashMap);
		}
	}
}

