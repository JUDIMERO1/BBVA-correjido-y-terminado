package co.empresa.bancoBBVA.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.empresa.bancoBBVA.modelo.Bill;
import co.empresa.bancoBBVA.util.Conexion;

public class BillDaoPostgres implements BillDao{

	Conexion conexion;
	private static final String INSERT_BILL_SQL = "INSERT INTO bill (date_bill, user_id, value, type, observation) VALUES (?, ?, ?, ?, ?);";
	private static final String DELETE_BILL_SQL = "DELETE FROM bill WHERE id = ?;";
	private static final String SELECT_BILL_SQL_BY_ID = "SELECT * FROM bill WHERE id = ?;";
	private static final String SELECT_ALL_BILL_SQL = "SELECT * FROM bill;";

	public BillDaoPostgres() {
		this.conexion = Conexion.getConexion();
	}
	
	@Override
	public void insert(Bill bill) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_BILL_SQL);
			preparedStatement.setDate(1, bill.getDate_bill());
			preparedStatement.setInt(2, bill.getUser_Id());
			preparedStatement.setInt(3, bill.getValue());
			preparedStatement.setInt(4, bill.getType());
			preparedStatement.setString(5, bill.getObservation());		
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}

	@Override
	public void delete(int id) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(DELETE_BILL_SQL);
			preparedStatement.setInt(1, id);
			conexion.execute();
		} catch (SQLException e) {
			
		}
	}

	@Override
	public List<Bill> selectAll() {
		List<Bill> bills = new ArrayList<>();

		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_ALL_BILL_SQL);
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				Date date_bill= rs.getDate("date_bill");
				int value = rs.getInt("value");
				String observation = rs.getString("observation");
				int user_id = rs.getInt("user_id");
				int type = rs.getInt("type");
				bills.add(new Bill(id, date_bill, user_id, value, type, observation));
			}
			
		}catch(SQLException e) {
			System.out.print(e);
		}
		return bills;
	}

	@Override
	public Bill select(int id) {
		Bill bill = null;

		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(SELECT_BILL_SQL_BY_ID);
			preparedStatement.setInt(1, id);
			ResultSet rs = conexion.query();
			
			while(rs.next()) {
				Date date_bill = rs.getDate("date_bill");
				String observation = rs.getString("observation");
				int type = rs.getInt("type");
				int value = rs.getInt("value");
				bill = (new Bill(id, date_bill, observation, type, value));
			}
					
		}catch(SQLException e) {
			
		}
		
		return bill;
	}

}
