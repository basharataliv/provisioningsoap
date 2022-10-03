/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provisioning.gateway.tabs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisioning.gateway.config.PropertyFileReading;




public class DBConnection {
	private final static Logger logger = LoggerFactory.getLogger(DBConnection.class);
	private static DataSource  dataSource = null;
	private static Connection connection =null;
	
	 public static Connection getConnection()  {
		
		 try {	
		 DataSource dataSource = getESBDataSource(); 
		 connection = dataSource.getConnection();
			} catch (Exception e) {
				logger.error("error",e);
			}
	    	return connection;
	     }
	
	   

	public static DataSource getESBDataSource(){

		 if(dataSource == null){
	            DataSource eds = new DataSource();
	            PoolProperties p = new PoolProperties();
	            String dburl =PropertyFileReading.getProperty("tabs.db.url");
    		    String username = PropertyFileReading.getProperty("tabs.db.username");
    		    String password = PropertyFileReading.getProperty("tabs.db.password");
    		    String dbDeiver = PropertyFileReading.getProperty("tabs.db.driver");
    		   
    	    try {
    	    	 Class.forName(dbDeiver);
				} catch (Exception e) {
					logger.error("error",e);
				}
			    p.setDriverClassName(dbDeiver);
			    p.setUrl(dburl);
	            p.setUsername(username);
	            p.setPassword(password);
	            p.setInitialSize(4);
	            p.setMaxActive(5);
	            p.setMaxIdle(4);
	            p.setMinIdle(1);
	            p.setMaxWait(3000);
	            p.setTestOnBorrow(true);
	            p.setRemoveAbandoned(true);
	            p.setRemoveAbandonedTimeout(55);
	            p.setValidationQuery("select 1 from dual");
	            p.setTestWhileIdle(true);
	            eds.setPoolProperties(p);
	            dataSource = eds;
	        }
		 
		 return dataSource;
	}
	
}