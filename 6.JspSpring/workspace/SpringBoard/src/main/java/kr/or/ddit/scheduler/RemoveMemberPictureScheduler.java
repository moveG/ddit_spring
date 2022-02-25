package kr.or.ddit.scheduler;

import java.io.File;

import kr.or.ddit.service.spring.ScheduledMemberService;

public class RemoveMemberPictureScheduler {
	
	private ScheduledMemberService scheduledMemberService;
	public void setScheduledMemberService(ScheduledMemberService scheduledMemberService) {
		this.scheduledMemberService = scheduledMemberService;
	}
	
	private String picturePath;
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	public void removePicture() throws Exception {
		File dir = new File(picturePath);
		File[] files = dir.listFiles();
		
		if(files != null) {
			for(File file : files) {
				if(scheduledMemberService.getMemberByPicture(file.getName()) == null) {
					file.delete();
				}
			}
		}
	}
}
