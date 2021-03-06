package test.crud;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;

import db.crud.RelationalUpdateTuple;
import db.model.Torka;
import db.model.Value;
import editorSeme.model.enums.Tip;

public class UpdateFailTest {

	@Test
	public void test() {
		String tab = "TEST";
		
		Torka t = new Torka();
		
		Value v = new Value();
		v.setCode("TEST_ID");
		v.setTip(Tip.BIGINT);
		BigDecimal big = new BigDecimal(-999);
		v.setValue(big);
		t.add(v);
		
		Value v1 = new Value();
		v1.setCode("TEST_BROJ");
		v1.setTip(Tip.NUMERIC);
		BigDecimal big1 = new BigDecimal(5);
		v1.setValue(big1);
		t.add(v1);
		
		
		Value v2 = new Value();
		v2.setCode("TEST_BOOLEAN");
		v2.setTip(Tip.BOOLEAN);
		boolean b = true;
		v2.setValue(b);
		t.add(v2);
		
		
		Value v3 = new Value();
		v3.setCode("TEST_STRING");
		v3.setTip(Tip.VARCHAR);
		String s = "Test2";
		v3.setValue(s);
		t.add(v3);
		
		ArrayList<String> pkey = new ArrayList<String>();
		pkey.add("TEST_ID");
		
		RelationalUpdateTuple rut = new RelationalUpdateTuple(t, tab, pkey);
		
		boolean result = rut.doCommand();
		
		assertEquals(false, result);
	}

}
