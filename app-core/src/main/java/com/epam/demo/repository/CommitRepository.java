package com.epam.demo.repository;

import com.epam.demo.model.Commit;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;


public interface CommitRepository extends ElasticsearchRepository<Commit, String> {

    Collection<Commit> findByStatus(String status);

    @Query("{\"bool\": {\"must\": [{\"match\": {\"author_name\": \"?0\"}}]}}")
    Collection<Commit> findByAuthorName(String authorName);
}
