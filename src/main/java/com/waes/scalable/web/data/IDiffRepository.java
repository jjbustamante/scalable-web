package com.waes.scalable.web.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IDiffRepository extends CrudRepository<Diff, Long> {
    
    @Modifying
    @Query("delete from DiffResultDetails r where r.diffResult.id=?1")
    int clearDetails(long resultId);
    
}
