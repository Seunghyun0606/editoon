package com.nokk.editoon.model.editoon.repository;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.nokk.editoon.model.editoon.dto.SaveEditoonDetailDTO;
import com.nokk.editoon.model.editoon.entity.EditoonEntity;
import com.nokk.editoon.model.editoon.entity.EditoonInfoEntity;

@Repository
public class EditoonRepo {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private EditoonNoRepo editoonNoRepo;
	
	public void createEditoon(int accountNo) {
		EditoonEntity editoonEntity = new EditoonEntity(accountNo, null);
    	mongoTemplate.insert(editoonEntity);
    	
    	EditoonInfoEntity editoonInfoEntity = new EditoonInfoEntity(accountNo, 1);
    	mongoTemplate.insert(editoonInfoEntity);
    }
	
	public void removeEditoon(int accountNo) {
		EditoonEntity editoonEntity = new EditoonEntity(accountNo, null);
    	mongoTemplate.remove(editoonEntity);
    	
    	EditoonInfoEntity editoonInfoEntity = new EditoonInfoEntity(accountNo, 0);
    	mongoTemplate.remove(editoonInfoEntity);
    }
	
	public int insertEditoonDetail(SaveEditoonDetailDTO saveEditoonDetailDTO, String thumbNailName, List<String> imageNameList, String createDate) {
    	int no = editoonNoRepo.findEditoonNo(saveEditoonDetailDTO.getNo()).getNo();
    	
    	Query query = new Query().addCriteria(Criteria.where( "_id" ).is(saveEditoonDetailDTO.getNo()));
        Update update = new Update();
        update.inc("no", 1);  //증감시킬 숫자
        mongoTemplate.updateFirst(query, update, EditoonInfoEntity.class);
    	
    	query = new Query(Criteria.where("_id").is(saveEditoonDetailDTO.getNo()));
    	update = new Update();
    	Document item = new Document();
    	item.put("_id", no);
    	item.put("subject", saveEditoonDetailDTO.getSubject());
    	item.put("thumbnail", thumbNailName);
    	item.put("image", imageNameList);
    	item.put("createDate", createDate);
    	update.push("editoonDetails").each(item);
    	mongoTemplate.updateFirst(query, update, EditoonEntity.class);
    	return no;
    }
}
