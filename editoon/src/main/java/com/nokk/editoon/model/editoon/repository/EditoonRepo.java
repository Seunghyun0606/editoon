package com.nokk.editoon.model.editoon.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.nokk.editoon.model.editoon.entity.EditoonEntity;
import com.nokk.editoon.model.editoon.entity.EditoonInfoEntity;

@Repository
public class EditoonRepo {

	@Autowired
	private MongoTemplate mongoTemplate;
	
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
	
}
