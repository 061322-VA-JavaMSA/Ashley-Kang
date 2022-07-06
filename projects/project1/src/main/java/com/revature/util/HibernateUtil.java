package com.revature.util;

import java.io.IOException;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.models.User;


public class HibernateUtil {
private static SessionFactory sf;
	
	public static SessionFactory getSessionFactory() {
		Properties properties = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		try {
			properties.load(loader.getResourceAsStream("connections.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Configuration config = new Configuration();
		config.configure("hibernate.cfg.xml").addProperties(properties);
		config.addAnnotatedClass(User.class);
		
		ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		
		if(sf == null || sf.isClosed() == true) {
			sf = config.buildSessionFactory(sr);
		}
		return sf;
	}
}
