package nu.pich.vucplace.client;

import nu.pich.vucplace.client.guestbook.GuestBookPlace;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.web.bindery.event.shared.EventBus;

public class VucPlaceEntryPoint implements EntryPoint {

	private final Place defaultPlace = GuestBookPlace.emptyPlace();
	private final SimplePanel appWidget = new SimplePanel();

	@Override
	public void onModuleLoad() {
		ClientFactory clientFactory = GWT.create(ClientFactory.class);
		EventBus eventBus = clientFactory.getEventBus();
		PlaceController placeController = clientFactory.getPlaceController();

		ActivityMapper activityMapper = new WebActivityMapper(clientFactory);
		ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
		activityManager.setDisplay(appWidget);

		WebPlaceHistoryMapper historyMapper = GWT.create(WebPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootPanel.get().add(appWidget);

		WebResources.INSTANCE.style().ensureInjected();

		historyHandler.handleCurrentHistory();
	}
}
