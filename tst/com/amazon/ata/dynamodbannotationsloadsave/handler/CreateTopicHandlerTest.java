package com.amazon.ata.dynamodbannotationsloadsave.handler;

import com.amazon.ata.dynamodbannotationsloadsave.cli.DiscussionCliState;
import com.amazon.ata.dynamodbannotationsloadsave.dynamodb.Topic;
import com.amazon.ata.dynamodbannotationsloadsave.dynamodb.TopicDao;
import com.amazon.ata.input.console.ATAUserHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CreateTopicHandlerTest {
    private CreateTopicHandler handler;
    private DiscussionCliState state;

    @Mock
    private TopicDao topicDao;
    @Mock
    private ATAUserHandler userHandler;

    @BeforeEach
    private void setup() {
        initMocks(this);
        handler = new CreateTopicHandler(topicDao, userHandler);
        state = getState();
    }

    @Test
    void handleRequest_givenTopicAndDescription_createsTopic() {
        // GIVEN
        // Topic data
        String topicName = "name";
        String topicDescription = "description";
        // Topic to create
        Topic newTopic = new Topic(topicName, topicDescription);
        // User handler returns name then description
        when(userHandler.getString("", "New topic name: ")).thenReturn(topicName);
        when(userHandler.getString("", "New topic description: ")).thenReturn(topicDescription);
        // DAO allows creating topic
        when(topicDao.createTopic(any(Topic.class))).thenReturn(newTopic);

        // WHEN
        String result = handler.handleRequest(state);

        // THEN
        // DAO allows creating topic
        verify(topicDao, times(1)).createTopic(newTopic);
        // Topic name is displayed
        assertTrue(result.contains(newTopic.getName()));
    }

    private DiscussionCliState getState() {
        return new DiscussionCliState();
    }
}
