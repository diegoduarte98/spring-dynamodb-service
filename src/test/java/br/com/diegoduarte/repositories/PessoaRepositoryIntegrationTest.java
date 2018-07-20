package br.com.diegoduarte.repositories;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

import br.com.diegoduarte.configuration.DynamoDBConfig;
import br.com.diegoduarte.model.Pessoa;
import br.com.diegoduarte.utils.DynamonDBUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { PropertyPlaceholderAutoConfiguration.class, DynamoDBConfig.class })
@TestPropertySource("classpath:application.properties")
public class PessoaRepositoryIntegrationTest {
	
	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;
	
	@Autowired
	private PessoaRepository repository;

	@Before
	public void init() throws Exception {
		AmazonDynamoDB client = new AmazonDynamoDBClient(new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey));
		DynamoDB dynamoDB = new DynamoDB(client);
		DynamonDBUtils.dynamoDB = dynamoDB;
		DynamonDBUtils.createTable("Pessoa", 1, 1, "S", "5");
	}

	@Test
	public void sampleTestCase() {
		Pessoa pessoa = Pessoa.builder().nome("Diego").cpf("888.888.888-88").sexo("Masculino").build(); 
		repository.save(pessoa);

		List<Pessoa> result = repository.findByNome("Diego");
		Assert.assertThat(result.size(), is(1));
		Assert.assertThat(result, hasItem(pessoa));
	}
}
