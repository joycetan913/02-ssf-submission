package vttp.batch5.ssf.noticeboard.repositories;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp.batch5.ssf.noticeboard.models.Notice;
import vttp.batch5.ssf.noticeboard.models.NoticeResp;

@Repository
public class NoticeRepository {

	// TODO: Task 4
	// You can change the signature of this method by adding any number of parameters
	// and return any type
	// 
	/*
	 * Write the redis-cli command that you use in this method in the comment. 
	 * For example if this method deletes a field from a hash, then write the following
	 * redis-cli command 
	 * 	hdel myhashmap a_key
	 *
	 *
	 */
	//public void insertNotices() {

	// }

	public static final String HASH_KEY_NAME = "Notice";

    @Autowired
    @Qualifier("notice")
    RedisTemplate<String, Object> redisTemplate;

	 //hgetall Notice
    public List<Notice> findAll() {
        List<Notice> payloads = redisTemplate
                    .opsForHash()
                    .values(HASH_KEY_NAME)
                    .stream()
                    .map(Notice.class::cast)
                    .collect(Collectors.toList());
            return payloads;
    }
	// hgetall id
	public Optional<Notice> getNoticeById(String id) {
		HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
		Map<String, Object> notice = hashOps.entries(id);
  
		if (notice.isEmpty())
		   return Optional.empty();
  
		Notice result = new Notice();
		result.setTitle(notice.get("title").toString());
		result.setPoster(notice.get("poster").toString());
		result.setPostDate(notice.new Date());
		result.setCategories(notice.get("categories").toString());
		result.setText(notice.get("text").toString());
  
		return Optional.of(result);
	 }

	public String getRandom(){
		String key1 = redisTemplate.opsForHash().randomKey(key);
        return key1;
	}

	// keys *
   public Set<String> getContactIds() {
      return redisTemplate.keys("*");
   }

	 public void insertNotices(Notice notice) {
	
	//hset abc123 title Christmas...
	//hset abc123 poster email
      HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
      Map<String, Object> values = new HashMap<>();
      values.put("title", Notice.getTitle());
      values.put("poster", Notice.getPoster());
      values.put("postDate", Notice.getPostDate());
	  values.put("categories", Notice.getCategories());
	  values.put("text", Notice.getText());
      hashOps.putAll(NoticeResp.getId(), values);
   }
	




}
