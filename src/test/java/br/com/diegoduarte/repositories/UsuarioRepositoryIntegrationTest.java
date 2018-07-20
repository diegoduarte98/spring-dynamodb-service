package br.com.diegoduarte.repositories;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;

import br.com.diegoduarte.configuration.DynamoDBConfig;
import br.com.diegoduarte.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class })
public class UsuarioRepositoryIntegrationTest {

	private static final long CAPACITY = 5L;

	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private UserRepository repository;

	@Before
	public void init() throws Exception {

		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

//		CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Usuario.class);
//		tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
//		amazonDynamoDB.createTable(tableRequest);
		
		DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

		/*TableCollection<ListTablesResult> tables = dynamoDB.listTables();
        Iterator<Table> iterator = tables.iterator();

        System.out.println("Listing table names");

        while (iterator.hasNext()) {
            Table table = iterator.next();
            System.out.println(table.getTableName());
        }*/
        
//        TableDescription tableDescription = dynamoDB.getTable("Usuario").describe();
//        System.out.format(
//            "Name: %s:\n" + "Status: %s \n" + "Provisioned Throughput (read capacity units/sec): %d \n"
//                + "Provisioned Throughput (write capacity units/sec): %d \n",
//            tableDescription.getTableName(), tableDescription.getTableStatus(),
//            tableDescription.getProvisionedThroughput().getReadCapacityUnits(),
//            tableDescription.getProvisionedThroughput().getWriteCapacityUnits());
		

//		dynamoDBMapper.batchDelete((List<Usuario>) repository.findAll());

//		// Delete User table in case it exists
//		amazonDynamoDB.listTables().getTableNames().stream().filter(tableName -> tableName.equals(Usuario.TABLE_NAME))
//				.forEach(tableName -> {
//					System.out.println(tableName);
//				});
//
//		// Create User table
//		amazonDynamoDB.createTable(new DynamoDBMapper(amazonDynamoDB).generateCreateTableRequest(Usuario.class)
//				.withProvisionedThroughput(new ProvisionedThroughput(CAPACITY, CAPACITY)));
		
//		ScanRequest scanRequest = new ScanRequest().withTableName("Usuario");
		
		 ScanRequest scanRequest = new ScanRequest()
			        .withTableName("Usuario");
		
		System.out.println(" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
		ScanResult result = amazonDynamoDB.scan(scanRequest);
		for (Map<String, AttributeValue> item : result.getItems()){
		    System.out.println(item);
		}	
		System.out.println(" ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
	}

	@Test
	public void sampleTestCase() {
//		Usuario dave = new Usuario("Diego", "Duarte");
//		repository.save(dave);
//
//		Usuario carter = new Usuario("Carter", "Beauford");
//		repository.save(carter);
//
//		List<Usuario> result = repository.findByLastName("Matthews");
//		Assert.assertThat(result.size(), is(1));
//		Assert.assertThat(result, hasItem(dave));*/
	}
}
