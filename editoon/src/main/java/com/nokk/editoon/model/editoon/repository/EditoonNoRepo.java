package com.nokk.editoon.model.editoon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.nokk.editoon.model.editoon.entity.EditoonInfoEntity;

public interface EditoonNoRepo extends MongoRepository<EditoonInfoEntity, Integer>{
	@Query(value = "{'_id' : ?0}")
	EditoonInfoEntity findNoteNo(int accountNo);
}
