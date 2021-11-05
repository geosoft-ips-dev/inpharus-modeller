package Datas;

import java.awt.Point;

public class RecordingInfo {
	private static RecordingInfo info;
	
	public boolean isRecording;
	public int pos_x = -1;
	public int pos_y = -1;
	
	public int recordingCount;
	
	public static RecordingInfo getInstance() {
		if(info == null)
			info = new RecordingInfo();
		
		return info;
	}
	
	public void startRecording(String loc_x, String loc_y) {
		isRecording = true;
		this.pos_x = Integer.parseInt(loc_x);
		this.pos_y = Integer.parseInt(loc_y);
		recordingCount = 0;
	}
	
	public void stopRecording() {
		isRecording = false;
		this.pos_x = -1;
		this.pos_y = -1;
	}
	
	public void increaseRecordCount() {
		recordingCount++;
	}
	
	public int getRecordCount() {
		return recordingCount;
	}
	
	public Point getPosition() {
		return new Point(pos_x, pos_y);
	}
	
	public int getXPos() {
		return pos_x;
	}
	
	public int getYPos() {
		return pos_y;
	}

}
