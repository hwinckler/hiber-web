package main;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import util.HibernateUtil;
import beans.Usuario;

public class Main {

	
	public static void main(String[] args) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		  
        session.beginTransaction();
 
        Usuario usuario = new Usuario();
        usuario.setNome("Wick");
        
        session.save(usuario);
 
        session.getTransaction().commit();
 
        Query q = session.createQuery("From Usuario");
                 
        List<Usuario> resultList = q.list();
        System.out.println("num of usuario:" + resultList.size());
        for (Usuario next : resultList) {
            System.out.println("next employee: " + next.getNome());
        }
        
        session.close();
		
	}
	
}

