package com.epam.demo.service;

import com.epam.demo.model.Commit;
import com.epam.demo.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class CommitServiceImpl implements CommitService {

    private final CommitRepository commitRepository;

    @Autowired
    public CommitServiceImpl(CommitRepository commitRepository) {
        this.commitRepository = commitRepository;
    }

    @Override
    public Commit save(Commit commit) {
        return commitRepository.save(commit);
    }

    @Override
    public void delete(String id) {
        commitRepository.deleteById(id);
    }

    @Override
    public Commit findOne(String id) {
        return commitRepository.findById(id).orElse(null);
    }

    @Override
    public Collection<Commit> findAll() {
        // as a workaround for https://github.com/spring-projects/spring-data-elasticsearch/pull/175
        AggregatedPageImpl<Commit> commits = (AggregatedPageImpl<Commit>) commitRepository.findAll();
        return commits.getContent() == null ? Collections.emptyList() : commits.getContent();
    }
}
