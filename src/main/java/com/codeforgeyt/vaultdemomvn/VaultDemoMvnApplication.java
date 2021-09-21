package com.codeforgeyt.vaultdemomvn;

import com.codeforgeyt.vaultdemomvn.configuration.MyKeyValues;
import com.codeforgeyt.vaultdemomvn.configuration.VaultConfiguration;
import com.codeforgeyt.vaultdemomvn.configuration.VaultProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

@SpringBootApplication
@EnableConfigurationProperties({MyKeyValues.class, VaultProperties.class})
public class VaultDemoMvnApplication implements CommandLineRunner {
	private final MyKeyValues myKeyValues;

	@Autowired
	private VaultConfiguration vaultConfiguration;

	public VaultDemoMvnApplication(MyKeyValues myKeyValues) {
		this.myKeyValues = myKeyValues;
	}


	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(VaultDemoMvnApplication.class);
		ConfigurableEnvironment environment = new StandardEnvironment();
		environment.setActiveProfiles("prod");
		application.setEnvironment(environment);
		application.run(args);
	}



	@Override
	public void run(String... args) {

		Logger logger = LoggerFactory.getLogger(VaultDemoMvnApplication.class);

		logger.info("-----------------------------------");
		logger.info("Key Values retrieved");
		logger.info("        example.username is {}", myKeyValues.getLogin());
		logger.info("        example.password is {}", myKeyValues.getPassword());
		logger.info("------------------------------------");
		vaultConfiguration.show();
		ApiStoreGateway apiStoreGateway = ApiStoreGateway.get();
		System.out.println(apiStoreGateway.getKeyValuePair("vault-demo",""));
	}
}
