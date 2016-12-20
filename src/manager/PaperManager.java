package manager;

import java.util.ArrayList;

import dao.PaperDAO;
import dto.PaperView;

public class PaperManager {
	
	private static final PaperManager instance = new PaperManager();
	private static PaperDAO paperDAO;
	
	private PaperManager() {
		paperDAO = PaperDAO.getInstance();
	}
	
	public static PaperManager getInstance() {
		return instance;
	}

	public ArrayList<PaperView> getAllPapers() {
		return paperDAO.getAllPapers();
	}
	
	public int insertPaper(PaperView paperView) {
		return paperDAO.insertPaper(paperView);
	}

}
