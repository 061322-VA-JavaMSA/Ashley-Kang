package com.revature.util;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.daos.UserHibernate;
import com.revature.models.Ticket;
import com.revature.models.User;


public class HibernateUtil {
private static SessionFactory sf;
private static Logger log = LogManager.getLogger(HibernateUtil.class);
	
	public static SessionFactory getSessionFactory() {
		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			properties.load(loader.getResourceAsStream("connections.properties"));
		} catch (IOException e) {
			log.error("Exception thrown:" + e.fillInStackTrace());
		}
		
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml").addProperties(properties);
		config.addAnnotatedClass(User.class);
		config.addAnnotatedClass(Ticket.class);
		
		ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		
		if(sf == null || sf.isClosed() == true) {
			sf = config.buildSessionFactory(sr);
		}
		return sf;
	}
}
