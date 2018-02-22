package test.searchTest;

import static org.junit.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import db.search.RelationalSearch;
import db.search.SearchType;

public class SearchRelInt {
	@Test
	public void test(){
		String table = "TEST";
		RelationalSearch rs = new RelationalSearch(table);
		ArrayList<String> atributteName = new ArrayList<String>();
		ArrayList<Object> attributeValue = new ArrayList<Object>();
		ArrayList<SearchType> type = new ArrayList<>();
		
		atributteName.add("TEST_ID");
		atributteName.add("TEST_ID");
		attributeValue.add(15f);
		attributeValue.add(25f);
		type.add(SearchType.MIN);
		type.add(SearchType.MAX);
		
		ResultSet res= (ResultSet) rs.MultiObjectSearch(atributteName, attributeValue, type);
		int i=0;
		try {
			while(res.next()){
				i++;
				System.out.println("found value with id " + res.getInt("TEST_ID"));
			}
		} catch (SQLException e) {
			System.out.println("failed test SearchRelInt");
		}
		
		assertEquals(1, i);
	}
}
