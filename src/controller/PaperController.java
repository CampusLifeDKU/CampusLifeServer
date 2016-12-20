package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dto.PaperView;
import manager.PaperManager;

/**
 * Servlet implementation class PaperController
 */
public class PaperController extends HttpServlet {
	private static final long serialVersionUID = 1L;   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json;charset=utf-8");
		
		String service = request.getParameter("service");
		String userCode = request.getParameter("userCode");
		
		PaperManager paperMngr = PaperManager.getInstance();
		
		if (service != null) {
			
			if (service.equals("get")) {
				
				jsonOut(paperMngr.getAllPapers(), response);
				
			} else if (service.equals("add")) {
				String lat = request.getParameter("lat");
				String lng = request.getParameter("lng");
				String region = request.getParameter("region");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				
				PaperView paperView = new PaperView(userCode, null, null, lat, lng, region, title, content, null);
				//System.out.println("[Insert Paper]\n" + paperView + "\n");
				jsonOut(paperMngr.insertPaper(paperView), response);
			}
		}
		
	}
	
	private void jsonOut(int result, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject paperJson = new JSONObject();
		try {
			
			if (result > 0) {
				// insert 성공 
				System.out.println("[Insert Paper Succeed]\n");
				paperJson.put("resultCode", "1");
				paperJson.put("timestamp", System.currentTimeMillis());
			} else {
				// insert 실패 
				paperJson.put("resultCode", "0");
				paperJson.put("errorCode", "1");
				paperJson.put("errorDescription", "Failed to insert the Paper.");
			}
			
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			paperJson.put("resultCode", "-1");
			paperJson.put("errorCode", "");
			paperJson.put("errorDescription", "Exception");
			
		} finally {
			PrintWriter pw = response.getWriter();
			pw.print(paperJson.toString());
			pw.close();
		}
	}
	
	private void jsonOut(ArrayList<PaperView> paperList, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject paperJson = new JSONObject();
		try {
			
			if(paperList != null) {
				
				paperJson.put("resultCode", "1");
				paperJson.put("timestamp", System.currentTimeMillis());
				JSONArray paperJSONArray = new JSONArray();
				JSONObject pJson = null;
				for( PaperView paper : paperList) {
					
					pJson = new JSONObject();
					pJson.put("userCode", paper.getUserCode());
					pJson.put("id", paper.getId());
					pJson.put("paperCode", paper.getPaperCode());
					pJson.put("lat", paper.getLat());
					pJson.put("lng", paper.getLng());
					pJson.put("region", paper.getRegion());
					pJson.put("title", paper.getTitle());
					pJson.put("content", paper.getContent());
					pJson.put("p_time", paper.getP_time().getTime());
					
					paperJSONArray.add(pJson);
				}
				paperJson.put("paperList", paperJSONArray);
				
			} else {
				// get 실패 
				paperJson.put("resultCode", "0");
				paperJson.put("errorCode", "1");
				paperJson.put("errorDescription", "No Paper list.");
			}
		} catch(NullPointerException npe) {
			npe.printStackTrace();
			paperJson.put("resultCode", "-1");
			paperJson.put("errorCode", "");
			paperJson.put("errorDescription", "Exception");
			
		} finally {
			System.out.println("[Paper Json Out]\n" + paperJson.toString() + "\n");
			PrintWriter pw = response.getWriter();
			pw.print(paperJson.toString());
			pw.close();
		}
		
	}

}
