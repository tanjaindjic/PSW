package test;

import java.util.ArrayList;
import java.util.Observer;

import editorSeme.model.enums.PackageType;
import editorSeme.model.enums.Tip;
import editorSeme.model.pojo.Atribut;
import editorSeme.model.pojo.Domain;
import editorSeme.model.pojo.FKey;
import editorSeme.model.pojo.Id_Id;
import editorSeme.model.pojo.Key;
import editorSeme.model.pojo.NameTranslate;
import editorSeme.model.pojo.Sistem;
import editorSeme.model.pojo.Table;
import editorSeme.model.pojo.Translation;
import editorSeme.model.pojo.Package;

/**
 * A class that creates an instance of a JSON database schema. Used for testing purposes only.
 *
 */
public class TestInit {

	/**
	 * Constructor for the class.
	 */
	public TestInit(){
		
	}
	
	public void initializeTestModel() {
		Sistem s = Sistem.getInstance();
		Translation t0 = new Translation("PSW 2017", "enUS");
		ArrayList<Translation> trans0 = new ArrayList<Translation>();
		trans0.add(t0);
		NameTranslate nt0 = new NameTranslate();
		nt0.setCode("psw");
		nt0.setTranslate(trans0);
		s.setNaziv(nt0);
		// s.setPath("C:\\Users\\milan\\Desktop\\s1.json");

		// Subsistem
		Package p1 = new Package(s, new ArrayList<Observer>());
	
		p1.setPackageType(PackageType.SUBSYSTEM);
		Translation t1 = new Translation("Tim 5.1", "enUS");
		ArrayList<Translation> trans1 = new ArrayList<Translation>();
		trans1.add(t1);
		NameTranslate nt1 = new NameTranslate();
		nt1.setCode("5.1");
		nt1.setTranslate(trans1);
		p1.setNaziv(nt1);
		s.addPackage(p1);

		// Paket
		Package p2 = new Package(p1, new ArrayList<Observer>());
		p1.setPackageType(PackageType.PACKAGE);
		Translation t2 = new Translation("Faculty DB", "enUS");
		ArrayList<Translation> trans2 = new ArrayList<Translation>();
		trans2.add(t2);
		NameTranslate nt2 = new NameTranslate();
		nt2.setCode("json db");
		nt2.setTranslate(trans2);
		p2.setNaziv(nt2);
		p1.getPackages().add(p2);

		// Tabela Fakultet
		Table tab1 = new Table(p2, new ArrayList<Observer>());
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
		p2.addTable(tab1);

		// Tabela Depatman
		Table tab2 = new Table(p2, new ArrayList<Observer>());
		Translation t7 = new Translation("Department", "enUS");
		ArrayList<Translation> trans7 = new ArrayList<Translation>();
		trans7.add(t7);
		NameTranslate nt7 = new NameTranslate();
		nt7.setCode("depart");
		nt7.setTranslate(trans7);
		tab2.setNaziv(nt7);

		// Atribut sd
		Atribut a21 = new Atribut();
		Domain d21 = new Domain();
		d21.setTip(Tip.VARCHAR);
		d21.setLength(10);
		a21.setDomain(d21);
		Translation t8 = new Translation("Department code", "enUS");
		ArrayList<Translation> trans8 = new ArrayList<Translation>();
		trans8.add(t8);
		NameTranslate nt8 = new NameTranslate();
		nt8.setCode("SD");
		nt8.setTranslate(trans8);
		a21.setNull(false);
		a21.setUnique(true);
		a21.setName(nt8);
		tab2.addAtribute(a21);

		// Atribut nazd
		Atribut a22 = new Atribut();
		Domain d22 = new Domain();
		d22.setTip(Tip.VARCHAR);
		d22.setLength(20);
		a22.setDomain(d22);
		Translation t9 = new Translation("Department name", "enUS");
		ArrayList<Translation> trans9 = new ArrayList<Translation>();
		trans9.add(t9);
		NameTranslate nt9 = new NameTranslate();
		nt9.setCode("NAZD");
		nt9.setTranslate(trans9);
		a22.setNull(false);
		a22.setUnique(true);
		a22.setName(nt9);
		tab2.addAtribute(a22);

		// Atribut Fakultet_SF
		Atribut a23 = new Atribut();
		Domain d23 = new Domain();
		d23.setTip(Tip.VARCHAR);
		d23.setLength(20);
		a23.setDomain(d3);
		Translation t10 = new Translation("Faculty code", "enUS");
		ArrayList<Translation> trans10 = new ArrayList<Translation>();
		trans10.add(t10);
		NameTranslate nt10 = new NameTranslate();
		nt10.setCode("FAKULTET_SF");
		nt10.setTranslate(trans10);
		a23.setNull(false);
		a23.setUnique(true);
		a23.setName(nt10);
		tab2.addAtribute(a23);

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
		tab2.setfKeys(fkeys21);

		Key k21 = new Key();
		ArrayList<Id_Id> ids21 = new ArrayList<Id_Id>();
		Id_Id id21 = new Id_Id();
		id21.setAtributeKey("SD");
		id21.setTableKey("depart");

		Id_Id id22 = new Id_Id();
		id21.setAtributeKey("FAKULTET_SF");
		id21.setTableKey("depart");

		ids21.add(id21);
		ids21.add(id22);

		k21.setIds(ids21);
		tab2.addKey(k21);
		tab2.setpKey(0);
		p2.addTable(tab2);

		// Tabela Katedra
		Table tab3 = new Table(p2, new ArrayList<Observer>());
		Translation t31 = new Translation("Chair", "enUS");
		ArrayList<Translation> trans31 = new ArrayList<Translation>();
		trans31.add(t31);
		NameTranslate nt31 = new NameTranslate();
		nt31.setCode("kat");
		nt31.setTranslate(trans31);
		tab3.setNaziv(nt31);

		// Atribut sk
		Atribut a31 = new Atribut();
		Domain d31 = new Domain();
		d31.setTip(Tip.VARCHAR);
		d31.setLength(10);
		a31.setDomain(d31);
		Translation t32 = new Translation("Chair code", "enUS");
		ArrayList<Translation> trans32 = new ArrayList<Translation>();
		trans32.add(t32);
		NameTranslate nt32 = new NameTranslate();
		nt32.setCode("SK");
		nt32.setTranslate(trans32);
		a31.setNull(false);
		a31.setUnique(true);
		a31.setName(nt32);
		tab3.addAtribute(a31);

		// Atribut nazk
		Atribut a32 = new Atribut();
		Domain d32 = new Domain();
		d32.setTip(Tip.VARCHAR);
		d32.setLength(20);
		a32.setDomain(d32);
		Translation t33 = new Translation("Chair name", "enUS");
		ArrayList<Translation> trans33 = new ArrayList<Translation>();
		trans33.add(t33);
		NameTranslate nt33 = new NameTranslate();
		nt33.setCode("NAZK");
		nt33.setTranslate(trans33);
		a32.setNull(false);
		a32.setUnique(true);
		a32.setName(nt33);
		tab3.addAtribute(a32);

		// Atribut Departman_Fakultet_SF
		Atribut a33 = new Atribut();
		Domain d33 = new Domain();
		d33.setTip(Tip.VARCHAR);
		d33.setLength(20);
		a33.setDomain(d3);
		Translation t34 = new Translation("Faculty code", "enUS");
		ArrayList<Translation> trans34 = new ArrayList<Translation>();
		trans34.add(t34);
		NameTranslate nt34 = new NameTranslate();
		nt34.setCode("DEPARTMAN_FAKULTET_SF");
		nt34.setTranslate(trans34);
		a33.setNull(false);
		a33.setUnique(true);
		a33.setName(nt34);
		tab3.addAtribute(a33);

		// Atribut Departman_SD
		Atribut a34 = new Atribut();
		Domain d34 = new Domain();
		d34.setTip(Tip.VARCHAR);
		d34.setLength(20);
		a34.setDomain(d3);
		Translation t35 = new Translation("Chair code", "enUS");
		ArrayList<Translation> trans35 = new ArrayList<Translation>();
		trans35.add(t35);
		NameTranslate nt35 = new NameTranslate();
		nt35.setCode("DEPARTMAN_SD");
		nt35.setTranslate(trans35);
		a34.setNull(false);
		a34.setUnique(true);
		a34.setName(nt35);
		tab3.addAtribute(a34);
		
		
		ArrayList<FKey> fkeys31 = new ArrayList<FKey>();
		FKey fk31 = new FKey();
		fk31.setConnectedTable("depart");
		ArrayList<String> foreignIds31 = new ArrayList<String>();
		foreignIds31.add("SD");
		foreignIds31.add("FAKULTET_SF");
		ArrayList<String> homeIds31 = new ArrayList<String>();
		homeIds31.add("DEPARTMAN_SD");
		homeIds31.add("DEPARTMAN_FAKULTET_SF");
		fk31.setForeignIds(foreignIds31);
		fk31.setHomeIds(homeIds31);
		fkeys31.add(fk31);
		
		tab3.setfKeys(fkeys31);

		Key k31 = new Key();
		ArrayList<Id_Id> ids31 = new ArrayList<Id_Id>();
		Id_Id id31 = new Id_Id();
		id31.setAtributeKey("SK");
		id31.setTableKey("kat");

		Id_Id id32 = new Id_Id();
		id32.setAtributeKey("DEPARTMAN_FAKULTET_SF");
		id32.setTableKey("kat");

		Id_Id id33 = new Id_Id();
		id33.setAtributeKey("DEPARTMAN_SD");
		id33.setTableKey("kat");
		
		ids31.add(id31);
		ids31.add(id32);
		ids31.add(id33);

		k31.setIds(ids31);
		tab3.addKey(k31);
		tab3.setpKey(0);
		p2.addTable(tab3);
		
		
		// Tabela postoji
		Table tab4 = new Table(p2, new ArrayList<Observer>());
		Translation t40 = new Translation("Exists", "enUS");
		ArrayList<Translation> trans40 = new ArrayList<Translation>();
		trans40.add(t40);
		NameTranslate nt40 = new NameTranslate();
		nt40.setCode("post");
		nt40.setTranslate(trans40);
		tab4.setNaziv(nt40);

		// Atribut postoji id
		Atribut a41 = new Atribut();
		Domain d41 = new Domain();
		d41.setTip(Tip.INT);
		a41.setDomain(d41);
		Translation t41 = new Translation("Exists id", "enUS");
		ArrayList<Translation> trans41 = new ArrayList<Translation>();
		trans41.add(t41);
		NameTranslate nt41 = new NameTranslate();
		nt41.setCode("POSTOJI_ID");
		nt41.setTranslate(trans41);
		a41.setNull(false);
		a41.setUnique(true);
		a41.setName(nt41);
		tab4.addAtribute(a41);

		// Atribut katedra_sk
		Atribut a42 = new Atribut();
		Domain d42 = new Domain();
		d42.setTip(Tip.VARCHAR);
		d42.setLength(10);
		a42.setDomain(d42);
		Translation t42 = new Translation("Chair id", "enUS");
		ArrayList<Translation> trans42 = new ArrayList<Translation>();
		trans42.add(t42);
		NameTranslate nt42 = new NameTranslate();
		nt42.setCode("KATEDRA_SK");
		nt42.setTranslate(trans42);
		a42.setNull(false);
		a42.setUnique(true);
		a42.setName(nt42);
		tab4.addAtribute(a42);

		// Atribut katedra_sd
		Atribut a43 = new Atribut();
		Domain d43 = new Domain();
		d43.setTip(Tip.VARCHAR);
		d43.setLength(10);
		a43.setDomain(d43);
		Translation t43 = new Translation("Department code", "enUS");
		ArrayList<Translation> trans43 = new ArrayList<Translation>();
		trans43.add(t43);
		NameTranslate nt43 = new NameTranslate();
		nt43.setCode("KATEDRA_SD");
		nt43.setTranslate(trans43);
		a43.setNull(false);
		a43.setUnique(true);
		a43.setName(nt43);
		tab4.addAtribute(a43);

		// Atribut Katedra_sf
		Atribut a44 = new Atribut();
		Domain d44 = new Domain();
		d44.setTip(Tip.VARCHAR);
		d44.setLength(20);
		a44.setDomain(d3);
		Translation t44 = new Translation("Faculty code", "enUS");
		ArrayList<Translation> trans44 = new ArrayList<Translation>();
		trans44.add(t44);
		NameTranslate nt44 = new NameTranslate();
		nt44.setCode("KATEDRA_SF");
		nt44.setTranslate(trans44);
		a44.setNull(false);
		a44.setUnique(true);
		a44.setName(nt44);
		tab3.addAtribute(a44);
		
		
		ArrayList<FKey> fkeys41 = new ArrayList<FKey>();
		FKey fk41 = new FKey();
		fk41.setConnectedTable("kat");
		ArrayList<String> foreignIds41 = new ArrayList<String>();
		foreignIds41.add("SK");
		foreignIds41.add("DEPARTMAN_SD");
		foreignIds41.add("DEPARTMAN_FAKULTET_SF");
		ArrayList<String> homeIds41 = new ArrayList<String>();
		homeIds41.add("KATEDRA_SK");
		homeIds41.add("KATEDRA_SD");
		homeIds41.add("KATEDRA_SF");
		fk41.setForeignIds(foreignIds41);
		fk41.setHomeIds(homeIds41);
		
		fkeys41.add(fk41);
		
		tab4.setfKeys(fkeys41);

		Key k41 = new Key();
		ArrayList<Id_Id> ids41 = new ArrayList<Id_Id>();
		Id_Id id41 = new Id_Id();
		id41.setAtributeKey("POSTOJI_ID");
		id41.setTableKey("post");

		ids41.add(id41);
	
		k41.setIds(ids41);
		tab4.addKey(k41);
		tab4.setpKey(0);
		p2.addTable(tab4);
		
		
		// Tabela predaje
		Table tab5 = new Table(p2, new ArrayList<Observer>());
		Translation t50 = new Translation("Teaches", "enUS");
		ArrayList<Translation> trans50 = new ArrayList<Translation>();
		trans50.add(t50);
		NameTranslate nt50 = new NameTranslate();
		nt50.setCode("pred");
		nt50.setTranslate(trans50);
		tab5.setNaziv(nt50);

		// Atribut je_zaposlen_je_zaposlen_id
		Atribut a51 = new Atribut();
		Domain d51 = new Domain();
		d51.setTip(Tip.INT);
		a51.setDomain(d51);
		Translation t51 = new Translation("Works id", "enUS");
		ArrayList<Translation> trans51 = new ArrayList<Translation>();
		trans51.add(t51);
		NameTranslate nt51 = new NameTranslate();
		nt51.setCode("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		nt51.setTranslate(trans51);
		a51.setNull(false);
		a51.setUnique(true);
		a51.setName(nt51);
		tab5.addAtribute(a51);

		// Atribut postoji postoji id
		Atribut a52 = new Atribut();
		Domain d52 = new Domain();
		d52.setTip(Tip.INT);
		a52.setDomain(d52);
		Translation t52 = new Translation("Exists id", "enUS");
		ArrayList<Translation> trans52 = new ArrayList<Translation>();
		trans52.add(t52);
		NameTranslate nt52 = new NameTranslate();
		nt52.setCode("KATEDRA_SK");
		nt52.setTranslate(trans52);
		a52.setNull(false);
		a52.setUnique(true);
		a52.setName(nt52);
		tab5.addAtribute(a52);
		
		ArrayList<FKey> fkeys51 = new ArrayList<FKey>();
		FKey fk51 = new FKey();
		fk51.setConnectedTable("post");
		ArrayList<String> foreignIds51 = new ArrayList<String>();
		foreignIds51.add("POSTOJI_ID");
		ArrayList<String> homeIds51 = new ArrayList<String>();
		homeIds51.add("POSTOJI_POSTOJI_ID");
		fk51.setForeignIds(foreignIds51);
		fk51.setHomeIds(homeIds51);
		fkeys51.add(fk51);
		
		FKey fk52 = new FKey();
		fk52.setConnectedTable("JE_ZAPOSLEN"); // OVDE GRBA
		ArrayList<String> foreignIds52 = new ArrayList<String>();
		foreignIds52.add("JE_ZAPOSLEN_ID");
		ArrayList<String> homeIds52 = new ArrayList<String>();
		homeIds52.add("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		fk52.setForeignIds(foreignIds52);
		fk52.setHomeIds(homeIds52);
		fkeys51.add(fk52);
		
		tab5.setfKeys(fkeys51);
		
		
		Key k51 = new Key();
		ArrayList<Id_Id> ids51 = new ArrayList<Id_Id>();
		Id_Id id51 = new Id_Id();
		id51.setAtributeKey("POSTOJI_POSTOJI_ID");
		id51.setTableKey("pred");
		ids51.add(id51);
		
		Id_Id id52 = new Id_Id();
		id52.setAtributeKey("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		id52.setTableKey("pred");
		ids51.add(id52);
	
		k51.setIds(ids51);
		tab5.addKey(k51);
		tab5.setpKey(0);
		p2.addTable(tab5);
		
		
		//JE ZAPOSLEN
		Table tab6 = new Table(p2, new ArrayList<Observer>());
		Translation t60 = new Translation("Is hired", "enUS");
		ArrayList<Translation> trans60 = new ArrayList<Translation>();
		trans60.add(t60);
		NameTranslate nt60 = new NameTranslate();
		nt60.setCode("JE_ZAPOSLEN");
		nt60.setTranslate(trans60);
		tab6.setNaziv(nt60);

		// Atribut KAATEDRA SK
		Atribut a61 = new Atribut();
		Domain d61 = new Domain();
		d61.setTip(Tip.VARCHAR);
		d61.setLength(10);
		a61.setDomain(d61);
		Translation t61 = new Translation("Chair id", "enUS");
		ArrayList<Translation> trans61 = new ArrayList<Translation>();
		trans61.add(t61);
		NameTranslate nt61 = new NameTranslate();
		nt61.setCode("KATEDRA_SK");
		nt61.setTranslate(trans61);
		a61.setNull(false);
		a61.setUnique(true);
		a61.setName(nt61);
		tab6.addAtribute(a61);
		
		// Atribut katedra sd
		Atribut a62 = new Atribut();
		Domain d62 = new Domain();
		d62.setTip(Tip.VARCHAR);
		d62.setLength(10);
		a62.setDomain(d62);
		Translation t62 = new Translation("Departman id", "enUS");
		ArrayList<Translation> trans62 = new ArrayList<Translation>();
		trans62.add(t62);
		NameTranslate nt62 = new NameTranslate();
		nt62.setCode("KATEDRA_SD");
		nt62.setTranslate(trans62);
		a62.setNull(false);
		a62.setUnique(true);
		a62.setName(nt62);
		tab6.addAtribute(a62);
		
		// Atribut katedra sf
		Atribut a63 = new Atribut();
		Domain d63 = new Domain();
		d63.setTip(Tip.VARCHAR);
		d63.setLength(10);
		a63.setDomain(d63);
		Translation t63 = new Translation("Fac id", "enUS");
		ArrayList<Translation> trans63 = new ArrayList<Translation>();
		trans63.add(t63);
		NameTranslate nt63 = new NameTranslate();
		nt63.setCode("KATEDRA_SF");
		nt63.setTranslate(trans63);
		a63.setNull(false);
		a63.setUnique(true);
		a63.setName(nt63);
		tab6.addAtribute(a63);
					
		// Atribut profesor mbr
		Atribut a64 = new Atribut();
		Domain d64 = new Domain();
		d64.setTip(Tip.INT);
		a64.setDomain(d64);
		Translation t64 = new Translation("Profesor number", "enUS");
		ArrayList<Translation> trans64 = new ArrayList<Translation>();
		trans64.add(t64);
		NameTranslate nt64 = new NameTranslate();
		nt64.setCode("PROFESOR_MBRP");
		nt64.setTranslate(trans64);
		a64.setNull(false);
		a64.setUnique(true);
		a64.setName(nt64);
		tab6.addAtribute(a64);
		
		// Atribut je zaposlen id
		Atribut a66 = new Atribut();
		Domain d66 = new Domain();
		d66.setTip(Tip.INT);
		a66.setDomain(d66);
		Translation t66 = new Translation("Is hired", "enUS");
		ArrayList<Translation> trans66 = new ArrayList<Translation>();
		trans66.add(t66);
		NameTranslate nt66 = new NameTranslate();
		nt66.setCode("JE_ZAPOSLEN_ID");
		nt66.setTranslate(trans66);
		a66.setNull(false);
		a66.setUnique(true);
		a66.setName(nt66);
		tab6.addAtribute(a66);
		
		ArrayList<FKey> fkeys61 = new ArrayList<FKey>();
		FKey fk61 = new FKey();
		fk61.setConnectedTable("kat");
		ArrayList<String> foreignIds61 = new ArrayList<String>();
		foreignIds61.add("KATEDRA_SK");
		foreignIds61.add("KATEDRA_SD");
		foreignIds61.add("KATEDRA_SF");
		ArrayList<String> homeIds61 = new ArrayList<String>();
		homeIds61.add("SK");
		homeIds61.add("DEPARTMAN_SD");
		homeIds61.add("DEPARTMAN_FAKULTET_SF");
		fk61.setForeignIds(foreignIds61);
		fk61.setHomeIds(homeIds61);
		fkeys61.add(fk61);
		tab6.setfKeys(fkeys61);

		Key k61 = new Key();
		ArrayList<Id_Id> ids61 = new ArrayList<Id_Id>();
		Id_Id id61 = new Id_Id();
		id61.setAtributeKey("JE_ZAPOSLEN_ID");
		id61.setTableKey("JE_ZAPOSLEN");

		ids61.add(id61);
	
		k61.setIds(ids61);
		tab6.addKey(k61);
		tab6.setpKey(0);
		p2.addTable(tab6);
		
		//PROFESOR
		Table tab7 = new Table(p2, new ArrayList<Observer>());
		Translation t70 = new Translation("Prosfesor", "enUS");
		ArrayList<Translation> trans70 = new ArrayList<Translation>();
		trans70.add(t70);
		NameTranslate nt70 = new NameTranslate();
		nt70.setCode("Profesor");
		nt70.setTranslate(trans70);
		tab7.setNaziv(nt70);
		
		//atribut mbr
		Atribut a71 = new Atribut();
		Domain d71 = new Domain();
		d71.setTip(Tip.INT);
		a71.setDomain(d71);
		Translation t71 = new Translation("Seriall number", "enUS");
		ArrayList<Translation> trans71 = new ArrayList<Translation>();
		trans71.add(t71);
		NameTranslate nt71 = new NameTranslate();
		nt71.setCode("MBRP");
		nt71.setTranslate(trans71);
		a71.setNull(false);
		a71.setUnique(true);
		a71.setName(nt71);
		tab7.addAtribute(a71);
		
		//atribut imep
		Atribut a72 = new Atribut();
		Domain d72 = new Domain();
		d72.setTip(Tip.VARCHAR);
		a72.setDomain(d72);
		d72.setLength(15);
		Translation t72 = new Translation("Name	of profesor", "enUS");
		ArrayList<Translation> trans72 = new ArrayList<Translation>();
		trans72.add(t72);
		NameTranslate nt72 = new NameTranslate();
		nt72.setCode("IMEP");
		nt72.setTranslate(trans72);
		a72.setNull(false);
		a72.setUnique(true);
		a72.setName(nt72);
		tab7.addAtribute(a72);
		
		//atribut prezime
		Atribut a73 = new Atribut();
		Domain d73 = new Domain();
		d73.setTip(Tip.VARCHAR);
		d73.setLength(15);
		a73.setDomain(d73);
		Translation t73 = new Translation("Surname of profesor", "enUS");
		ArrayList<Translation> trans73 = new ArrayList<Translation>();
		trans72.add(t73);
		NameTranslate nt73 = new NameTranslate();
		nt73.setCode("PRZP");
		nt73.setTranslate(trans73);
		a73.setNull(false);
		a73.setUnique(true);
		a73.setName(nt73);
		tab7.addAtribute(a73);
		
		//atribut jejez
		Atribut a74 = new Atribut();
		Domain d74 = new Domain();
		d74.setTip(Tip.INT);
		a74.setDomain(d74);
		Translation t74 = new Translation("Is hired at", "enUS");
		ArrayList<Translation> trans74 = new ArrayList<Translation>();
		trans74.add(t74);
		NameTranslate nt74 = new NameTranslate();
		nt74.setCode("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		nt74.setTranslate(trans74);
		a74.setNull(false);
		a74.setUnique(true);
		a74.setName(nt74);
		tab7.addAtribute(a74);
		
		ArrayList<FKey> fkeys71 = new ArrayList<FKey>();
		FKey fk71 = new FKey();
		fk71.setConnectedTable("JE_ZAPOSLEN");
		ArrayList<String> foreignIds71 = new ArrayList<String>();
		foreignIds71.add("JE_ZAPOSLEN_ID");
		ArrayList<String> homeIds71 = new ArrayList<String>();
		homeIds71.add("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		fk71.setForeignIds(foreignIds71);
		fk71.setHomeIds(homeIds71);
		fkeys71.add(fk71);
		tab7.setfKeys(fkeys71);

		Key k71 = new Key();
		ArrayList<Id_Id> ids71 = new ArrayList<Id_Id>();
		Id_Id id71 = new Id_Id();
		id71.setAtributeKey("PROFESOR");
		id71.setTableKey("MBRP");

		ids71.add(id71);
	
		k71.setIds(ids71);
		tab7.addKey(k71);
		tab7.setpKey(0);
		p2.addTable(tab7);
		
	}
	
	public Package initializeTestpackage() {
		// Paket
		Package p2 = new Package(null, new ArrayList<Observer>());
		Translation t2 = new Translation("Faculty DB", "enUS");
		ArrayList<Translation> trans2 = new ArrayList<Translation>();
		trans2.add(t2);
		NameTranslate nt2 = new NameTranslate();
		nt2.setCode("json db");
		nt2.setTranslate(trans2);
		p2.setNaziv(nt2);

		// Tabela Fakultet
		Table tab1 = new Table(p2, new ArrayList<Observer>());
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
		p2.addTable(tab1);

		// Tabela Depatman
		Table tab2 = new Table(p2, new ArrayList<Observer>());
		Translation t7 = new Translation("Department", "enUS");
		ArrayList<Translation> trans7 = new ArrayList<Translation>();
		trans7.add(t7);
		NameTranslate nt7 = new NameTranslate();
		nt7.setCode("depart");
		nt7.setTranslate(trans7);
		tab2.setNaziv(nt7);

		// Atribut sd
		Atribut a21 = new Atribut();
		Domain d21 = new Domain();
		d21.setTip(Tip.VARCHAR);
		d21.setLength(10);
		a21.setDomain(d21);
		Translation t8 = new Translation("Department code", "enUS");
		ArrayList<Translation> trans8 = new ArrayList<Translation>();
		trans8.add(t8);
		NameTranslate nt8 = new NameTranslate();
		nt8.setCode("SD");
		nt8.setTranslate(trans8);
		a21.setNull(false);
		a21.setUnique(true);
		a21.setName(nt8);
		tab2.addAtribute(a21);

		// Atribut nazd
		Atribut a22 = new Atribut();
		Domain d22 = new Domain();
		d22.setTip(Tip.VARCHAR);
		d22.setLength(20);
		a22.setDomain(d22);
		Translation t9 = new Translation("Department name", "enUS");
		ArrayList<Translation> trans9 = new ArrayList<Translation>();
		trans9.add(t9);
		NameTranslate nt9 = new NameTranslate();
		nt9.setCode("NAZD");
		nt9.setTranslate(trans9);
		a22.setNull(false);
		a22.setUnique(true);
		a22.setName(nt9);
		tab2.addAtribute(a22);

		// Atribut Fakultet_SF
		Atribut a23 = new Atribut();
		Domain d23 = new Domain();
		d23.setTip(Tip.VARCHAR);
		d23.setLength(20);
		a23.setDomain(d3);
		Translation t10 = new Translation("Faculty code", "enUS");
		ArrayList<Translation> trans10 = new ArrayList<Translation>();
		trans10.add(t10);
		NameTranslate nt10 = new NameTranslate();
		nt10.setCode("FAKULTET_SF");
		nt10.setTranslate(trans10);
		a23.setNull(false);
		a23.setUnique(true);
		a23.setName(nt10);
		tab2.addAtribute(a23);

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
		tab2.setfKeys(fkeys21);

		Key k21 = new Key();
		ArrayList<Id_Id> ids21 = new ArrayList<Id_Id>();
		Id_Id id21 = new Id_Id();
		id21.setAtributeKey("SD");
		id21.setTableKey("depart");

		Id_Id id22 = new Id_Id();
		id21.setAtributeKey("FAKULTET_SF");
		id21.setTableKey("depart");

		ids21.add(id21);
		ids21.add(id22);

		k21.setIds(ids21);
		tab2.addKey(k21);
		tab2.setpKey(0);
		p2.addTable(tab2);

		// Tabela Katedra
		Table tab3 = new Table(p2, new ArrayList<Observer>());
		Translation t31 = new Translation("Chair", "enUS");
		ArrayList<Translation> trans31 = new ArrayList<Translation>();
		trans31.add(t31);
		NameTranslate nt31 = new NameTranslate();
		nt31.setCode("kat");
		nt31.setTranslate(trans31);
		tab3.setNaziv(nt31);

		// Atribut sk
		Atribut a31 = new Atribut();
		Domain d31 = new Domain();
		d31.setTip(Tip.VARCHAR);
		d31.setLength(10);
		a31.setDomain(d31);
		Translation t32 = new Translation("Chair code", "enUS");
		ArrayList<Translation> trans32 = new ArrayList<Translation>();
		trans32.add(t32);
		NameTranslate nt32 = new NameTranslate();
		nt32.setCode("SK");
		nt32.setTranslate(trans32);
		a31.setNull(false);
		a31.setUnique(true);
		a31.setName(nt32);
		tab3.addAtribute(a31);

		// Atribut nazk
		Atribut a32 = new Atribut();
		Domain d32 = new Domain();
		d32.setTip(Tip.VARCHAR);
		d32.setLength(20);
		a32.setDomain(d32);
		Translation t33 = new Translation("Chair name", "enUS");
		ArrayList<Translation> trans33 = new ArrayList<Translation>();
		trans33.add(t33);
		NameTranslate nt33 = new NameTranslate();
		nt33.setCode("NAZK");
		nt33.setTranslate(trans33);
		a32.setNull(false);
		a32.setUnique(true);
		a32.setName(nt33);
		tab3.addAtribute(a32);

		// Atribut Departman_Fakultet_SF
		Atribut a33 = new Atribut();
		Domain d33 = new Domain();
		d33.setTip(Tip.VARCHAR);
		d33.setLength(20);
		a33.setDomain(d3);
		Translation t34 = new Translation("Faculty code", "enUS");
		ArrayList<Translation> trans34 = new ArrayList<Translation>();
		trans34.add(t34);
		NameTranslate nt34 = new NameTranslate();
		nt34.setCode("DEPARTMAN_FAKULTET_SF");
		nt34.setTranslate(trans34);
		a33.setNull(false);
		a33.setUnique(true);
		a33.setName(nt34);
		tab3.addAtribute(a33);

		// Atribut Departman_SD
		Atribut a34 = new Atribut();
		Domain d34 = new Domain();
		d34.setTip(Tip.VARCHAR);
		d34.setLength(20);
		a34.setDomain(d3);
		Translation t35 = new Translation("Chair code", "enUS");
		ArrayList<Translation> trans35 = new ArrayList<Translation>();
		trans35.add(t35);
		NameTranslate nt35 = new NameTranslate();
		nt35.setCode("DEPARTMAN_SD");
		nt35.setTranslate(trans35);
		a34.setNull(false);
		a34.setUnique(true);
		a34.setName(nt35);
		tab3.addAtribute(a34);
		
		
		ArrayList<FKey> fkeys31 = new ArrayList<FKey>();
		FKey fk31 = new FKey();
		fk31.setConnectedTable("depart");
		ArrayList<String> foreignIds31 = new ArrayList<String>();
		foreignIds31.add("SD");
		foreignIds31.add("FAKULTET_SF");
		ArrayList<String> homeIds31 = new ArrayList<String>();
		homeIds31.add("DEPARTMAN_SD");
		homeIds31.add("DEPARTMAN_FAKULTET_SF");
		fk31.setForeignIds(foreignIds31);
		fk31.setHomeIds(homeIds31);
		fkeys31.add(fk31);
		
		tab3.setfKeys(fkeys31);

		Key k31 = new Key();
		ArrayList<Id_Id> ids31 = new ArrayList<Id_Id>();
		Id_Id id31 = new Id_Id();
		id31.setAtributeKey("SK");
		id31.setTableKey("kat");

		Id_Id id32 = new Id_Id();
		id32.setAtributeKey("DEPARTMAN_FAKULTET_SF");
		id32.setTableKey("kat");

		Id_Id id33 = new Id_Id();
		id33.setAtributeKey("DEPARTMAN_SD");
		id33.setTableKey("kat");
		
		ids31.add(id31);
		ids31.add(id32);
		ids31.add(id33);

		k31.setIds(ids31);
		tab3.addKey(k31);
		tab3.setpKey(0);
		p2.addTable(tab3);
		
		
		// Tabela postoji
		Table tab4 = new Table(p2, new ArrayList<Observer>());
		Translation t40 = new Translation("Exists", "enUS");
		ArrayList<Translation> trans40 = new ArrayList<Translation>();
		trans40.add(t40);
		NameTranslate nt40 = new NameTranslate();
		nt40.setCode("post");
		nt40.setTranslate(trans40);
		tab4.setNaziv(nt40);

		// Atribut postoji id
		Atribut a41 = new Atribut();
		Domain d41 = new Domain();
		d41.setTip(Tip.INT);
		a41.setDomain(d41);
		Translation t41 = new Translation("Exists id", "enUS");
		ArrayList<Translation> trans41 = new ArrayList<Translation>();
		trans41.add(t41);
		NameTranslate nt41 = new NameTranslate();
		nt41.setCode("POSTOJI_ID");
		nt41.setTranslate(trans41);
		a41.setNull(false);
		a41.setUnique(true);
		a41.setName(nt41);
		tab4.addAtribute(a41);

		// Atribut katedra_sk
		Atribut a42 = new Atribut();
		Domain d42 = new Domain();
		d42.setTip(Tip.VARCHAR);
		d42.setLength(10);
		a42.setDomain(d42);
		Translation t42 = new Translation("Chair id", "enUS");
		ArrayList<Translation> trans42 = new ArrayList<Translation>();
		trans42.add(t42);
		NameTranslate nt42 = new NameTranslate();
		nt42.setCode("KATEDRA_SK");
		nt42.setTranslate(trans42);
		a42.setNull(false);
		a42.setUnique(true);
		a42.setName(nt42);
		tab4.addAtribute(a42);

		// Atribut katedra_sd
		Atribut a43 = new Atribut();
		Domain d43 = new Domain();
		d43.setTip(Tip.VARCHAR);
		d43.setLength(10);
		a43.setDomain(d43);
		Translation t43 = new Translation("Department code", "enUS");
		ArrayList<Translation> trans43 = new ArrayList<Translation>();
		trans43.add(t43);
		NameTranslate nt43 = new NameTranslate();
		nt43.setCode("KATEDRA_SD");
		nt43.setTranslate(trans43);
		a43.setNull(false);
		a43.setUnique(true);
		a43.setName(nt43);
		tab4.addAtribute(a43);

		// Atribut Katedra_sf
		Atribut a44 = new Atribut();
		Domain d44 = new Domain();
		d44.setTip(Tip.VARCHAR);
		d44.setLength(20);
		a44.setDomain(d3);
		Translation t44 = new Translation("Faculty code", "enUS");
		ArrayList<Translation> trans44 = new ArrayList<Translation>();
		trans44.add(t44);
		NameTranslate nt44 = new NameTranslate();
		nt44.setCode("KATEDRA_SF");
		nt44.setTranslate(trans44);
		a44.setNull(false);
		a44.setUnique(true);
		a44.setName(nt44);
		tab3.addAtribute(a44);
		
		
		ArrayList<FKey> fkeys41 = new ArrayList<FKey>();
		FKey fk41 = new FKey();
		fk41.setConnectedTable("kat");
		ArrayList<String> foreignIds41 = new ArrayList<String>();
		foreignIds41.add("SK");
		foreignIds41.add("DEPARTMAN_SD");
		foreignIds41.add("DEPARTMAN_FAKULTET_SF");
		ArrayList<String> homeIds41 = new ArrayList<String>();
		homeIds41.add("KATEDRA_SK");
		homeIds41.add("KATEDRA_SD");
		homeIds41.add("KATEDRA_SF");
		fk41.setForeignIds(foreignIds41);
		fk41.setHomeIds(homeIds41);
		
		fkeys41.add(fk41);
		
		tab4.setfKeys(fkeys41);

		Key k41 = new Key();
		ArrayList<Id_Id> ids41 = new ArrayList<Id_Id>();
		Id_Id id41 = new Id_Id();
		id41.setAtributeKey("POSTOJI_ID");
		id41.setTableKey("post");

		ids41.add(id41);
	
		k41.setIds(ids41);
		tab4.addKey(k41);
		tab4.setpKey(0);
		p2.addTable(tab4);
		
		
		// Tabela predaje
		Table tab5 = new Table(p2, new ArrayList<Observer>());
		Translation t50 = new Translation("Teaches", "enUS");
		ArrayList<Translation> trans50 = new ArrayList<Translation>();
		trans50.add(t50);
		NameTranslate nt50 = new NameTranslate();
		nt50.setCode("pred");
		nt50.setTranslate(trans50);
		tab5.setNaziv(nt50);

		// Atribut je_zaposlen_je_zaposlen_id
		Atribut a51 = new Atribut();
		Domain d51 = new Domain();
		d51.setTip(Tip.INT);
		a51.setDomain(d51);
		Translation t51 = new Translation("Works id", "enUS");
		ArrayList<Translation> trans51 = new ArrayList<Translation>();
		trans51.add(t51);
		NameTranslate nt51 = new NameTranslate();
		nt51.setCode("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		nt51.setTranslate(trans51);
		a51.setNull(false);
		a51.setUnique(true);
		a51.setName(nt51);
		tab5.addAtribute(a51);

		// Atribut postoji postoji id
		Atribut a52 = new Atribut();
		Domain d52 = new Domain();
		d52.setTip(Tip.INT);
		a52.setDomain(d52);
		Translation t52 = new Translation("Exists id", "enUS");
		ArrayList<Translation> trans52 = new ArrayList<Translation>();
		trans52.add(t52);
		NameTranslate nt52 = new NameTranslate();
		nt52.setCode("KATEDRA_SK");
		nt52.setTranslate(trans52);
		a52.setNull(false);
		a52.setUnique(true);
		a52.setName(nt52);
		tab5.addAtribute(a52);
		
		ArrayList<FKey> fkeys51 = new ArrayList<FKey>();
		FKey fk51 = new FKey();
		fk51.setConnectedTable("post");
		ArrayList<String> foreignIds51 = new ArrayList<String>();
		foreignIds51.add("POSTOJI_ID");
		ArrayList<String> homeIds51 = new ArrayList<String>();
		homeIds51.add("POSTOJI_POSTOJI_ID");
		fk51.setForeignIds(foreignIds51);
		fk51.setHomeIds(homeIds51);
		fkeys51.add(fk51);
		
		FKey fk52 = new FKey();
		fk52.setConnectedTable("JE_ZAPOSLEN"); // OVDE GRBA
		ArrayList<String> foreignIds52 = new ArrayList<String>();
		foreignIds52.add("JE_ZAPOSLEN_ID");
		ArrayList<String> homeIds52 = new ArrayList<String>();
		homeIds52.add("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		fk52.setForeignIds(foreignIds52);
		fk52.setHomeIds(homeIds52);
		fkeys51.add(fk52);
		
		tab5.setfKeys(fkeys51);
		
		
		Key k51 = new Key();
		ArrayList<Id_Id> ids51 = new ArrayList<Id_Id>();
		Id_Id id51 = new Id_Id();
		id51.setAtributeKey("POSTOJI_POSTOJI_ID");
		id51.setTableKey("pred");
		ids51.add(id51);
		
		Id_Id id52 = new Id_Id();
		id52.setAtributeKey("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		id52.setTableKey("pred");
		ids51.add(id52);
	
		k51.setIds(ids51);
		tab5.addKey(k51);
		tab5.setpKey(0);
		p2.addTable(tab5);
		
		
		//JE ZAPOSLEN
		Table tab6 = new Table(p2, new ArrayList<Observer>());
		Translation t60 = new Translation("Is hired", "enUS");
		ArrayList<Translation> trans60 = new ArrayList<Translation>();
		trans60.add(t60);
		NameTranslate nt60 = new NameTranslate();
		nt60.setCode("JE_ZAPOSLEN");
		nt60.setTranslate(trans60);
		tab6.setNaziv(nt60);

		// Atribut KAATEDRA SK
		Atribut a61 = new Atribut();
		Domain d61 = new Domain();
		d61.setTip(Tip.VARCHAR);
		d61.setLength(10);
		a61.setDomain(d61);
		Translation t61 = new Translation("Chair id", "enUS");
		ArrayList<Translation> trans61 = new ArrayList<Translation>();
		trans61.add(t61);
		NameTranslate nt61 = new NameTranslate();
		nt61.setCode("KATEDRA_SK");
		nt61.setTranslate(trans61);
		a61.setNull(false);
		a61.setUnique(true);
		a61.setName(nt61);
		tab6.addAtribute(a61);
		
		// Atribut katedra sd
		Atribut a62 = new Atribut();
		Domain d62 = new Domain();
		d62.setTip(Tip.VARCHAR);
		d62.setLength(10);
		a62.setDomain(d62);
		Translation t62 = new Translation("Departman id", "enUS");
		ArrayList<Translation> trans62 = new ArrayList<Translation>();
		trans62.add(t62);
		NameTranslate nt62 = new NameTranslate();
		nt62.setCode("KATEDRA_SD");
		nt62.setTranslate(trans62);
		a62.setNull(false);
		a62.setUnique(true);
		a62.setName(nt62);
		tab6.addAtribute(a62);
		
		// Atribut katedra sf
		Atribut a63 = new Atribut();
		Domain d63 = new Domain();
		d63.setTip(Tip.VARCHAR);
		d63.setLength(10);
		a63.setDomain(d63);
		Translation t63 = new Translation("Fac id", "enUS");
		ArrayList<Translation> trans63 = new ArrayList<Translation>();
		trans63.add(t63);
		NameTranslate nt63 = new NameTranslate();
		nt63.setCode("KATEDRA_SF");
		nt63.setTranslate(trans63);
		a63.setNull(false);
		a63.setUnique(true);
		a63.setName(nt63);
		tab6.addAtribute(a63);
					
		// Atribut profesor mbr
		Atribut a64 = new Atribut();
		Domain d64 = new Domain();
		d64.setTip(Tip.INT);
		a64.setDomain(d64);
		Translation t64 = new Translation("Profesor number", "enUS");
		ArrayList<Translation> trans64 = new ArrayList<Translation>();
		trans64.add(t64);
		NameTranslate nt64 = new NameTranslate();
		nt64.setCode("PROFESOR_MBRP");
		nt64.setTranslate(trans64);
		a64.setNull(false);
		a64.setUnique(true);
		a64.setName(nt64);
		tab6.addAtribute(a64);
		
		// Atribut je zaposlen id
		Atribut a66 = new Atribut();
		Domain d66 = new Domain();
		d66.setTip(Tip.INT);
		a66.setDomain(d66);
		Translation t66 = new Translation("Is hired", "enUS");
		ArrayList<Translation> trans66 = new ArrayList<Translation>();
		trans66.add(t66);
		NameTranslate nt66 = new NameTranslate();
		nt66.setCode("JE_ZAPOSLEN_ID");
		nt66.setTranslate(trans66);
		a66.setNull(false);
		a66.setUnique(true);
		a66.setName(nt66);
		tab6.addAtribute(a66);
		
		ArrayList<FKey> fkeys61 = new ArrayList<FKey>();
		FKey fk61 = new FKey();
		fk61.setConnectedTable("kat");
		ArrayList<String> foreignIds61 = new ArrayList<String>();
		foreignIds61.add("KATEDRA_SK");
		foreignIds61.add("KATEDRA_SD");
		foreignIds61.add("KATEDRA_SF");
		ArrayList<String> homeIds61 = new ArrayList<String>();
		homeIds61.add("SK");
		homeIds61.add("DEPARTMAN_SD");
		homeIds61.add("DEPARTMAN_FAKULTET_SF");
		fk61.setForeignIds(foreignIds61);
		fk61.setHomeIds(homeIds61);
		fkeys61.add(fk61);
		tab6.setfKeys(fkeys61);

		Key k61 = new Key();
		ArrayList<Id_Id> ids61 = new ArrayList<Id_Id>();
		Id_Id id61 = new Id_Id();
		id61.setAtributeKey("JE_ZAPOSLEN_ID");
		id61.setTableKey("JE_ZAPOSLEN");

		ids61.add(id61);
	
		k61.setIds(ids61);
		tab6.addKey(k61);
		tab6.setpKey(0);
		p2.addTable(tab6);
		
		//PROFESOR
		Table tab7 = new Table(p2, new ArrayList<Observer>());
		Translation t70 = new Translation("Prosfesor", "enUS");
		ArrayList<Translation> trans70 = new ArrayList<Translation>();
		trans70.add(t70);
		NameTranslate nt70 = new NameTranslate();
		nt70.setCode("Profesor");
		nt70.setTranslate(trans70);
		tab7.setNaziv(nt70);
		
		//atribut mbr
		Atribut a71 = new Atribut();
		Domain d71 = new Domain();
		d71.setTip(Tip.INT);
		a71.setDomain(d71);
		Translation t71 = new Translation("Seriall number", "enUS");
		ArrayList<Translation> trans71 = new ArrayList<Translation>();
		trans71.add(t71);
		NameTranslate nt71 = new NameTranslate();
		nt71.setCode("MBRP");
		nt71.setTranslate(trans71);
		a71.setNull(false);
		a71.setUnique(true);
		a71.setName(nt71);
		tab7.addAtribute(a71);
		
		//atribut imep
		Atribut a72 = new Atribut();
		Domain d72 = new Domain();
		d72.setTip(Tip.VARCHAR);
		a72.setDomain(d72);
		d72.setLength(15);
		Translation t72 = new Translation("Name	of profesor", "enUS");
		ArrayList<Translation> trans72 = new ArrayList<Translation>();
		trans72.add(t72);
		NameTranslate nt72 = new NameTranslate();
		nt72.setCode("IMEP");
		nt72.setTranslate(trans72);
		a72.setNull(false);
		a72.setUnique(true);
		a72.setName(nt72);
		tab7.addAtribute(a72);
		
		//atribut prezime
		Atribut a73 = new Atribut();
		Domain d73 = new Domain();
		d73.setTip(Tip.VARCHAR);
		d73.setLength(15);
		a73.setDomain(d73);
		Translation t73 = new Translation("Surname of profesor", "enUS");
		ArrayList<Translation> trans73 = new ArrayList<Translation>();
		trans72.add(t73);
		NameTranslate nt73 = new NameTranslate();
		nt73.setCode("PRZP");
		nt73.setTranslate(trans73);
		a73.setNull(false);
		a73.setUnique(true);
		a73.setName(nt73);
		tab7.addAtribute(a73);
		
		//atribut jejez
		Atribut a74 = new Atribut();
		Domain d74 = new Domain();
		d74.setTip(Tip.INT);
		a74.setDomain(d74);
		Translation t74 = new Translation("Is hired at", "enUS");
		ArrayList<Translation> trans74 = new ArrayList<Translation>();
		trans74.add(t74);
		NameTranslate nt74 = new NameTranslate();
		nt74.setCode("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		nt74.setTranslate(trans74);
		a74.setNull(false);
		a74.setUnique(true);
		a74.setName(nt74);
		tab7.addAtribute(a74);
		
		ArrayList<FKey> fkeys71 = new ArrayList<FKey>();
		FKey fk71 = new FKey();
		fk71.setConnectedTable("JE_ZAPOSLEN");
		ArrayList<String> foreignIds71 = new ArrayList<String>();
		foreignIds71.add("JE_ZAPOSLEN_ID");
		ArrayList<String> homeIds71 = new ArrayList<String>();
		homeIds71.add("JE_ZAPOSLEN_JE_ZAPOSLEN_ID");
		fk71.setForeignIds(foreignIds71);
		fk71.setHomeIds(homeIds71);
		fkeys71.add(fk71);
		tab7.setfKeys(fkeys71);

		Key k71 = new Key();
		ArrayList<Id_Id> ids71 = new ArrayList<Id_Id>();
		Id_Id id71 = new Id_Id();
		id71.setAtributeKey("PROFESOR");
		id71.setTableKey("MBRP");

		ids71.add(id71);
	
		k71.setIds(ids71);
		tab7.addKey(k71);
		tab7.setpKey(0);
		p2.addTable(tab7);
		
		return p2;
	}
	
	public Table initTableTest() {
		// Tabela Fakultet
		Table tab1 = new Table(null, new ArrayList<Observer>());
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

		return tab1;
	}
	
}
