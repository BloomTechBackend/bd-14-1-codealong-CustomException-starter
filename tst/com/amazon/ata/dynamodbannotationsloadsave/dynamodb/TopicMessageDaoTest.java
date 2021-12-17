package com.amazon.ata.dynamodbannotationsloadsave.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.MockitoAnnotations.initMocks;

public class TopicMessageDaoTest {
    private TopicMessageDao topicMessageDao;

    @Mock
    private DynamoDBMapper mapper;

    @BeforeEach
    private void setup() {
        initMocks(this);
        topicMessageDao = new TopicMessageDao(mapper);
    }

    @Test
    void createTopicMessage_withValidTopicMessage_isSavedAndReturned() {
        // GIVEN
        // TopicMessage to be saved
        TopicMessage newTopicMessage = new TopicMessage();
        newTopicMessage.setAuthor("author");
        newTopicMessage.setTopicName("topicName");
        newTopicMessage.setMessageContent("message");
        // DynamoDB accepts it
        doNothing().when(mapper).save(newTopicMessage);

        // WHEN
        TopicMessage result = topicMessageDao.saveTopicMessage(newTopicMessage);

        // THEN
        assertEquals(newTopicMessage, result);
    }
}
