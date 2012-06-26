package br.ufrpe.persi.simuladorRede.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SimuladorRedeTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite(SimuladorRedeTestSuite.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(ModeloTestCase.class);
		suite.addTestSuite(SessionManagerTestCase.class);
		//$JUnit-END$
		return suite;
	}

}
