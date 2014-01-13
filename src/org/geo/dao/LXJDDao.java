package org.geo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.geo.db.DBEntry;


/**
 * 
 * @author lenovo
 *
 */
public class LXJDDao {

	private String getSegmentSql(double x, double y, double detax, double detay){
		double abx,abx2,aby,aby2;
		abx = x-detax;
		abx2 = x+detax;
		aby = y-detay;
		aby2 = y+detay;
		return "select * from lxjd where id not in(" +
					"select id from lxjd t where "+abx+">=t.maxx or " +
					abx2+"<=t.minx or "+aby+">=t.maxy or "+aby2+"<=t.miny " +
					"or minx is null)";
	}
	/**
	 * 获取总的记录数
	 * @param sql
	 * @return
	 */
	public static void getSegment(double x, double y, double detax, double detay){
		String sql = "";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBEntry.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			if (rs.next()) {
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null){
					rs.close();
				}
				if(pstmt!=null){
					pstmt.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
