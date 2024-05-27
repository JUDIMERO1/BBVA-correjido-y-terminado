package co.empresa.bancoBBVA.dao;

public class BillDaoFactory {
	
	public static BillDao getBillDao(String type) {
		switch(type) {
		case "postgresql":
			return new BillDaoPostgres();
		default:
			return new BillDaoPostgres();
		}
	}
}
