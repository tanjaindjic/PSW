package test.crud;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import db.crud.RelationalRead;

public class ReadTest {

	@Test
	public void test() {
		RelationalRead read = new RelationalRead();
		Object o =read.readTable("TEST");
		ResultSet rs = (ResultSet)o;
		boolean b = false;
		try {
			while(rs.next()){
				b= true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(true, b);
		
	}

}
