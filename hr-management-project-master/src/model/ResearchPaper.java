package model;

public class ResearchPaper {
	private String title;
	private String description;
	private String date;
	private boolean isFirstAuthor;
	private String journals;
	public ResearchPaper(String title, String description, String date, boolean isFirstAuthor, String journals) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
		this.isFirstAuthor = isFirstAuthor;
		this.journals = journals;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isFirstAuthor() {
		return isFirstAuthor;
	}
	public void setFirstAuthor(boolean isFirstAuthor) {
		this.isFirstAuthor = isFirstAuthor;
	}
	public String getJournals() {
		return journals;
	}
	public void setJournals(String journals) {
		this.journals = journals;
	}
	
	
}
