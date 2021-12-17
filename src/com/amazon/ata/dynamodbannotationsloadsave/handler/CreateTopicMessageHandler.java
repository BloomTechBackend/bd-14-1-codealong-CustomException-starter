package com.amazon.ata.dynamodbannotationsloadsave.handler;

import com.amazon.ata.dynamodbannotationsloadsave.cli.DiscussionCliOperation;
import com.amazon.ata.dynamodbannotationsloadsave.cli.DiscussionCliState;
import com.amazon.ata.dynamodbannotationsloadsave.dynamodb.TopicMessage;
import com.amazon.ata.dynamodbannotationsloadsave.dynamodb.TopicMessageDao;
import com.amazon.ata.input.console.ATAUserHandler;

/**
 * Handler for the CREATE_TOPIC operation.
 */
public class CreateTopicMessageHandler implements DiscussionCliOperationHandler {
    private ATAUserHandler userHandler;
    private TopicMessageDao topicMessageDao;

    /**
     * Constructs handler with its dependencies.
     * @param userHandler the ATAUserHandler, for user input
     */
    public CreateTopicMessageHandler(ATAUserHandler userHandler, TopicMessageDao topicMessageDao) {
        this.userHandler = userHandler;
        this.topicMessageDao = topicMessageDao;
    }

    @Override
    public String handleRequest(DiscussionCliState state) {
        if (null == state.getCurrentMember()) {
            throw new IllegalStateException(
                "Encountered request to create topic message but there is no current member. Exiting"
            );
        }
        if (null == state.getCurrentTopic()) {
            state.setNextOperation(DiscussionCliOperation.VIEW_TOPICS);
            return "You must select a topic first.";
        }

        String messageContent = userHandler.getString("Message:");
        TopicMessage newTopicMessage = new TopicMessage();
        newTopicMessage.setAuthor(state.getCurrentMember().getUsername());
        newTopicMessage.setTopicName(state.getCurrentTopic().getName());
        newTopicMessage.setMessageContent(messageContent);

        topicMessageDao.saveTopicMessage(newTopicMessage);
        state.setNextOperation(DiscussionCliOperation.VIEW_TOPIC_MESSAGES);


        return String.format("Discussion topic message [%s] sent!", newTopicMessage);
    }
}
