package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.PaperView;
import module.DBConnection;

public class PaperDAO {
	
	private final String INSERT_PAPER = "insert into paper (lat, lng, region, title, content, p_time, user_code)"
			+ " values (?,?,?,?,?,?,?);";
	private final String GET_ALL_PAPERS = "select * from paper_view;";
	
	private static PaperDAO paperDAO;
	static {
		paperDAO = new PaperDAO();
	}
	public static PaperDAO getInstance() {
		return paperDAO;
	}
	private PaperDAO(){}
	
	public ArrayList<PaperView> getAllPapers() {
		
		PreparedStatement pstmt = null;
		ArrayList<PaperView> paperList = null;
		PaperView paperView = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			paperList = new ArrayList<PaperView>();
			
			conn = DBConnection.getInstance().getConn();
			pstmt = conn.prepareStatement(GET_ALL_PAPERS);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				paperView = new PaperView(rs.getString("user_code"), rs.getString("id"),
						rs.getString("paper_code"), rs.getString("lat"), rs.getString("lng"), rs.getString("region"),
						rs.getString("title"), rs.getString("content"), rs.getTimestamp("p_time"));
				
				paperList.add(paperView);
			}
			System.out.println("PaperList Count : " + paperList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return paperList;
	}
	
	public int insertPaper(PaperView paperView) {
		
		System.out.println("Insert Paper : " + paperView);
		
		Connection conn = null; 
		PreparedStatement pstmt = null;
		int res = 0;
		
		try {
			conn = DBConnection.getInstance().getConn();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(INSERT_PAPER);
			pstmt.setString(1,paperView.getLat());
			pstmt.setString(2,paperView.getLng());
			pstmt.setString(3,paperView.getRegion());
			pstmt.setString(4,paperView.getTitle());
			pstmt.setString(5,paperView.getContent());
			pstmt.setTimestamp(6,new Timestamp(System.currentTimeMillis()));
			pstmt.setString(7,paperView.getUserCode());
			
			res = pstmt.executeUpdate();
			conn.commit();
		
		} catch (SQLException e ) {
	        e.printStackTrace();
	        if (conn != null) {
	            try {
	                System.err.print("Transaction is being Rolled back");
	                conn.rollback();
	            } catch(SQLException se) {
	                se.printStackTrace();
	            }
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	if (conn != null) {
	            try {
	                System.err.print("Transaction is being Rolled back");
	                conn.rollback();
	            } catch(SQLException se) {
	                se.printStackTrace();
	            }
	        }
	    } finally {
	    	if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) {
            	try { 
            		conn.setAutoCommit(true);
                	//conn.close(); 
            	} catch(SQLException ex) {}
            } 
	    }
		return res;
	}
	
	public static void main(String[] args) {
		
	}

}
