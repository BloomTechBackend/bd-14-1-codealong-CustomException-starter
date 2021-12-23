# bd-dynamodb-annotations-load-and-save-solution

[Group Document (GILA)](https://docs.google.com/document/d/1ZDDyZXRjUus2LjCBOy7F3xHJi4BSmlnLg5kZMqAW3D8/edit?usp=sharing)

### Goal 

The goal of this GP is to help learners implement DynamoDB annotations to their project.


### Kick off

Possible kick off topics:
- Explain that DynamoDB leverages annotations to map class fields to DynamoDB columns. 
- You can share personal work experiences where converting database data to Java objects is a core feature in just about any project. 
- Address code generation as a tool for these kinds of POJO classes, but we need to understand the patterns before this can be realized

### Intro to the project

Explain that this project is a cli for message boards. You can run the solution code (make sure you've already set up the tables in DynamoDB) and show the learners what the end product will look like. It's also fun to see things update remotely, so after you create a new message or a new topic, you can go to the DynamoDB website and show them that what you created is actually there.

With the code downloaded, all the learners to run the following commands (these commands are in the README):

```
aws cloudformation create-stack --region us-west-2 --stack-name dynamodbannotationsloadsave-memberstable --template-body file://cloudformation/dynamodbannotationsloadsave/classroom/discussion_cli_table_members.yaml --capabilities CAPABILITY_IAM
aws cloudformation create-stack --region us-west-2 --stack-name dynamodbannotationsloadsave-messagestable --template-body file://cloudformation/dynamodbannotationsloadsave/classroom/discussion_cli_table_messages.yaml --capabilities CAPABILITY_IAM
aws cloudformation create-stack --region us-west-2 --stack-name dynamodbannotationsloadsave-topicstable --template-body file://cloudformation/dynamodbannotationsloadsave/classroom/discussion_cli_table_topics.yaml --capabilities CAPABILITY_IAM
```

### Running the project

With this in place, run the application and try to log in (the app shouldn't fully work yet, but you should see some things happen before it crashes).

Here are some questions you can address with the class:
1. Log in and provide a username. If you haven’t logged in before, what happens?  
   - Crash
3. At the moment, we are unable to load into the CLI. Attempting to retrieve topics fails. What is the type of exception thrown by the CLI? 
   - DynamoDBMappingException
4. What is the error message thrown by the exception? 
   - class Topic not annotated with @DynamoDBTable 
6. What class in our service (a POJO in the dynamodbannotationsloadsave.classroom.dynamodb package) is referenced in the exception message? 
   - Topic
8. Which of the tables you deployed as preparation for this lesson contain items of this class? 
   - show in AWS
10. What annotation does the exception message say is missing on the class?
    - @DynamoDBTable

These are all great things to note. The error is quite clear at what the issue is if we quickly study it. (this is a good example of good excpetion handling)

### Adding Annotations to Topic

Open the `Topic` class and start going through the needed annotations.
- `@DynamoDBTable(tableName = "DynamoDbAnnotationsLoadSave-Topics")` above the class
- `@DynamoDBHashKey(attributeName = "name")` above getName
- `@DynamoDBAttribute(attributeName = "description")` above getDescription
- `@DynamoDBAttribute(attributeName = "isArchived")` above isArchived
- OPTIONAL: `@DynamoDBTyped(DynamoDBMapperFieldModel.DynamoDBAttributeType.BOOL)` above isArchived
  - By default, DynamoDBMapper will store a Boolean from Java as a Number, 0 for false, 1 for true. This DynamoDBTyped annotation forces DynamoDBMapper to use the DynamoDB BOOL type. We're also using DynamoDBTyped for the same purpose in Member for the isActive field.

Run the app and see that you're now passed the initial error. 

### Fix Topics

Try to create a topic (it should crash).

You can also run the tests and notice that one of the failing tests directly relates to creating topics. Note that you were able to run the test faster and with less effort than going through the app. This will become relevent in the next section when we have them write tests.

Go through the errors and go through how to find culprit class that needs fixing (`TopicDao.createTopic(Topic topic)`). Once it's fixed, run the tests again and ensure it now passes. You can run the app too and see the change there as well.

### Adding Annotations to TopicMessage

Similarly to `Topic`, `TopicMessage` is missing annotations as well. This may be a good time to split into groups or even have them do this part individually before you walk through the answer.

See the solution code for what needs to be added.

### Adding Tests for Topic Message

Have them open the `TopicMessageDaoTest` and see if they can add any tests themselves (they can use the `MemberDaoTest` as a reference.









