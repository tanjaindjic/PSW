package test.crud;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import db.crud.RelationalCreateTuple;
import db.model.Torka;
import db.model.Value;
import editorSeme.model.enums.Tip;

public class CreateFailTest {

	@Test
	public void test() {
String tab = "TEST";
		
		Torka t = new Torka();
		
		Value v = new Value();
		v.setCode("TEST_ID1");
		v.setTip(Tip.BIGINT);
		BigDecimal big = new BigDecimal(1);
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
		String s = "Test1";
		v3.setValue(s);
		t.add(v3);
		
		RelationalCreateTuple rct = new RelationalCreateTuple(t, tab);
		boolean result = rct.doCommand();
		
		assertEquals(false, result);
	}

}
