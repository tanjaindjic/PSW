package test.jsonCreationTests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import editorSeme.controller.command.AddTable;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Domain;
import editorSeme.model.pojo.Id_Id;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;
import test.TestInit;

public class PackageInsertTableTest {

	@Test
	public void test() {
		TestInit testini = new TestInit();
		Package p1 = testini.initializeTestpackage();
		
		// Tabela Fakultet
		Table tab1 = new Table(p1, new ArrayList<Observer>());
		Translation t3 = new Translation("Faculty", "enUS");
		ArrayList<Translation> trans3 = new ArrayList<Translation>();
		trans3.add(t3);
		NameTranslate nt3 = new NameTranslate();
		nt3.setCode("faks");
		nt3.setTranslate(trans3);
		tab1.setNaziv(nt3);
		// Atribut sf
		Atribut a1 = new Atribut();
		Domain d1 = new Domain();
		d1.setTip(Tip.VARCHAR);
		d1.setLength(10);
		a1.setDomain(d1);
		Translation t4 = new Translation("Faculty code", "enUS");
		ArrayList<Translation> trans4 = new ArrayList<Translation>();
		trans4.add(t4);
		NameTranslate nt4 = new NameTranslate();
		nt4.setCode("SF");
		nt4.setTranslate(trans4);
		a1.setNull(false);
		a1.setUnique(true);
		a1.setName(nt4);
		tab1.addAtribute(a1);

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
		tab1.addAtribute(a2);

		// Atribut adrf
		Atribut a3 = new Atribut();
		Domain d3 = new Domain();
		d3.setTip(Tip.VARCHAR);
		d3.setLength(20);
		a3.setDomain(d3);
		Translation t6 = new Translation("Faculty adress", "enUS");
		ArrayList<Translation> trans6 = new ArrayList<Translation>();
		trans6.add(t6);
		NameTranslate nt6 = new NameTranslate();
		nt6.setCode("ADRF");
		nt6.setTranslate(trans6);
		a3.setNull(true);
		a3.setUnique(true);
		a3.setName(nt6);
		tab1.addAtribute(a3);

		Key k1 = new Key();
		ArrayList<Id_Id> ids1 = new ArrayList<Id_Id>();
		Id_Id id1 = new Id_Id();
		id1.setAtributeKey("SF");
		id1.setTableKey("faks");
		ids1.add(id1);
		k1.setIds(ids1);
		tab1.addKey(k1);
		tab1.setpKey(0);
		
		AddTable com = new AddTable(p1, tab1);
		assertTrue(com.doCommand());
		
	}

}
