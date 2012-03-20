package nu.pich.vucplace.client.batch;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class BatchPlace extends Place {

	private String url;
	private boolean deleteAll;

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDeleteAll(boolean deleteAll) {
		this.deleteAll = deleteAll;
	}

	public boolean hasUrl() {
		return getUrl() != null;
	}

	public String getUrl() {
		return url;
	}

	public boolean shouldDeleteAll() {
		return deleteAll;
	}

	public static class Tokenizer implements PlaceTokenizer<BatchPlace> {

		private static final String URL = "url";
		private static final String DELETE_ALL = "deleteall";

		@Override
		public BatchPlace getPlace(String token) {
			Map<String, String> urlParameters = getUrlParameters(token);
			String url = urlParameters.containsKey(URL) ? urlParameters.get(URL) : null;
			boolean deleteAll = urlParameters.containsKey(DELETE_ALL) ? Boolean.parseBoolean(urlParameters.get(DELETE_ALL)) : false;

			BatchPlace place = new BatchPlace();
			place.setUrl(url);
			place.setDeleteAll(deleteAll);
			return place;
		}

		public static Map<String, String> getUrlParameters(String url) {
			String[] params = url.split("&");
			Map<String, String> map = new HashMap<String, String>();
			for (String param : params) {
				String[] split = param.split("=");
				if (split.length == 2) {
					String name = split[0];
					String value = split[1];
					map.put(name, value);
				}
			}
			return map;
		}

		@Override
		public String getToken(BatchPlace place) {
			return buildTokenFromPlace(place);
		}

		private String buildTokenFromPlace(BatchPlace place) {
			Map<String, String> map = new TreeMap<String, String>();
			if (place.shouldDeleteAll()) {
				map.put(DELETE_ALL, "true");
			}
			if (place.hasUrl()) {
				map.put(URL, place.getUrl());
			}

			StringBuilder builder = new StringBuilder();
			for (String key : map.keySet()) {
				if (!builder.toString().equals("")) {
					builder.append("&");
				}
				builder.append(key);
				builder.append("=");
				builder.append(map.get(key));
			}

			return builder.toString();
		}

	}
}
