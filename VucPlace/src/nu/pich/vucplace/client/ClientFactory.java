package nu.pich.vucplace.client;

import nu.pich.vucplace.client.guestbook.GuestBookView;

import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

public interface ClientFactory {
	EventBus getEventBus();

	PlaceController getPlaceController();

	GuestBookView getGuestBookView();
}
