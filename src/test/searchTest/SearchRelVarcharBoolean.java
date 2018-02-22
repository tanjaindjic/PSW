package test.searchTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import org.junit.Test;

import db.search.RelationalSearch;
import db.search.SearchType;
import db.search.view.ApplyFilterListener;
import db.search.view.FilterDialog;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Domain;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Table;
import workingsection.Tabs;

public class SearchRelVarcharBoolean {
	@Test
	public void test(){
		String table = "TEST";
		RelationalSearch rs = new RelationalSearch(table);
		ArrayList<String> atributteName = new ArrayList<String>();
		ArrayList<Object> attributeValue = new ArrayList<Object>();
		ArrayList<SearchType> type = new ArrayList<>();
		
		atributteName.add("TEST_STRING");
		atributteName.add("TEST_BOOLEAN");
		attributeValue.add("T0");
		attributeValue.add(false);
		type.add(SearchType.STRING);
		type.add(SearchType.BOOLEAN);
		
		ResultSet res= (ResultSet) rs.MultiObjectSearch(atributteName, attributeValue, type);
		int i=0;
		try {
			while(res.next()){
				i++;
				System.out.println("found value with id" + res.getInt("TEST_ID"));
			}
		} catch (SQLException e) {
			System.out.println("failed test SearchRelVarcharBoolean");
		}
		
		assertEquals(1, i);
	}
}
