package com.epam.demo.service;

import com.epam.demo.TestApplication;
import com.epam.demo.model.Commit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class CommitServiceTest {

    @Autowired
    private CommitService commitService;

    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Before
    public void setUp() {
        esTemplate.deleteIndex(Commit.class);
        esTemplate.createIndex(Commit.class);
        esTemplate.putMapping(Commit.class);
        esTemplate.refresh(Commit.class);
    }

    private Commit getTestCommit() {
        Commit commit = new Commit();
        commit.setId("test");
        commit.setMessage("test message");
        commit.setStatus("success");
        commit.setAuthorName("siarhei");
        return commit;
    }

    @Test
    public void testSave() {
        Commit commit = getTestCommit();

        Commit testCommit = commitService.save(commit);

        Assert.assertNotNull(testCommit);
        Assert.assertNotNull(testCommit.getId());
        Assert.assertEquals(commit.getId(), testCommit.getId());
        Assert.assertNotNull(testCommit.getMessage());
        Assert.assertEquals(commit.getMessage(), testCommit.getMessage());
    }

    @Test
    public void testFindOne() {
        Commit commit = getTestCommit();
        commitService.save(commit);

        Commit testCommit = commitService.findOne(commit.getId());

        Assert.assertNotNull(testCommit);
        Assert.assertNotNull(testCommit.getId());
        Assert.assertEquals(commit.getId(), testCommit.getId());
        Assert.assertNotNull(testCommit.getMessage());
        Assert.assertEquals(commit.getMessage(), testCommit.getMessage());
    }

    @Test
    public void testDelete() {
        Commit commit = getTestCommit();
        commitService.save(commit);
        commitService.delete(commit.getId());
        Commit testCommit = commitService.findOne(commit.getId());

        Assert.assertNull(testCommit);
    }

    @Test
    public void testFindAll() {
        Commit commit = getTestCommit();
        commitService.save(commit);

        int expectedAmount = 1;
        Collection<Commit> commits = commitService.findAll();

        Assert.assertEquals(expectedAmount, commits.size());
    }

    @Test
    public void testFindByStatus() {
        Commit commit = getTestCommit();
        commitService.save(commit);

        int expectedAmount = 1;
        Collection<Commit> commits = commitService.findByStatus("success");

        Assert.assertEquals(expectedAmount, commits.size());
    }

    @Test
    public void testFindByAuthorName() {
        Commit commit = getTestCommit();
        commitService.save(commit);

        int expectedAmount = 1;
        Collection<Commit> commits = commitService.findByAuthorName("siarhei");

        Assert.assertEquals(expectedAmount, commits.size());
    }
}
