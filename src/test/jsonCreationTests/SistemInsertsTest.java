package test.jsonCreationTests;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.TimeoutException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import editorSeme.controller.command.AddPackage;
import editorSeme.model.enums.PackageType;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Package;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Translation;
import editorSeme.view.WrongParentException;
import test.TestInit;

public class SistemInsertsTest {

	@Test
	public void test() {
		TestInit testinit = new TestInit();
		testinit.initializeTestModel();
		Package p1 = new Package(Sistem.getInstance(), new ArrayList<Observer>());
		
		p1.setPackageType(PackageType.SUBSYSTEM);
		Translation t1 = new Translation("Tim 5.1", "enUS");
		ArrayList<Translation> trans1 = new ArrayList<Translation>();
		trans1.add(t1);
		NameTranslate nt1 = new NameTranslate();
		nt1.setCode("5.1");
		nt1.setTranslate(trans1);
		p1.setNaziv(nt1);

		AddPackage com;
		try {
			com = new AddPackage(Sistem.getInstance(), p1);
			assertFalse(com.doCommand());
		} catch (WrongParentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
