package model.DAO.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDAOJDBC implements SellerDAO {
	
	private Connection conn;
	
	public SellerDAOJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
							+ "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id "
							+ "WHERE seller.Id = ?");
					st.setInt(1, id);
					rs = st.executeQuery();
					if (rs.next()) {
						Department dep = instantiateDepatment(rs);
						Seller obj = instantiateSeller(rs, dep);
						return obj;
						
					}
					return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantiateDepatment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
	
			st = conn.prepareStatement("""
			    SELECT seller.*, department.Name as DepName
			    FROM seller INNER JOIN department
			    ON seller.DepartmentId = department.Id
			    ORDER BY Name
			""");

					
					rs = st.executeQuery();
					
					List<Seller> list = new ArrayList<>() ;
					Map<Integer, Department> map = new HashMap<>(); // guarda dentro do map qualquer departamento instanciado
					
					while (rs.next()) {
						
						Department dep = map.get(rs.getInt("DepartmentId")) ; // testa se o departamneto ja existe, ele vai dentro do map e ve se o departmentId e retorna null se nao existe e ai eu instancio
						
						
						if (dep == null) {
							dep = instantiateDepatment(rs);
							map.put(rs.getInt("DepartmentId"), dep); // instancia um novo departamento se for nulo; caso nao seja ele nao instancia
							// faz com que nao crie dois objs Department iguais e sim q direcionem ao mesmo
						}
						
						Seller obj = instantiateSeller(rs, dep);
						list.add(obj);
						
					}
					return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name ");
					st.setInt(1, department.getId());
					
					rs = st.executeQuery();
					
					List<Seller> list = new ArrayList<>() ;
					Map<Integer, Department> map = new HashMap<>(); // guarda dentro do map qualquer departamento instanciado
					
					while (rs.next()) {
						
						Department dep = map.get(rs.getInt("DepartmentId")) ; // testa se o departamneto ja existe, ele vai dentro do map e ve se o departmentId e retorna null se nao existe e ai eu instancio
						
						
						if (dep == null) {
							dep = instantiateDepatment(rs);
							map.put(rs.getInt("DepartmentId"), dep); // instancia um novo departamento se for nulo; caso nao seja ele nao instancia
							// faz com que nao crie dois objs Department iguais e sim q direcionem ao mesmo
						}
						
						Seller obj = instantiateSeller(rs, dep);
						list.add(obj);
						
					}
					return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	}

	

