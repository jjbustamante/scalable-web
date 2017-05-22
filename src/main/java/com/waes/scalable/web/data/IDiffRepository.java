package com.waes.scalable.web.data;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * The Interface IDiffRepository.
 * This class represents the Data Access Layer for the application.  
 */
public interface IDiffRepository extends CrudRepository<Diff, Long> {
    
    /**
     * Clear details.
     *
     * @param resultId the result id
     * @return the int
     */
    @Modifying
    @Query("delete from DiffResultDetails r where r.diffResult.id=?1")
    int clearDetails(long resultId);
    
}
