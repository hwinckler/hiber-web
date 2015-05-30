package dbunit;

import java.io.FileInputStream;
import java.sql.Connection;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.HibernateUtil;

public class DBUnitLoad{

	private static final Logger logger = LoggerFactory.getLogger(DBUnitLoad.class);
	
	private static IDataSet idataSet = null;
	
	public static void setUp(final String dataSet) throws Exception{
		
		logger.debug("setUp...");
		
		HibernateUtil.buildTest();
		
		Connection connection = ((SessionFactoryImplementor) HibernateUtil.getSessionFactory()).getConnectionProvider().getConnection();
		
		DatabaseConnection databaseConnection = new DatabaseConnection(connection);
				
		idataSet = new FlatXmlDataSetBuilder().build(new FileInputStream(dataSet));

        DatabaseOperation.INSERT.execute(databaseConnection, idataSet);
        
        connection.commit();
        
        //session.close();
        
	}
	
	public static void tearDown() throws Exception{
		
		logger.debug("tearDown...");
		
		Connection connection = ((SessionFactoryImplementor) HibernateUtil.getSessionFactory()).getConnectionProvider().getConnection();
		
		DatabaseConnection databaseConnection = new DatabaseConnection(connection);	
						
        DatabaseOperation.TRUNCATE_TABLE.execute(databaseConnection, idataSet);
        
        //session.close();
 
        
	}
}
