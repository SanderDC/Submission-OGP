package hillbillies.tests.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;

import hillbillies.model.*;
import hillbillies.model.statements.SequenceStatement;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.programs.SourceLocation;

import org.junit.Test;
public class Dodge {

	static Unit unit;
	static World world;
	@Before
	public void setUp() throws Exception {
		int [][][]coordinates= new int [2][2][2];

		world=new World(coordinates, new DefaultTerrainChangeListener());
		unit= new Unit(world, false);
	}
	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			unit.dodge();
		}
			

	}

}
