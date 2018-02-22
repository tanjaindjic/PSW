package test.jsonCreationTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AtributValidationTest.class, DomainValidationTest.class, GeneralTest.class,
		NameTranslateValidatonTest.class, PackageInsertPackageTest.class, PackageInsertTableTest.class,
		PackageValidationTest.class, SistemInsertsTest.class, SistemValidationTest.class,
		TableInsertAttributeTest.class, TableInsertFKeyTest.class, TableInsertKeyTest.class,
		TableValidationTest.class })
public class AllTests {

}
