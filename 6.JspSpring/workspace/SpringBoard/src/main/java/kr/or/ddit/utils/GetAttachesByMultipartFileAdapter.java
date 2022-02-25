package kr.or.ddit.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.dto.AttachVO;
import com.jsp.utils.MakeFileName;

public class GetAttachesByMultipartFileAdapter {
	
	public static List<AttachVO> save(List<MultipartFile> multiFiles, String savePath) throws Exception {
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		//저장 -> AttachVO -> list.add
		if(multiFiles != null) {
			for(MultipartFile multi : multiFiles) {
				String fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
				File target = new File(savePath, fileName);
				
				target.mkdirs();
				
				multi.transferTo(target);
				
				AttachVO attachVO = new AttachVO();
				attachVO.setUploadPath(savePath);
				attachVO.setFileName(fileName);
				attachVO.setFileType(fileName.substring(fileName.lastIndexOf(".") + 1).toUpperCase());
				attachList.add(attachVO);
			}
		}
		return attachList;
	}
}
