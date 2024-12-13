package vttp.batch5.ssf.noticeboard.services;

import vttp.batch5.ssf.noticeboard.models.Notice;

public class NoticeService {

	// TODO: Task 3
	// You can change the signature of this method by adding any number of parameters
	// and return any type
	public String postToNoticeServer(NoticeResp nr) {
	
      String id = UUID.randomUUID().toString().substring(0, 8);
      NoticeResp.setId(id);

      NoticeRepo.insertNotices(Notice);

      return id;
   }




	}

}
