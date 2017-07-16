package com.epam.demo.repository;

import com.epam.demo.model.Commit;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CommitRepository extends ElasticsearchRepository<Commit, String> {
}
