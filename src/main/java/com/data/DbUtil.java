package com.data;

import java.net.URI;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.model.*;

public class DbUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactorys() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				// Hibernate settings equivalent to hibernate.cfg.xml's properties

				Properties settings = new Properties();
				/*
				 * settings.put(Environment.DRIVER, "org.postgresql.Driver");
				 * settings.put(Environment.URL,
				 * "jdbc:postgresql://ec2-44-194-225-27.compute-1.amazonaws.com:5432/dbpncaer12ig4p"
				 * );
				 * settings.put(Environment.USER, "tmchqrkqisyfqw");
				 * settings.put(Environment.PASS,
				 * "6cbad36d7efbdf936d6dfc94841fc17c1f518782d15ab48cfff785f24976d9c6");
				 */
				settings.put(Environment.DRIVER, "org.postgresql.Driver");
				settings.put(Environment.URL,
						"jdbc:postgresql://ec2-18-204-170-75.compute-1.amazonaws.com:5432/dcti81nb4p0eg8");
				settings.put(Environment.USER, "hskuhhcqrrezwy");
				settings.put(Environment.PASS, "6be2eb1fbef51ea763728e8a59b28b89af919f112573db96a23fe218109265b2");

				settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

				/*
				 * settings.put(Environment.URL,
				 * "jdbc:mysql://database-1.ctmpvntqk27b.ap-southeast-1.rds.amazonaws.com:3306/book_store"
				 * );
				 * settings.put(Environment.USER, "admin");
				 * settings.put(Environment.PASS, "chobeosuiroi:sadcow");
				 * settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
				 * settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				 * settings.put(Environment.SHOW_SQL, "true");
				 */
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

//				settings.put(Environment.HBM2DDL_AUTO, "update");
				 settings.put(Environment.HBM2DDL_AUTO, "create-drop");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(Category.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Order.class);
				configuration.addAnnotatedClass(Item.class);
				configuration.addAnnotatedClass(Cart.class);
				configuration.addAnnotatedClass(LineItem.class);
				configuration.addAnnotatedClass(Review.class);
				configuration.addAnnotatedClass(Photo.class);
				configuration.addAnnotatedClass(File.class);
				configuration.addAnnotatedClass(Promo.class);
				configuration.addAnnotatedClass(CheckPromo.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;

	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties settings = new Properties();

				String DATABASE_URL = System.getenv("DATABASE_URL");


					// Mysql of local
					settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
					settings.put(Environment.URL,
							"jdbc:mysql://localhost:3306/book_store?allowPublicKeyRetrieval=true&useSSL=false");
					settings.put(Environment.USER, "root");
					settings.put(Environment.PASS, "123456789");

					// Mysql of AWS

				

				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "create-drop");

//				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(Category.class);
				configuration.addAnnotatedClass(Product.class);
				configuration.addAnnotatedClass(Order.class);
				configuration.addAnnotatedClass(Item.class);
				configuration.addAnnotatedClass(Cart.class);
				configuration.addAnnotatedClass(LineItem.class);
				configuration.addAnnotatedClass(Review.class);

				configuration.addAnnotatedClass(Promo.class);
				configuration.addAnnotatedClass(File.class);
				configuration.addAnnotatedClass(Photo.class);
				configuration.addAnnotatedClass(CheckPromo.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				System.out.println("Hibernate Java Config serviceRegistry created");
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}