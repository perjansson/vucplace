package nu.pich.vucplace.client;

import nu.pich.vucplace.client.batch.BatchPlace;
import nu.pich.vucplace.client.guestbook.GuestBookPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ GuestBookPlace.Tokenizer.class, BatchPlace.Tokenizer.class })
public interface WebPlaceHistoryMapper extends PlaceHistoryMapper {

}
