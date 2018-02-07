package com.dromree.thermopi.dbaccess.mongodb;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Configuration for MongoDB
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.dromree.thermopi.dbaccess.mongodb.repositories")
public class MongoDBConfig extends AbstractMongoConfiguration {

    @Value("${mongo.server:localhost}")
    private String server;
    @Value("${mongo.db.name:ThermoPi}")
    private String dbName;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(server);
    }

    @Override
    @Primary
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate() throws Exception {
        return super.mongoTemplate();
    }
}
