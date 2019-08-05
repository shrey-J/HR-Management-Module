package model;

public class Conference {
	private String topic;
	private String description;
	private boolean isSpeaker;
	private String attendedOrConducted;
	private String startDate;
	private String endDate;
	public Conference(String topic, String description, boolean isSpeaker, String attendedOrConducted,
			String startDate, String endDate) {
		super();
		this.topic = topic;
		this.description = description;
		this.isSpeaker = isSpeaker;
		this.attendedOrConducted = attendedOrConducted;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isSpeaker() {
		return isSpeaker;
	}
	public void setWasSpeaker(boolean wasSpeaker) {
		this.isSpeaker = isSpeaker;
	}
	public String getAttendedOrConducted() {
		return attendedOrConducted;
	}
	public void setAttendedOrConducted(String attendedOrConducted) {
		this.attendedOrConducted = attendedOrConducted;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
