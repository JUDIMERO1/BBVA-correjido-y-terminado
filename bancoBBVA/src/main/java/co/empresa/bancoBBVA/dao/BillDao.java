package co.empresa.bancoBBVA.dao;

import java.sql.SQLException;
import java.util.List;

import co.empresa.bancoBBVA.modelo.Bill;

public interface BillDao {

	public void insert(Bill bill) throws SQLException;
	public void delete(int id) throws SQLException;
	public List<Bill> selectAll();
	public Bill select(int id);	
}
