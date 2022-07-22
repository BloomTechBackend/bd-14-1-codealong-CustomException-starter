package com.amazon.ata.dynamodbannotationsloadsave.dynamodb;

import com.amazon.ata.aws.dynamodb.DynamoDbClientProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import java.util.Arrays;

public class CreateNewTable {
    public static void main(String[] args) {
        DynamoDB dynamoDB = new DynamoDB(DynamoDbClientProvider.getDynamoDBClient(Regions.US_WEST_1));

        String tableName = "Student";
        try {
            System.out.println("creating table");
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                    new ProvisionedThroughput(10L, 10L)
            );
            table.waitForActive();
        }catch(Exception e){
            System.err.println("unable to create");
            System.err.println(e.getMessage());
        }
    }
}
