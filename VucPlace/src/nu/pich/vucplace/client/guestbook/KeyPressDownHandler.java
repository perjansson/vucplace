package nu.pich.vucplace.client.guestbook;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;

public class KeyPressDownHandler implements KeyPressHandler {

	private final Handler handler;

	public KeyPressDownHandler(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void onKeyPress(KeyPressEvent event) {
		if (event.isShiftKeyDown() && event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
			handler.onShiftEnterKeyPress();
		} else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
			handler.onEscapeKeyPress();
		}
	}

	public interface Handler {
		void onShiftEnterKeyPress();

		void onEscapeKeyPress();
	}
}
