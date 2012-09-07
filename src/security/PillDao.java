package security;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.dentus.HibernateUtil;
import com.dentus.Pacjent;


public class PillDao
{
	public PillDao()
	{
		
	}
	public PillUser2 findByLogin(String username)
	{
		Session session =HibernateUtil.getSession();
		@SuppressWarnings("unchecked")
		List<PillUser2> list =(session.createCriteria(PillUser2.class).add(Restrictions.like("username",username))).list();
	return list.get(0);
	}
	public void dodajPillUsera(PillUser2 puser) 
	{
		
		Session session =HibernateUtil.getSession();
		session.saveOrUpdate(puser);	
		session.flush();
	}

}
