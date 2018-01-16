package carInfo.main;

import java.util.ArrayList;
import java.util.Scanner;

import carInfo.dao.CarInfoDAO;
import carInfo.dto.CarDetail;
import carInfo.dto.CarInfo;
import carInfo.dto.CarPriceList;

public class CarInfoMain {
	public static void main(String[] args) throws Exception {
		// 입력 (int)
		Scanner sc = new Scanner(System.in);
		// 입력 (String)
		Scanner scStr = new Scanner(System.in);
		System.out.println("다음 메뉴를 선택하세요");
		System.out.println("1. 자동차 정보 보기  2. 가격정보 입력  3. 자동차 정보 수정");
		int input = sc.nextInt();
		
		// dao 객체 인스턴스 생성
		CarInfoDAO dao = new CarInfoDAO();
		
		if(input == 1) {
			// 전체 자동차 정보를 담을 ArrayList 생성
			ArrayList<CarInfo> list = dao.getCarInfo();
			
			// 출력
			System.out.println(input + ". 자동차 정보 보기를 선택하셨습니다.");
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).getCiNum() 
						+ ", " + list.get(i).getCiName()
						+ ", " + list.get(i).getCiMaker()
						+ ", " + list.get(i).getCiPrice() + "만원");
				System.out.println();
			}
			// 상세보기 입력
			System.out.println("상세보기할 번호를 입력하세요.");
			int inputNum = sc.nextInt();
			CarDetail detail = dao.getCarDetail(inputNum);
			System.out.println(detail.toString());
		} else if (input == 2) {
			// DB에 접속하여 전체 가격 정보를 뿌리는 dao 기능 추가
			ArrayList<CarPriceList> cpl = dao.getCarPriceList();
			
			for (int i = 0; i < cpl.size(); i++) {
				System.out.println(cpl.get(i).toString());
			}
			
			System.out.println("더 입력하시겠습니까? (Y/N)");
			String inputStr = scStr.nextLine();
			
			if(inputStr.trim().equalsIgnoreCase("Y")) {
				// 인서트
				System.out.println("차명 : ");
				String carName = scStr.nextLine();
				System.out.println("가격 : ");
				int carPrice = sc.nextInt();
				
				// db에 인서트
				dao.insertCarPriceList(carName,carPrice);
				
			} else if(inputStr.trim().equalsIgnoreCase("N")) {
				// 종료
			}
			
		} else if(input == 3) {
			System.out.println("3번을 선택하셨습니다 주인님");
			// CarDetail의 전체 정보를 불러오기
			ArrayList<CarDetail> list = dao.getCarDetailList();
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).toString());
			}
			
			System.out.println("어떤 항목을 수정하시겠습니까? :");
			int inputNum = sc.nextInt();
			
			System.out.println("차명을 입력하세요 : ");
			String carName = scStr.nextLine();
			System.out.println("제조사를 입력하세요 : ");
			String carMaker = scStr.nextLine();
			System.out.println("배기량을 숫자만 입력하세요 : ");
			int cc = sc.nextInt();
			System.out.println("엔진타입을 입력하세요 : ");
			String engineType = scStr.nextLine();
			System.out.println("휠 인치를 숫자로 입력하세요 : ");
			int wheelInch = sc.nextInt();
			System.out.println("가격을 숫자로 입력하세요 : ");
			int price = sc.nextInt();
			
//			System.out.println(carName);
//			System.out.println(carMaker);
//			System.out.println(cc);
//			System.out.println(engineType);
//			System.out.println(wheelInch);
//			System.out.println(price);
			
			// 선택 항목 수정
			dao.updateCarDetail(inputNum,carName,carMaker,cc,engineType,wheelInch);
			
			ArrayList<CarDetail> list2 = dao.getCarDetailList();
			for (int i = 0; i < list2.size(); i++) {
				System.out.println(list2.get(i).toString());
			}
			System.out.println("===========완료===============");
			
			
		}
		
		else {
			System.out.println("다른 입력입니다. 입력한 번호 : " + input);
		}
		
	}

}
