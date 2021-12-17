# DynamoDB Annotations, Load, and Save - ATA Discussion CLI

**Branch name:** dynamodbannotationsloadsave-classroom

**AWS account:** (account number for your <Alias>ATAUnit3 account -- 
[find on Conduit](https://access.amazon.com/aws/accounts))
 
**role:** IibsAdminAccess-DO-NOT-DELETE

**RDE workflows:**
- `dynamodbannotationsloadsave-classroom-cli`
- `dynamodbannotationsloadsave-classroom-tests`

We've started a simple command-line interface (CLI) to interact with the Discussion app tables from the previous
lesson. We'll be interacting with the tables:
* `DynamoDbAnnotationsLoadSave-Members`: containing members of the discussion app
* `DynamoDbAnnotationsLoadSave-Messages`: the actual messages that members have posted
* `DynamoDbAnnotationsLoadSave-Topics`: the list of topics up for discussion

## Phase 0: Start `ada` and deploy your DynamoDB tables

1. Make sure `ada` is running with the credentials specified at the top of this README
   (the AWS account that ATA set up for you for this unit). [Instructions on `ada` and `aws`
   command line interfaces](https://w.amazon.com/bin/view/Amazon_Technical_Academy/Internal/HowTos/Get_AWS_Credentials_On_Laptop/).
1. Create the tables we'll be using for this activity by running the following `aws` CLI commands:
   ```none
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbannotationsloadsave-memberstable --template-body file://cloudformation/dynamodbannotationsloadsave/classroom/discussion_cli_table_members.yaml --capabilities CAPABILITY_IAM
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbannotationsloadsave-messagestable --template-body file://cloudformation/dynamodbannotationsloadsave/classroom/discussion_cli_table_messages.yaml --capabilities CAPABILITY_IAM
   aws cloudformation create-stack --region us-west-2 --stack-name dynamodbannotationsloadsave-topicstable --template-body file://cloudformation/dynamodbannotationsloadsave/classroom/discussion_cli_table_topics.yaml --capabilities CAPABILITY_IAM
   ```
1. Make sure the `aws` command runs without error.
1. Log into your AWS account on Conduit and verify that the tables exist and have
   sample data.
   
These tables are used by our Discussion CLI. The Discussion CLI lets members create discussion topics, look up
what topics already exist, read messages on those topics, and send messages to those topics.

We have provided a [class diagram of the Discussion CLI service](https://plantuml.corp.amazon.com/plantuml/form/encoded.html#encoded=dPPTRvim58Qlnwz0wWtHbFw0QehAA4kHyb65gDjTvP9Ji0PsPfjkqxJ_FcD3mDWaod9b_3w7r-S3A_TS82RgglIo4d7kFs2UrPnZIg8Ixsf2WFat9SuBaJFuxO8koAb00eOCaBeoaUqH619oFS3yFzw7rNQPR3nV_fwJ-ElBVlkKHAbwZXx3pJBMEtfZ5uVxuKOVyRAEqpHSnga5TeeIuc_9tllhjIcieNerCc_EC_Th3eoW2lmxFnKCaro5pdtCmqpWzsR_CwKb8D8Atn6hq1F5HF3m7U4IlPODb101EJ3ff-tsz8YptWs8jdDP7O1d31zLkUnJiAoGnpWCpc5Oh85pb8Fj91frux0Jk08v8jLnBA1Q59IDTojj456P816zEYhi0wAZsdxqSn1Q3Amgp-HAQmsOCP0ZrRALRbId3qWZSSDG9zszIEc1Ae0CLa-pRg6oKaTJZpoGiwjNaf1DRS0LvkAJsbmuY-sqLiagcN-XRDzLFx3xO5gqGOk-e561IAwJU4JaKBg-rwXFr6A0s53hCit6lx2OT5lH79D9zHd3pw49dtOveKv6bX_O6yfWuZAnJw1BNwZz7VoevM07N5rFTuuxQsPCh-jUdCyNPfn9sRfwhmEoU-Y2h2QUXymcEjX14ntgi8iEUQgDvz0JuvZPszl5-JvwZY6-AcWqr9TZXqD-cJQc_Z8--HbS4Nf5p7zLtCofQUNfzdPrem6wozPnIBSyJlz4zRUdTm_a8F-X_0C0).
The classes you can expect to interact with today are highlighted in green.

**GOAL:** `DynamoDbAnnotationsLoadSave-Members`, `DynamoDbAnnotationsLoadSave-Messages` and
`DynamoDbAnnotationsLoadSave-Topics` tables are created in your AWS Account, including sample data.

Phase 0 is complete when:
* `DynamoDbAnnotationsLoadSave-Members`, `DynamoDbAnnotationsLoadSave-Messages`, and `DynamoDbAnnotationsLoadSave-Topics`
  tables exist in your Unit 3 AWS account with some sample data

## Phases 1+

Follow the instructions in the GILA worksheet provided by the instructors in class.
