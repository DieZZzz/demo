package com.epam.demo.service;

import com.epam.demo.model.Commit;

import java.util.Collection;

public interface CommitService {

    Commit save(Commit commit);

    void delete(String id);

    Commit findOne(String id);

    Collection<Commit> findAll();
}
