package carInfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import carInfo.dto.CarDetail;
import carInfo.dto.CarInfo;
import carInfo.dto.CarPriceList;

public class CarInfoDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<CarInfo> getCarInfo(){
		ArrayList<CarInfo> list = new ArrayList<CarInfo>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// oracle DBMS로 접근하는 주소,계정ID,비번
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "java");
			// 쿼리문 문자열 준비
			String sql = "select ci.CINUM,"
					+ "ci.CINAME,"
					+ "cm.CMNAME,"
					+ "ci.CIPRICE "
					+ "from carInfo ci , carmaker cm "
					+ "where ci.ciNum = cm.cmNum";
			// DBMS에 날릴 기존 준비한 쿼리문 문자열을 집어넣는다
			pstmt = conn.prepareStatement(sql);
			// 쿼리 실행 하여 나온 결과를 어디에 집어넣을까??? (ResultSet)
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarInfo info = new CarInfo();
				
				info.setCiNum(rs.getInt(1));
				info.setCiName(rs.getString(2));
				info.setCiMaker(rs.getString(3));
				info.setCiPrice(rs.getInt(4));
				list.add(info);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public CarDetail getCarDetail(int inputNum) {
		// 리턴할 인스턴스 생성
		CarDetail result = new CarDetail();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// oracle DBMS로 접근하는 주소,계정ID,비번
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "java");
			// sql 준비
			String sql = "select\r\n" + 
					"cd.cdNum, cd.cdName, cm.CMNAME,\r\n" + 
					"cd.cdcc, cd.CDENGINETYPE, cd.CDWHEELINCH,\r\n" + 
					"ci.CIPRICE, cd.CDINPUTDATE\r\n" + 
					"from carDetail cd, carInfo ci, carMaker cm\r\n" + 
					"where cd.cdNum = ci.ciNum\r\n" + 
					"and cd.cdmaker = cm.CMNUM\r\n" + 
					"and ci.CINUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, inputNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result.setCiNum(rs.getInt(1));
				result.setCiName(rs.getString(2));
				result.setCiMaker(rs.getString(3));
				result.setCdcc(rs.getInt(4));
				result.setCdEngineType(rs.getString(5));
				result.setCdWheelInch(rs.getInt(6));
				result.setCiPrice(rs.getInt(7));
				result.setCdInputDate(rs.getString(8));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ArrayList<CarPriceList> getCarPriceList() throws Exception {
		ArrayList<CarPriceList> list = new ArrayList<CarPriceList>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// oracle DBMS로 접근하는 주소,계정ID,비번
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "java");
			// 쿼리문 문자열 준비
			String sql = "select plNum,plName,plPrice from priceList";
			// DBMS에 날릴 기존 준비한 쿼리문 문자열을 집어넣는다
			pstmt = conn.prepareStatement(sql);
			// 쿼리 실행 하여 나온 결과를 어디에 집어넣을까??? (ResultSet)
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarPriceList carPriceInfo = new CarPriceList();
				carPriceInfo.setPlNum(rs.getInt(1));
				carPriceInfo.setPlName(rs.getString(2));
				carPriceInfo.setPlPrice(rs.getInt(3));
				
				list.add(carPriceInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
			conn.close();
		}
		
		return list;
	}

	public void insertCarPriceList(String carName, int carPrice) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// oracle DBMS로 접근하는 주소,계정ID,비번
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "java");
			
			System.out.println("carName:"+carName);
			System.out.println("carPrice:"+carPrice);
			
			// 쿼리문 문자열 준비
			String sql = "insert into priceList (plNum,plName,plPrice)"
					+ "values (seq_priceList_plNum.nextval, ?, ?)";
			// DBMS에 날릴 기존 준비한 쿼리문 문자열을 집어넣는다
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, carName);
			pstmt.setInt(2, carPrice);
			
			// insert, update 쿼리 실행
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<CarDetail> getCarDetailList() {
		ArrayList<CarDetail> list = new ArrayList<CarDetail>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// oracle DBMS로 접근하는 주소,계정ID,비번
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "java");
			// sql 준비
			String sql = "select cd.cdNum, cd.cdname, cm.CMNAME, cd.cdcc, cd.cdEngineType, cd.cdWheelInch,ci.CIPRICE, cd.CDINPUTDATE\r\n" + 
					"from carDetail cd, CARMAKER cm, carInfo ci\r\n" + 
					"where cd.CDMAKER = cm.CMNUM\r\n" + 
					"and cd.CDNUM = ci.CINUM";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CarDetail result = new CarDetail();
				result.setCiNum(rs.getInt(1));
				result.setCiName(rs.getString(2));
				result.setCiMaker(rs.getString(3));
				result.setCdcc(rs.getInt(4));
				result.setCdEngineType(rs.getString(5));
				result.setCdWheelInch(rs.getInt(6));
				result.setCiPrice(rs.getInt(7));
				result.setCdInputDate(rs.getString(8));
				
				list.add(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
}
