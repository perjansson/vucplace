package nu.pich.vucplace.client.guestbook;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import nu.pich.vucplace.shared.guestbook.domain.Day;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class GuestBookPlace extends Place {

	private Integer fromPost;
	private Integer toPost;
	private String message;
	private Day fromDay;
	private Day toDay;

	public GuestBookPlace(Integer fromPost, Integer toPost, String message, Day fromDay, Day toDay) {
		this.fromPost = fromPost;
		this.toPost = toPost;
		this.message = message;
		this.fromDay = fromDay;
		this.toDay = toDay;
	}

	private GuestBookPlace() {
	}

	public static Place emptyPlace() {
		return new GuestBookPlace();
	}

	public Integer getFromPost() {
		return fromPost;
	}

	public boolean hasFromPost() {
		return getFromPost() != null;
	}

	public Integer getToPost() {
		return toPost;
	}

	public boolean hasToPost() {
		return getToPost() != null;
	}

	public String getText() {
		return message;
	}

	public boolean hasText() {
		return getText() != null;
	}

	public boolean hasToDay() {
		return getToDay() != null;
	}

	public boolean hasFromDay() {
		return getFromDay() != null;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setFromPost(Integer fromPost) {
		this.fromPost = fromPost;
	}

	public void setToPost(Integer toPost) {
		this.toPost = toPost;
	}

	public Day getFromDay() {
		return fromDay;
	}

	public void setFromDay(Day fromDay) {
		this.fromDay = fromDay;
	}

	public Day getToDay() {
		return toDay;
	}

	public void setToDay(Day toDay) {
		this.toDay = toDay;
	}

	public static class Tokenizer implements PlaceTokenizer<GuestBookPlace> {

		private static final String FROM_POST = "frompost";
		private static final String TO_POST = "topost";
		private static final String TEXT = "text";
		private static final String FROM_DATE = "fromdate";
		private static final String TO_DATE = "todate";

		@Override
		public GuestBookPlace getPlace(String token) {
			Map<String, String> urlParameters = getUrlParameters(token);
			Integer fromPost = urlParameters.containsKey(FROM_POST) ? Integer.parseInt(urlParameters.get(FROM_POST)) : null;
			Integer toPost = urlParameters.containsKey(TO_POST) ? Integer.parseInt(urlParameters.get(TO_POST)) : null;
			String message = urlParameters.containsKey(TEXT) ? urlParameters.get(TEXT) : null;
			Day fromDay = urlParameters.containsKey(FROM_DATE) ? toDay(urlParameters.get(FROM_DATE)) : null;
			Day toDay = urlParameters.containsKey(TO_DATE) ? toDay(urlParameters.get(TO_DATE)) : null;
			return new GuestBookPlace(fromPost, toPost, message, fromDay, toDay);
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
		public String getToken(GuestBookPlace place) {
			return buildTokenFromPlace(place);
		}

		private String buildTokenFromPlace(GuestBookPlace place) {
			Map<String, String> map = new TreeMap<String, String>();
			if (place.hasText()) {
				map.put(TEXT, place.getText());
			}
			if (place.hasFromDay()) {
				map.put(FROM_DATE, place.getFromDay().toString());
			}
			if (place.hasToDay()) {
				map.put(TO_DATE, place.getToDay().toString());
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

		private Day toDay(String dateString) {
			Day day = null;

			try {
				if (dateFormatIsYYMMDD(dateString)) {
					String year = dateString.substring(0, 2);
					String month = dateString.substring(2, 4);
					String dayOfMonth = dateString.substring(4, 6);
					day = new Day("20" + year, month, dayOfMonth);

				} else if (dateFormatIsYYYYMMDD(dateString)) {
					String year = dateString.substring(0, 4);
					String month = dateString.substring(4, 6);
					String dayOfMonth = dateString.substring(6, 8);
					day = new Day(year, month, dayOfMonth);

				} else if (dateFormatIsYY_MM_DD(dateString)) {
					String year = dateString.substring(0, 2);
					String month = dateString.substring(3, 5);
					String dayOfMonth = dateString.substring(6, 8);
					day = new Day("20" + year, month, dayOfMonth);

				} else if (dateFormatIsYYYY_MM_DD(dateString)) {
					String year = dateString.substring(0, 4);
					String month = dateString.substring(5, 7);
					String dayOfMonth = dateString.substring(8, 10);
					day = new Day(year, month, dayOfMonth);
				}

			} catch (Exception e) {
				System.out.println("Error converting dateString: " + dateString + " to date.");
			}

			return day;
		}

		private boolean dateFormatIsYYYY_MM_DD(String dateString) {
			return dateString.length() == 10;
		}

		private boolean dateFormatIsYY_MM_DD(String dateString) {
			return dateString.length() == 8 && dateString.indexOf("-") > -1;
		}

		private boolean dateFormatIsYYYYMMDD(String dateString) {
			return dateString.length() == 8 && dateString.indexOf("-") == -1;
		}

		private boolean dateFormatIsYYMMDD(String dateString) {
			return dateString.length() == 6;
		}

	}

	@Override
	public String toString() {
		return "GuestBookPlace [fromPost=" + fromPost + ", toPost=" + toPost + ", message=" + message + ", fromDay=" + fromDay + ", toDay="
				+ toDay + "]";
	}

}
