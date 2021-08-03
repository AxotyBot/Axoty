package dev.redcodes.axoty.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AxolotlFact {

	String fact;

	public AxolotlFact() {

		while (this.fact == null) {

			try {
				URL api = new URL("https://axoltlapi.herokuapp.com/");

				URLConnection connection = api.openConnection();

				connection.connect();

				JsonElement element = JsonParser
						.parseReader(new InputStreamReader((InputStream) connection.getContent()));
				JsonObject rootObj = element.getAsJsonObject();
				this.fact = rootObj.get("facts").getAsString();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public String getFact() {
		return this.fact;
	}

}
