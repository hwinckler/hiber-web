package dao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import dbunit.DBUnitLoad;

public class NovoDAOTest extends DBUnitLoad{

	final static String dataSet = "test/integration/dao/NovoDataset.xml";
	
	@BeforeClass
	public static void init() throws Exception {
		setUp(dataSet);
	}
	
	@Test
	public void getUsuers() throws Exception{
	
		Long count = new NovoDAO().getUserCount();
		
		Assert.assertEquals(3, count.longValue());
		
	}
	
	@AfterClass
	public static void end() throws Exception {
		tearDown();
	}
}
