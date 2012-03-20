package nu.pich.vucplace.client;

import nu.pich.vucplace.client.guestbook.GuestBookViewImpl;
import nu.pich.vucplace.client.guestbook.GuestBookView;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public class WebClientFactory implements ClientFactory {

	private final EventBus eventBus = new SimpleEventBus();
	private final PlaceController placeController = new PlaceController(eventBus);
	private final GuestBookView guestBookView = new GuestBookViewImpl();

	@Override
	public EventBus getEventBus() {
		return eventBus;
	}

	@Override
	public PlaceController getPlaceController() {
		return placeController;
	}

	@Override
	public GuestBookView getGuestBookView() {
		return guestBookView;
	}

}
