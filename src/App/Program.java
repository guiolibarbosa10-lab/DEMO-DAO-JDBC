package App;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.DAO.DaoFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) throws ParseException {
		
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SellerDAO sellerDao = DaoFactory.createSellerDao();
	Scanner sc = new Scanner(System.in);
//*
	//System.out.println("WANT TO CREATE A NEW SELLER? (Y/N)");
	//char resp = sc.next().charAt(0);
	//if (resp == 'Y') {
		//System.out.println("SET NEW DATA: ");
		//System.out.print("NAME: ");
	//	String name = sc.next();
	//	System.out.print("EMAIL: ");
	//	String email = sc.next();
	//	System.out.println("BIRTH DATE: ");
	//	Date bDay = sdf.parse(sc.next());
	//	System.out.println("BASE SALARY: ");
	//	Double baseSalary = sc.nextDouble();
	//	System.out.println("Department Id: ");
	//	int depId = sc.nextInt();
	//	System.out.println("Department Name: ");
	//	String depName = sc.next();
		
	//	Department department = new Department(depId, depName);
		
	//	Seller novoSeller = new Seller(null, name, email, bDay, baseSalary, department);
		//sellerDao.insert(novoSeller);
		
		//System.out.println("=== Teste #0: Insert acessando ===");
	//	System.out.println("INSERTED; NEW ID IS: " + novoSeller.getId());
	//	//System.out.println("SELLER NAME " + novoSeller.getName());
		
		
	//} else {
		//System.out.println("Try next time");
	
	
	
	
	
	System.out.println("=== Teste #1: seller findById ===");
	Seller seller = sellerDao.findById(3);
	
	System.out.println(seller);
	
	System.out.println();

	System.out.println("=== Teste #2: seller findByDep ===");
	Department department = new Department(2, null);
	List<Seller> list = sellerDao.findByDepartment(department);
	for (Seller obj : list) {
		System.out.println(obj);
	}
	
	System.out.println();
	
	System.out.println("=== Teste #3: seller findAll ===");
	list = sellerDao.findAll();
	for (Seller obj : list) {
		System.out.println(obj);
	}
	
	System.out.println();
	
	System.out.println("=== Teste #4: seller insert ===");
	Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
	sellerDao.insert(newSeller);
	System.out.println("INSERTED; NEW ID IS: " + newSeller.getId());
	
System.out.println();
	
	System.out.println("=== Teste #5: seller update ===");
	seller = sellerDao.findById(1);
	seller.setName("NEYMAR");
	seller.setEmail("neymarjr@gmail.com");
	sellerDao.update(seller);
	System.out.println("Update Completed");
	
	
	

	}

	}
