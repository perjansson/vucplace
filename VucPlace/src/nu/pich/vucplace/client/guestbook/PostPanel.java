package nu.pich.vucplace.client.guestbook;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PostPanel extends Composite {

	private static PostElementUiBinder uiBinder = GWT.create(PostElementUiBinder.class);

	interface PostElementUiBinder extends UiBinder<Widget, PostPanel> {
	}

	public interface Handler {
		void onDeletePost(Long postKey);

		void onWhois(Long postKey);
	}

	private final List<Handler> handlers = new ArrayList<Handler>();

	private final Long postKey;

	@UiField
	Label name, date;

	@UiField
	HTML message;

	@UiField
	Anchor link;

	public PostPanel(Long postKey) {
		this.postKey = postKey;
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void addHander(Handler handler) {
		handlers.add(handler);
	}

	public void setName(String string) {
		name.setText(string);
	}

	public void setMessage(String string) {
		message.setHTML(string);
	}

	public void setLink(String string) {
		link.setText(string);
		link.setHref(string);
	}

	public void setDate(String string) {
		date.setText(string);
	}

	@UiHandler("trashCan")
	public void onDelete(ClickEvent event) {
		for (Handler handler : handlers) {
			handler.onDeletePost(postKey);
		}
	}

	@UiHandler("questionMark")
	public void onWhois(ClickEvent event) {
		for (Handler handler : handlers) {
			handler.onWhois(postKey);
		}
	}

	public Long getPostKey() {
		return postKey;
	}

}
