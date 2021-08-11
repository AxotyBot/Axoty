package dev.redcodes.axoty.api;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

import dev.redcodes.axoty.data.connection.MongoDBHandler;

public class AxolotlVideo {

	URL url;
	URL sourceUrl;
	File file;
	String suggester;

	public AxolotlVideo() {

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("videos");
		FindIterable<Document> iterable = collection.find();
		Iterator<Document> iterator = iterable.iterator();

		List<ObjectId> data = new ArrayList<ObjectId>();

		while (iterator.hasNext()) {
			Document doc = iterator.next();
			data.add(doc.getObjectId("_id"));
		}

		int randomNum = new Random().nextInt(data.size());

		Document doc = collection.find(Filters.eq("_id", data.get(randomNum))).first();

		try {
			this.url = new URL(doc.getString("url"));
			this.sourceUrl = new URL(doc.getString("sourceUrl"));
			this.suggester = doc.getString("suggester");
			this.file = new File("resources/" + doc.getObjectId("_id").toString());

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}

	}

	public URL getUrl() {
		return this.url;
	}
	
	public URL getSourceUrl() {
		return this.sourceUrl;
	}

	public String getSuggester() {
		return this.suggester;
	}

}
