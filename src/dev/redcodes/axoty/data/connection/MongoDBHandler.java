package dev.redcodes.axoty.data.connection;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import dev.redcodes.axoty.Axoty;
import dev.redcodes.axoty.token.DONOTOPEN;

public class MongoDBHandler {

	static MongoDatabase db;
	static MongoClient client;

	public static void connect() {

		MongoDatabase database;

		if (Axoty.Dev) {
			ConnectionString connString = new ConnectionString(DONOTOPEN.getDevMongoConnection());
			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString)
					.retryWrites(true).build();
			client = MongoClients.create(settings);
			database = client.getDatabase("Development");

		} else {
			ConnectionString connString = new ConnectionString(DONOTOPEN.getMongoConnection());
			MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connString)
					.retryWrites(true).build();
			client = MongoClients.create(settings);
			database = client.getDatabase("Public");
		}
		
		
		db = database;

		System.out.println("Connected to database");
	}

	public static void disconnect() {
		client.close();
	}

	public static MongoDatabase getDatabase() {
		return db;
	}
}