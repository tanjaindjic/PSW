package test.crud;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import db.crud.RelationalRead;

public class ReadFailTest {

	@Test
	public void test() {
		
		RelationalRead read = new RelationalRead();
		Object o =read.readTable("TEST!!!");
		ResultSet rs = (ResultSet)o;
		boolean b = true;
		if(rs == null)
			b= false;
		assertEquals(false, b);
	}

}
