package nu.pich.vucplace.client.guestbook;

import java.util.Date;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ScrollEvent;
import com.google.gwt.user.client.Window.ScrollHandler;
import com.google.gwt.user.client.ui.Widget;

public class ScrollToBottomHandler implements ScrollHandler {

	private static final int TIME_WAIT = 2500;
	private static final int BOTTOM_OFFSET_PX = 25;

	private final Handler handler;

	private final Widget widget;

	private long lastScrollToBottom;

	private int lastScrollPos = 0;

	public ScrollToBottomHandler(Widget widget, Handler handler) {
		this.widget = widget;
		this.handler = handler;
	}

	@Override
	public void onWindowScroll(ScrollEvent event) {
		if (enoughTimeSinceLastScrollHasPassed() && scrollIsDown() && widgetIsLargerThanClientHeight() && scrollIsAtBottom()) {
			lastScrollToBottom = new Date().getTime();
			handler.onScrollToBottom();
		}
	}

	private boolean widgetIsLargerThanClientHeight() {
		int widgetHeight = widget.getOffsetHeight();
		int clientHeight = Window.getClientHeight();
		return widgetHeight - clientHeight > 0;
	}

	private boolean scrollIsAtBottom() {
		int clientHeight = Window.getClientHeight();
		int scrollPosition = Window.getScrollTop();
		int widgetHeight = widget.getOffsetHeight();
		return clientHeight + scrollPosition + BOTTOM_OFFSET_PX > widgetHeight;
	}

	private boolean scrollIsDown() {
		boolean isDownScroll = false;
		int scrollTopPos = Window.getScrollTop();
		if (lastScrollPos != 0 && (scrollTopPos - lastScrollPos > 5)) {
			isDownScroll = true;
		}
		lastScrollPos = scrollTopPos;
		return isDownScroll;
	}

	private boolean enoughTimeSinceLastScrollHasPassed() {
		boolean isEnoughTime = false;
		if (lastScrollToBottom == 0 || (new Date().getTime() - lastScrollToBottom > TIME_WAIT)) {
			isEnoughTime = true;
		}
		return isEnoughTime;
	}

	public interface Handler {
		void onScrollToBottom();
	}
}