/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.provisioning.gateway.tabs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.provisioning.gateway.config.PropertyFileReading;

/**
 *
 * @author saif
 */
public class VipCallWarid {
	private final static Logger logger = LoggerFactory.getLogger(VipCallWarid.class);

	/**
	 * @param args the command line arguments
	 */
//	public static void main(String[] args) {
//		// TODO code application logic here
//
//		String Msisdn = args[0]; // billing partno
//		String Command = args[1]; // INST/DISC
//		// String Msisdn="03222180632";
//		// int part_no = Integer.parseInt(partno);
//
//		logger.info(" Warid Msisdn = " + Msisdn);
//
//		Connection con = null;
//		CallableStatement stmt = null;
//
//		// Class.forName(driver_class);
//		con = DBConnection.getConnection();
//
//		// OracleCallableStatement ocs =
//		// (OracleCallableStatement)conn.prepareCall(
//		// "{? = call acpks_stmt_gen.fn_stmt_gen(?,?,?,?,?,?)}");
//		// CallableStatement ocs;
//		try {
//			stmt = con.prepareCall("{call TCS.VENDORS_SERVICE_CHANGE( ?, ?, ?, ?, ? )}");
//
//			stmt.setString(1, Msisdn);
//			stmt.setString(2, PropertyFileReading.getProperty("tabs.user"));
//			stmt.setString(3, PropertyFileReading.getProperty("tabs.password"));
//			stmt.setString(4, Command);
//			stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
//			// ocs.setNull(6, java.sql.Types.DATE) ;
//			// ocs.setNull(7, java.sql.Types.DATE);
//
//			stmt.execute();
//
//			String RETVAL = stmt.getString(5);
//			if (RETVAL != null) {
//				logger.info("RETVAL=" + RETVAL);
//			} else {
//
//				logger.info("RETVAL=" + RETVAL);
//				logger.info("RETVAL Not Found with ID " + Msisdn + "");
//			}
//
//			getSubscriberType(Msisdn);
//			getNumStatus(Msisdn);
//			getServiceStatus(Msisdn);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				stmt.close();
//				con.close();
//				// input.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	/**
	 * 
	 * @param msisdn
	 * @param command
	 */
	public static int sendTabsRequest(String Msisdn, String Command) {// INST/DISC

		int rv = -1;
		logger.info(" Warid Msisdn = " + Msisdn);

		Connection con = null;
		CallableStatement stmt = null;

		con = DBConnection.getConnection();
		try {
			stmt = con.prepareCall("{call TCS.VENDORS_SERVICE_CHANGE( ?, ?, ?, ?, ? )}");

			stmt.setString(1, Msisdn);
			stmt.setString(2, PropertyFileReading.getProperty("tabs.user"));
			stmt.setString(3, PropertyFileReading.getProperty("tabs.password"));
			stmt.setString(4, Command);
			stmt.registerOutParameter(5, java.sql.Types.VARCHAR);
			// ocs.setNull(6, java.sql.Types.DATE) ;
			// ocs.setNull(7, java.sql.Types.DATE);

			stmt.execute();

			String RETVAL = stmt.getString(5);
			if (RETVAL != null) {
				logger.info("RETVAL=" + RETVAL);
				return 0;

			} else {

				logger.info("RETVAL=" + RETVAL);
				logger.info("RETVAL Not Found with ID " + Msisdn + "");
			}


		} catch (Exception e) {
			logger.error("error in tabs",e);
		} finally {
			try {
				stmt.close();
				con.close();
				// input.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rv;
	}

	/**
	 * To get subscriber type prepaid/postpaid
	 * 
	 * @param Num
	 * @return
	 * @throws SQLException
	 */

	public static ArrayList getSubscriberType(String Num) throws SQLException {

		Connection conn = null;
		// String ret = "";
		conn = DBConnection.getConnection();
		String proc3StoredProcedure = "select subno,prepost_paid from TCS.VENDOR_INFO_VW where subno = ?";
		PreparedStatement ps;
		ArrayList ret = new ArrayList();
		try {
			ps = conn.prepareStatement(proc3StoredProcedure);
			ps.setString(1, Num);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// ret = rs.getString(1);

				ret.add(rs.getString(1));
				ret.add(rs.getString(2));
				// log.debug("MSISDN:" + Num + " OnNet/OffNet:" + ret);
			}
			logger.info("getSubscriberType => RETVAL = " + ret.toString());
			ps.close();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		conn.close();

//        if (ret.isEmpty() || ret.equals("")) {
//            ret = "OFFNET";
//        }
		return ret;
	}

	public static ArrayList getNumStatus(String Num) throws SQLException {

		Connection conn = null;
		// String ret = "";
		conn = DBConnection.getConnection();
		String proc3StoredProcedure = "select subno,status from TCS.VENDOR_INFO_VW where subno = ?";
		PreparedStatement ps;
		ArrayList ret = new ArrayList();
		try {
			ps = conn.prepareStatement(proc3StoredProcedure);
			ps.setString(1, Num);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// ret = rs.getString(1);

				ret.add(rs.getString(1));
				ret.add(rs.getString(2));
				// log.debug("MSISDN:" + Num + " OnNet/OffNet:" + ret);
			}
			logger.info("GetNumSTATUS => RETVAL = " + ret.toString());
			ps.close();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		conn.close();

//        if (ret.isEmpty() || ret.equals("")) {
//            ret = "OFFNET";
//        }
		return ret;
	}

	public static ArrayList getServiceStatus(String Num) throws SQLException {

		Connection conn = null;
		// String ret = "";
		conn = DBConnection.getConnection();
		String proc3StoredProcedure = "select SUBNO, EQUIPID from TCS.CRM_USER_EQUIPMENTS_VW WHERE SUBNO = " + Num
				+ " AND EQUIPID = 'VIPCALL'";
		PreparedStatement ps;
		ArrayList ret = new ArrayList();
		try {
			ps = conn.prepareStatement(proc3StoredProcedure);
			// ps.setString(1, Num);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// ret = rs.getString(1);

				ret.add(rs.getString(1));
				ret.add(rs.getString(2));
				// log.debug("MSISDN:" + Num + " OnNet/OffNet:" + ret);
			}
			logger.info("getServiceStatus => RETVAL = " + ret.toString());
			ps.close();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		conn.close();

//        if (ret.isEmpty() || ret.equals("")) {
//            ret = "OFFNET";
//        }
		return ret;
	}

}
