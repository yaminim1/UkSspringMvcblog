package com.uk.daoImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uk.dao.UserDao;
import com.uk.model.Users;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory session;

	@SuppressWarnings("unchecked")
	public Users findByName(String username) {
		List<Users> users = new ArrayList<Users>();

		users = session.getCurrentSession().createQuery("from Users where name=:username")
				.setParameter("username", username).list();
		System.out.println("users size");
		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}

	}

}
