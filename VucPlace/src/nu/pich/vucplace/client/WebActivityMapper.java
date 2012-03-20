package nu.pich.vucplace.client;

import nu.pich.vucplace.client.batch.BatchActivity;
import nu.pich.vucplace.client.batch.BatchPlace;
import nu.pich.vucplace.client.guestbook.GuestBookActivity;
import nu.pich.vucplace.client.guestbook.GuestBookPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class WebActivityMapper implements ActivityMapper {

	private final ClientFactory clientFactory;

	public WebActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		Activity activity = null;

		if (place instanceof GuestBookPlace) {
			activity = new GuestBookActivity((GuestBookPlace) place, clientFactory);
		} else if (place instanceof BatchPlace) {
			activity = new BatchActivity((BatchPlace) place, clientFactory);
		}

		return activity;
	}

}
