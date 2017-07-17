package com.epam.demo.api.v1;

import com.epam.demo.model.Commit;
import com.epam.demo.service.KafkaProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/produce")
@Api(value = "Kafka Commits producer Api", description = "Operations with kafka producer")
public class KafkaProducerController {

    @Value("${kafka.topic}")
    private String kafkaCommitsTopic;

    private final KafkaProducerService producerService;

    @Autowired
    public KafkaProducerController(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @ApiOperation(value = "Add new Commit to the topic", produces = "application/json")
    @RequestMapping(method = RequestMethod.POST)
    public void publish(@ApiParam(value = "Commit JSON", required = true) @RequestBody Commit message) {
        producerService.sendMessage(kafkaCommitsTopic, message);
    }
}
