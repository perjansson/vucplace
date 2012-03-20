package nu.pich.vucplace.client.guestbook;

import java.util.List;

import nu.pich.vucplace.shared.guestbook.domain.Day;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;

public class GuestBookViewImpl extends Composite implements GuestBookView, KeyPressDownHandler.Handler, PostPanel.Handler,
		ScrollToBottomHandler.Handler {

	private static GuestBookListUiBinder uiBinder = GWT.create(GuestBookListUiBinder.class);

	interface GuestBookListUiBinder extends UiBinder<Widget, GuestBookViewImpl> {
	}

	private Presenter presenter;

	@UiField
	Panel postsPanel, addPostPanel, loadPostsPanel;

	@UiField
	TextBox name, link, filterText;

	@UiField
	TextArea message;

	@UiField
	Button createPostButton, loadMorePostsButton;

	@UiField
	Label createPostMessage, clientIp, clientOs, noofPosts;

	@UiField
	DateBox filterFromDate, filterToDate;

	public GuestBookViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		addEventHandlers();
		initView();
	}

	private void addEventHandlers() {
		addKeyPressDownHandler();
		addScrollToBottomHandler();
	}

	private void initView() {
		DateBox.Format format = new DateBox.DefaultFormat(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_SHORT));
		filterFromDate.setFormat(format);
		filterToDate.setFormat(format);
	}

	private void addKeyPressDownHandler() {
		KeyPressDownHandler handler = new KeyPressDownHandler(this);
		name.addKeyPressHandler(handler);
		link.addKeyPressHandler(handler);
		message.addKeyPressHandler(handler);
	}

	private void addScrollToBottomHandler() {
		Window.addWindowScrollHandler(new ScrollToBottomHandler(postsPanel, this));
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void updatePosts(List<PostDTO> posts) {
		for (PostDTO postDTO : posts) {
			PostPanel panel = createPostPanelFrom(postDTO);
			postsPanel.add(panel);
		}
	}

	@Override
	public void updateNoofPosts(int noof) {
		noofPosts.setText(String.valueOf(noof));
	}

	private PostPanel createPostPanelFrom(PostDTO postDTO) {
		PostPanel panel = new PostPanel(postDTO.getKey());
		panel.setName(postDTO.getName());
		panel.setMessage(postDTO.getMessage());
		panel.setLink(postDTO.getLink());
		String date = DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM).format(postDTO.getDateTime());
		panel.setDate(date);
		panel.addHander(this);
		return panel;
	}

	@Override
	public void removePost(PostDTO postDTO) {
		for (Widget widget : postsPanel) {
			if (widget instanceof PostPanel) {
				PostPanel postPanel = (PostPanel) widget;
				if (postPanel.getPostKey().equals(postDTO.getKey())) {
					postsPanel.remove(postPanel);
				}
			}
		}
	}

	@Override
	public void updateClientIp(String text) {
		clientIp.setText(text);
	}

	@Override
	public void updateClientOs(String text) {
		clientOs.setText(text);
	}

	@Override
	public void clearPosts() {
		postsPanel.clear();
	}

	@Override
	public void showPostsContentPanel() {
		postsPanel.setVisible(true);
	}

	@UiHandler("showAddPostPanelButton")
	public void onShowAddPostPanel(ClickEvent event) {
		addPostPanel.setVisible(!addPostPanel.isVisible());
		name.setFocus(true);
	}

	@UiHandler("createPostButton")
	public void onCreatePost(ClickEvent event) {
		checkLink();
		if (newPostIsValid()) {
			presenter.onCreatePost(name.getText(), message.getText(), link.getText());
		} else {
			Window.alert("Ooops, namn eller meddelande kan inte vara tomt...");
		}
	}

	@UiHandler("loadMorePostsButton")
	public void onLoadMorePosts(ClickEvent event) {
		presenter.onLoadMorePosts();
	}

	@Override
	public void onDeletePost(Long postKey) {
		presenter.onDeletePost(postKey);
	}

	@Override
	public void onWhois(Long postKey) {
		presenter.onWhois(postKey);
	}

	@UiHandler("updatePostsButton")
	public void onUpdatePosts(ClickEvent event) {
		presenter.onUpdatePosts();
	}

	private void checkLink() {
		String url = link.getText();
		if (!url.isEmpty()) {
			if (!url.startsWith("http://")) {
				link.setText("http://" + url);
			}
		}
	}

	private boolean newPostIsValid() {
		return !name.getText().isEmpty() && !message.getText().isEmpty();
	}

	@Override
	public void hideAddPostPanel() {
		addPostPanel.setVisible(false);
	}

	@Override
	public void clearName() {
		name.setText("");
	}

	@Override
	public void clearMessage() {
		message.setText("");
	}

	@Override
	public void clearLink() {
		link.setText("");
	}

	@Override
	public void disableCreatePostButton() {
		createPostButton.setEnabled(false);
	}

	@Override
	public void enableCreatePostButton() {
		createPostButton.setEnabled(true);
	}

	@Override
	public void showLoadingPostsMessage() {
		loadPostsPanel.setVisible(true);
	}

	@Override
	public void hideLoadingPostsMessage() {
		loadPostsPanel.setVisible(false);
	}

	@Override
	public void showCreatePostMessage() {
		createPostMessage.setVisible(true);
	}

	@Override
	public void hideCreatePostMessage() {
		createPostMessage.setVisible(false);
	}

	@Override
	public void disableName() {
		name.setEnabled(false);
	}

	@Override
	public void enableName() {
		name.setEnabled(true);
	}

	@Override
	public void disableLink() {
		link.setEnabled(false);
	}

	@Override
	public void enableLink() {
		link.setEnabled(true);
	}

	@Override
	public void disableMessage() {
		message.setEnabled(false);
	}

	@Override
	public void enableMessage() {
		message.setEnabled(true);
	}

	@Override
	public void showErrorMessage(String message) {
		Window.alert(message);
	}

	@Override
	public void onShiftEnterKeyPress() {
		onCreatePost(null);
	}

	@Override
	public void onEscapeKeyPress() {
		hideAddPostPanel();
	}

	@Override
	public String getFilterText() {
		return filterText.getText();
	}

	@Override
	public Day getFilterFromDay() {
		return Day.fromDate(filterFromDate.getValue());
	}

	@Override
	public Day getFilterToDay() {
		return Day.fromDate(filterToDate.getValue());
	}

	@Override
	public void updateFilterText(String text) {
		filterText.setText(text);
	}

	@Override
	public void updateFilterFromDate(Day fromDay) {
		filterFromDate.setValue(fromDay != null ? fromDay.toDate() : null);
	}

	@Override
	public void updateFilterToDate(Day toDay) {
		filterToDate.setValue(toDay != null ? toDay.toDate() : null);
	}

	@Override
	public void onScrollToBottom() {
		presenter.onLoadMorePosts();
	}
}
