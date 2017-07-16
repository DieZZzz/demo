package com.epam.demo.api.v1;

import com.epam.demo.model.Commit;
import com.epam.demo.service.CommitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/commits")
@Api(value = "Commits REST Api",description = "Operations with commits")
public class CommitController {

    private final CommitService commitService;

    @Autowired
    public CommitController(CommitService commitService) {
        this.commitService = commitService;
    }

    @ApiOperation(value = "Return all commits", produces = "application/json")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Collection<Commit> getCommits() {
        return commitService.findAll();
    }

    @ApiOperation(value = "Finds commit by id", produces = "application/json")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Commit getById(@ApiParam(value = "Commit id (e.g. SHA)", required = true) @PathVariable String id) {
        return commitService.findOne(id);
    }

    @ApiOperation(value = "Creates new commit", produces = "application/json")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Commit> create(@ApiParam(value = "Commit JSON", required = true) @RequestBody Commit commit) {
        Commit result = commitService.save(commit);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(result.getId())
                .toUri());
        return new ResponseEntity<>(result, headers, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Removes a specified commit", produces = "application/json")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteCommit(@ApiParam(value = "Commit id (e.g. SHA)", required = true) @PathVariable String id) {
        commitService.delete(id);
    }
}
