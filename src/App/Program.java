package App;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
	SellerDAO sellerDao = DaoFactory.createSellerDao();
	
	System.out.println("=== Teste #1: seller findById ===");
	Seller seller = sellerDao.findById(3);
	
	System.out.println(seller);
	

	System.out.println("=== Teste #1: seller findByDep ===");
	Department department = new Department(2, null);
	List<Seller> list = sellerDao.findByDepartment(department);
	for (Seller obj : list) {
		System.out.println(obj);
	}
	
	
	}

}
