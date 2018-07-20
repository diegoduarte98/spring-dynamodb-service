package br.com.diegoduarte.utils;

import java.util.ArrayList;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class DynamonDBUtils {
	
	public static DynamoDB dynamoDB;
	
	public static void createTable(String tableName, long readCapacityUnits, long writeCapacityUnits, String partitionKeyName, String partitionKeyType) {
		createTable(tableName, readCapacityUnits, writeCapacityUnits, partitionKeyName, partitionKeyType, null, null);
	}

	private static void createTable(String tableName, long readCapacityUnits, long writeCapacityUnits, String partitionKeyName,
			String partitionKeyType, String sortKeyName, String sortKeyType) {

		try {

			ArrayList<KeySchemaElement> keySchema = new ArrayList<KeySchemaElement>();
			keySchema.add(new KeySchemaElement().withAttributeName(partitionKeyName).withKeyType(KeyType.HASH)); // Partition

			ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
			attributeDefinitions.add(
					new AttributeDefinition().withAttributeName(partitionKeyName).withAttributeType(partitionKeyType));

			if (sortKeyName != null) {
				keySchema.add(new KeySchemaElement().withAttributeName(sortKeyName).withKeyType(KeyType.RANGE)); // Sort
				attributeDefinitions
						.add(new AttributeDefinition().withAttributeName(sortKeyName).withAttributeType(sortKeyType));
			}

			CreateTableRequest request = new CreateTableRequest().withTableName(tableName).withKeySchema(keySchema)
					.withProvisionedThroughput(new ProvisionedThroughput().withReadCapacityUnits(readCapacityUnits)
							.withWriteCapacityUnits(writeCapacityUnits));

			request.setAttributeDefinitions(attributeDefinitions);

			System.out.println("Issuing CreateTable request for " + tableName);
			Table table = dynamoDB.createTable(request);
			System.out.println("Waiting for " + tableName + " to be created...this may take a while...");
			table.waitForActive();

		} catch (Exception e) {
			System.err.println("CreateTable request failed for " + tableName);
			System.err.println(e.getMessage());
		}
	}
}
