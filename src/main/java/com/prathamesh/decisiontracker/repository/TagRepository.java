package com.prathamesh.decisiontracker.repository;

import com.prathamesh.decisiontracker.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.prathamesh.decisiontracker.dto.TagScoreDTO;

import java.util.List;
import java.util.Objects;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Boolean existsByTagName(String tagName);

    @Query(value = "SELECT t.*, COUNT(dt.decision_id) FROM tags t INNER JOIN decision_tags dt ON t.tag_id = dt.tag_id GROUP BY t.tag_id HAVING COUNT(dt.decision_id) >= :minimum_usage_count", nativeQuery = true)
    List<Object[]> getTopTags(@Param("minimum_usage_count") Integer minimumUsageCount);

//    @Query(value = "SELECT t.tag_name, t.tag_id, AVG(d.score) from decision_tags dt inner join decision d on dt.decision_id = d.decision_id inner join tags t on dt.tag_id = t.tag_id group by (t.tag_name, t.tag_id)", nativeQuery = true)
//    List<Object[]> getAverageScorePerTag();

    @Query("SELECT new com.prathamesh.decisiontracker.dto.TagScoreDTO(t.tagId, t.tagName, AVG(d.score)) from Decision d JOIN d.tags t GROUP BY t.tagId, t.tagName")
    List<TagScoreDTO> getAvgScorePerTag();
}
