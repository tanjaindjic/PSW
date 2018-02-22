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

import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Domain;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Translation;

public class AtributValidationTest {
	@Test
	public void test() {
		Atribut a1 = new Atribut();
		Domain d1 = new Domain();
		d1.setTip(Tip.VARCHAR);
		d1.setLength(10);
		a1.setDomain(d1);
		Translation t4 = new Translation("Test atribute", "enUS");
		ArrayList<Translation> trans4 = new ArrayList<Translation>();
		trans4.add(t4);
		NameTranslate nt4 = new NameTranslate();
		nt4.setCode("SF");
		nt4.setTranslate(trans4);
		a1.setNull(false);
		a1.setUnique(true);
		a1.setName(nt4);

		assertTrue(a1.validate());
	}

}
