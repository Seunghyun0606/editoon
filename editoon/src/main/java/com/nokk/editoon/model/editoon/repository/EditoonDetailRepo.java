package com.nokk.editoon.model.editoon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.nokk.editoon.model.editoon.entity.EditoonEntity;

public interface EditoonDetailRepo extends MongoRepository<EditoonEntity, Integer>{
	@Query(value = "{'_id': ?0}", fields = "{editoonDetails: {'image' : false}}")
	EditoonEntity findEditoonDetailList(int accountNo);

	@Query(value = "{'_id': ?0}", fields = "{editoonDetails : {$elemMatch : {_id : ?1}}}")
	EditoonEntity findEditoonDetailContent(int accountNo, int editoonNo);
}
