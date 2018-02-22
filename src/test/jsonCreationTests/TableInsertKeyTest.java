package test.jsonCreationTests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import editorSeme.model.pojo.Id_Id;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.Table;
import test.TestInit;

public class TableInsertKeyTest {

	@Test
	public void test() {
		TestInit testini = new TestInit();
		Table t = testini.initTableTest();
		
		Key k1 = new Key();
		ArrayList<Id_Id> ids1 = new ArrayList<Id_Id>();
		Id_Id id1 = new Id_Id();
		id1.setAtributeKey("SF1");
		id1.setTableKey("faks1");
		ids1.add(id1);
		k1.setIds(ids1);
		t.addKey(k1);
		t.setpKey(0);
		
		assertTrue(t.addKey(k1));
	}

}
