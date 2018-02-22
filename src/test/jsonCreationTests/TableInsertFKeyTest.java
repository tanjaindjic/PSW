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

import editorSeme.model.pojo.FKey;
import editorSeme.model.pojo.Table;
import test.TestInit;

public class TableInsertFKeyTest {

	@Test
	public void test() {
		TestInit testini = new TestInit();
		Table t = testini.initTableTest();
		
		ArrayList<FKey> fkeys21 = new ArrayList<FKey>();
		FKey fk21 = new FKey();
		fk21.setConnectedTable("faks");
		ArrayList<String> foreignIds21 = new ArrayList<String>();
		foreignIds21.add("SF");
		ArrayList<String> homeIds21 = new ArrayList<String>();
		homeIds21.add("FAKULTET_SF");
		fk21.setForeignIds(foreignIds21);
		fk21.setHomeIds(homeIds21);
		fkeys21.add(fk21);
		
		assertTrue(t.addFKey(fk21));
	}

}
