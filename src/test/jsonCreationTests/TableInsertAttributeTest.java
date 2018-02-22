package test.jsonCreationTests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import editorSeme.controller.command.AddAtribute;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Domain;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;
import test.TestInit;

public class TableInsertAttributeTest {

	@Test
	public void test() {
		TestInit testini = new TestInit();
		Table t = testini.initTableTest();
		
		// Atribut nazf
		Atribut a2 = new Atribut();
		Domain d2 = new Domain();
		d2.setTip(Tip.VARCHAR);
		d2.setLength(20);
		a2.setDomain(d2);
		Translation t5 = new Translation("Faculty name", "enUS");
		ArrayList<Translation> trans5 = new ArrayList<Translation>();
		trans5.add(t5);
		NameTranslate nt5 = new NameTranslate();
		nt5.setCode("NAZF");
		nt5.setTranslate(trans5);
		a2.setNull(false);
		a2.setUnique(true);
		a2.setName(nt5);
		
		AddAtribute com = new AddAtribute(t, a2);
		assertTrue(com.doCommand());
		
		
	}

}
