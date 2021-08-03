package dev.redcodes.axoty.data.users;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import dev.redcodes.axoty.data.connection.MongoDBHandler;
import net.dv8tion.jda.api.entities.User;

public class AxotyUser {

	User user;
	int imagesRequested;
	int factsRequested;
	int memesRequested;
	int videosRequested;

	public AxotyUser(User user) {
		this.user = user;

		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");

		Document doc = collection.find(Filters.eq("_id", this.user.getIdLong())).first();

		if (doc != null) {

			this.imagesRequested = doc.getInteger("images");
			this.factsRequested = doc.getInteger("facts");
			this.memesRequested = doc.getInteger("memes");
			this.videosRequested = doc.getInteger("videos");

		} else {
			doc = new Document("_id", this.user.getIdLong()).append("images", 0).append("facts", 0).append("memes", 0)
					.append("videos", 0);
			collection.insertOne(doc);
		}

	}

	public int getMemesRequested() {
		return memesRequested;
	}

	public void setMemesRequested(int memesRequested) {
		this.memesRequested = memesRequested;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.user.getIdLong()), Updates.set("memes", this.memesRequested));
	}

	public void addMemesRequested() {
		this.memesRequested++;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.user.getIdLong()), Updates.set("memes", this.memesRequested));
	}

	public int getVideosRequested() {
		return videosRequested;
	}

	public void setVideosRequested(int videosRequested) {
		this.videosRequested = videosRequested;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.user.getIdLong()), Updates.set("videos", this.videosRequested));
	}

	public void addVideosRequested() {
		this.videosRequested++;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.user.getIdLong()), Updates.set("videos", this.videosRequested));
	}

	public int getImagesRequested() {
		return imagesRequested;
	}

	public void setImagesRequested(int imagesRequested) {
		this.imagesRequested = imagesRequested;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.user.getIdLong()), Updates.set("images", this.imagesRequested));
	}

	public void addImagesRequested() {
		this.imagesRequested++;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.user.getIdLong()), Updates.set("images", this.imagesRequested));
	}

	public int getFactsRequested() {
		return factsRequested;
	}

	public void setFactsRequested(int factsRequested) {
		this.factsRequested = factsRequested;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.user.getIdLong()), Updates.set("facts", this.factsRequested));
	}

	public void addFactsRequested() {
		this.factsRequested++;
		MongoCollection<Document> collection = MongoDBHandler.getDatabase().getCollection("users");
		collection.updateOne(Filters.eq("_id", this.user.getIdLong()), Updates.set("facts", this.factsRequested));
	}

	public User getUser() {
		return user;
	}

}
