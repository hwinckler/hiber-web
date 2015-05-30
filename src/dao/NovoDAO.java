package dao;

import org.hibernate.Session;

import util.HibernateUtil;

public class NovoDAO {

	public Long getUserCount() throws Exception{
		Session session = null;
		
		try{
			
			session = HibernateUtil.getSession();
		
			return (Long) session.createQuery("select count(*) from Novo").uniqueResult();
		}
		catch(Exception e){
			throw e;
		}
		finally{
			if(session != null){
				session.close();
			}
		}
		
	}
}
