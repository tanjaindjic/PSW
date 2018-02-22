package test.searchTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

import db.crud.RelationalDeleteTuple;
import db.model.Torka;
import db.model.Value;
import editorSeme.model.enums.Tip;

public class DeleteRelTest {
	@Test
	public void test() {
		
String table = "TEST";
		
		Torka t0 = new Torka();
		
		Value v00 = new Value();
		v00.setCode("TEST_ID");
		v00.setTip(Tip.BIGINT);
		BigDecimal big00 = new BigDecimal(11);
		v00.setValue(big00);
		t0.add(v00);
		
		Value v01 = new Value();
		v01.setCode("TEST_BROJ");
		v01.setTip(Tip.NUMERIC);
		BigDecimal big01 = new BigDecimal(5);
		v01.setValue(big01);
		t0.add(v01);		
		
		Value v02 = new Value();
		v02.setCode("TEST_BOOLEAN");
		v02.setTip(Tip.BOOLEAN);
		boolean b0 = false;
		v02.setValue(b0);
		t0.add(v02);
		
		Value v03 = new Value();
		v03.setCode("TEST_STRING");
		v03.setTip(Tip.VARCHAR);
		String s0 = "T0";
		v03.setValue(s0);
		t0.add(v03);
		
		
		Torka t1 = new Torka();
		
		Value v10 = new Value();
		v10.setCode("TEST_ID");
		v10.setTip(Tip.BIGINT);
		BigDecimal big11 = new BigDecimal(21);
		v10.setValue(big11);
		t1.add(v10);
		
		Value v11 = new Value();
		v11.setCode("TEST_BROJ");
		v11.setTip(Tip.NUMERIC);
		BigDecimal big12 = new BigDecimal(6);
		v11.setValue(big12);
		t1.add(v11);		
		
		Value v12 = new Value();
		v12.setCode("TEST_BOOLEAN");
		v12.setTip(Tip.BOOLEAN);
		boolean b1 = true;
		v12.setValue(b1);
		t1.add(v12);
		
		Value v13 = new Value();
		v13.setCode("TEST_STRING");
		v13.setTip(Tip.VARCHAR);
		String s1 = "T1";
		v13.setValue(s1);
		t1.add(v13);
		
		ArrayList<String> pkey = new ArrayList<String>();
		pkey.add("TEST_ID");
		
		RelationalDeleteTuple rdt0 = new RelationalDeleteTuple(t0, table, pkey);
		
		boolean result0 = rdt0.doCommand();
		
		assertEquals(true, result0);
		
		RelationalDeleteTuple rdt1 = new RelationalDeleteTuple(t1, table, pkey);
		
		boolean result1 = rdt1.doCommand();
		
		assertEquals(true, result1);
	}

}
