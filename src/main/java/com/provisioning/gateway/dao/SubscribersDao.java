package com.provisioning.gateway.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.provisioning.gateway.model.Subscriber;


@Component
public class SubscribersDao {
	private final static Logger logger = LoggerFactory.getLogger(SubscribersDao.class);
	
	public  Subscriber findByAparty(String a_party) {
		String query="select s.a_party, s.opr_type, s.created_date, s.status, s.status_update_dt,s.service_id, s.sub_date, s.channel, s.hlractivation_date, s.unsub_date,s.unsub_channel, s.hlr_deactivation_date, s.charge_dt, s.next_charge_dt from subscribers_{t} s  where s.a_party = {a_party}";
		int index = Integer.parseInt(a_party.substring(a_party.length() - 1));
		query=query.replace("{t}", index+"").replace("{a_party}", a_party);
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Connection connection=null;
		Subscriber sub=null;
		
		try {
			connection=getConnection();
			preparedStatement = connection.prepareStatement(query);
			rs = preparedStatement.executeQuery();
	       
	            while (rs.next()) {
	            	sub=new Subscriber(rs.getString("a_party"),  rs.getInt("opr_type"),rs.getDate("created_date"),rs.getInt("status"),rs.getDate("status_update_dt"), rs.getInt("service_id"),rs.getDate("sub_date"),rs.getString("channel"), rs.getDate("hlractivation_date"),rs.getDate("unsub_date"),rs.getString("unsub_channel"),rs.getDate("hlr_deactivation_date"),rs.getDate("charge_dt"),rs.getDate("next_charge_dt"));
	            }
		} catch (Exception e) {
			logger.error("Error in fetching data",e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
					
				} catch (SQLException e) {
					logger.error("Error in fetching data",e);
				}
			}
			if (connection != null) {
				try {
					
					connection.close();
				} catch (SQLException e) {
					logger.error("Error in fetching data",e);
				}
			}
		}
		return sub;
		
	}
	
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String pass;
	@Value("${spring.datasource.driver-class-name}")
	private String driver;
	
	public  Connection getConnection() {
		Connection con = null;
		try {
			// load the Driver Class
			Class.forName(driver);
			// create the connection now
			con = DriverManager.getConnection(url, username,pass);
		} catch (ClassNotFoundException e) {
			logger.error("error to connect to the tabs",e);
		} catch (Exception e) {
			logger.error("error to connect to the tabs",e);
		}
		return con;
	}
}
