package nu.pich.vucplace.client.guestbook;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import nu.pich.vucplace.client.ActionFactory;
import nu.pich.vucplace.client.ClientFactory;
import nu.pich.vucplace.client.guestbook.GuestBookView.Presenter;
import nu.pich.vucplace.client.guestbook.rpc.CreatePostCallback;
import nu.pich.vucplace.client.guestbook.rpc.DeletePostCallback;
import nu.pich.vucplace.client.guestbook.rpc.GetPostCallback;
import nu.pich.vucplace.client.guestbook.rpc.GetPostsCallback;
import nu.pich.vucplace.shared.guestbook.command.CreatePostAction;
import nu.pich.vucplace.shared.guestbook.command.CreatePostResult;
import nu.pich.vucplace.shared.guestbook.command.DeletePostAction;
import nu.pich.vucplace.shared.guestbook.command.DeletePostResult;
import nu.pich.vucplace.shared.guestbook.command.GetPostAction;
import nu.pich.vucplace.shared.guestbook.command.GetPostResult;
import nu.pich.vucplace.shared.guestbook.command.GetPostsAction;
import nu.pich.vucplace.shared.guestbook.command.GetPostsResult;
import nu.pich.vucplace.shared.guestbook.domain.Day;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class GuestBookActivity extends AbstractActivity implements Presenter, CreatePostCallback.Handler, GetPostsCallback.Handler,
		DeletePostCallback.Handler, GetPostCallback.Handler {

	private static final int DEFAULT_FROM_POST = 0;
	private static final int DEAFULT_TO_START = 49;
	private static final int DEFAULT_LOAD_MORE_INTERVAL = 50;

	private int currentFrom;
	private int currentTo;
	private int noofPosts;

	private final DispatchAsync dispatchAsync = new StandardDispatchAsync(new DefaultExceptionHandler());

	private final GuestBookView view;
	private List<PostDTO> posts = new ArrayList<PostDTO>();
	private final GuestBookPlace place;
	private final PlaceController placeController;

	public GuestBookActivity(GuestBookPlace place, ClientFactory clientFactory) {
		this.place = place;
		this.view = clientFactory.getGuestBookView();
		this.placeController = clientFactory.getPlaceController();
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		containerWidget.setWidget(view.asWidget());
		view.setPresenter(this);

		int fromPost = place.hasFromPost() ? place.getFromPost() : DEFAULT_FROM_POST;
		int toPost = place.hasToPost() ? place.getToPost() : DEAFULT_TO_START;
		String text = place.getText();
		Day fromDay = place.getFromDay();
		Day toDay = place.getToDay();
		System.out.println(place);
		retrievePosts(fromPost, toPost, text, fromDay, toDay);

		view.updateFilterText(text);
		view.updateFilterFromDate(fromDay);
		view.updateFilterToDate(toDay);

		view.updateClientIp(getClientIp());
		view.updateClientOs(getClientOs());
	}

	private void retrievePosts(int from, int to, String message, Day fromDay, Day toDay) {
		GetPostsAction action = ActionFactory.createGetPostsAction(from, to, message, fromDay, toDay);
		AsyncCallback<GetPostsResult> callback = new GetPostsCallback(this);
		dispatchAsync.execute(action, callback);
	}

	@Override
	public void onCreatePost(String name, String message, String link) {
		view.hideAddPostPanel();
		view.clearName();
		view.clearMessage();
		view.clearLink();
		view.updateNoofPosts(++noofPosts);

		PostDTO postDTO = new PostDTO();
		postDTO.setName(name);
		postDTO.setLink(link);
		postDTO.setMessage(message);
		postDTO.setDateTime(new Date());
		postDTO.setClientIp(getClientIp());
		postDTO.setClientBrowser(getClientBrowser());
		postDTO.setClientOs(getClientOs());

		addNewPost(postDTO);
		Window.scrollTo(0, 0);

		GetPostsAction getPostsAction = ActionFactory.createGetPostsAction(currentFrom, currentTo, view.getFilterText(),
				view.getFilterFromDay(), view.getFilterToDay());
		CreatePostAction action = new CreatePostAction(postDTO, getPostsAction);
		AsyncCallback<CreatePostResult> callback = new CreatePostCallback(this);
		dispatchAsync.execute(action, callback);
	}

	private void addNewPost(PostDTO postDTO) {
		posts.add(0, postDTO);
		refreshViewWithPosts();
	}

	private void refreshViewWithPosts() {
		view.clearPosts();
		view.updatePosts(posts);
		view.updateNoofPosts(noofPosts);
	}

	@Override
	public void handleCreatePostSuccess(CreatePostResult result) {
		// Do nothing at the moment
	}

	@Override
	public void onDeletePost(Long postKey) {
		String passKey = getPassKeyFromScreen();
		if (passKey != null) {
			DeletePostAction action = new DeletePostAction(postKey, passKey);
			AsyncCallback<DeletePostResult> callback = new DeletePostCallback(this);
			dispatchAsync.execute(action, callback);
		}
	}

	@Override
	public void onWhois(Long postKey) {
		String passKey = getPassKeyFromScreen();
		if (passKey != null) {
			GetPostAction action = new GetPostAction(postKey, passKey);
			AsyncCallback<GetPostResult> callback = new GetPostCallback(this);
			dispatchAsync.execute(action, callback);
		}
	}

	private String getPassKeyFromScreen() {
		return Window.prompt("Hmm, do you really have permission?", "pass key");
	}

	@Override
	public void onUpdatePosts() {
		placeController.goTo(new GuestBookPlace(currentFrom, currentTo, view.getFilterText(), view.getFilterFromDay(), view
				.getFilterToDay()));
	}

	@Override
	public void onLoadMorePosts() {
		view.showLoadingPostsMessage();
		placeController.goTo(new GuestBookPlace(currentFrom, currentTo + DEFAULT_LOAD_MORE_INTERVAL, view.getFilterText(), view
				.getFilterFromDay(), view.getFilterToDay()));
	}

	@Override
	public void handleDeletePostSuccess(DeletePostResult result) {
		PostDTO postDTO = new PostDTO();
		postDTO.setKey(result.getPostKey());
		view.removePost(postDTO);
	}

	@Override
	public void handleGetPostSuccess(GetPostResult result) {
		PostDTO postDTO = result.getPostDTO();
		StringBuffer buffer = new StringBuffer();
		buffer.append("IP: " + postDTO.getClientIp());
		buffer.append("\n");
		buffer.append("OS: " + postDTO.getClientOs());
		buffer.append("\n");
		buffer.append("Browser: " + postDTO.getClientBrowser());
		Window.alert(buffer.toString());
	}

	@Override
	public void handleGetPostsSuccess(GetPostsResult result) {
		view.clearPosts();
		currentFrom = result.getFrom();
		currentTo = result.getTo();

		posts = result.getPosts();
		noofPosts = result.getNoofPosts();

		refreshViewWithPosts();

		view.showPostsContentPanel();
		view.hideLoadingPostsMessage();
	}

	private void disableView() {
		view.disableName();
		view.disableLink();
		view.disableMessage();
		view.disableCreatePostButton();
	}

	private void enableView() {
		view.enableName();
		view.enableLink();
		view.enableMessage();
		view.enableCreatePostButton();
	}

	@Override
	public void handleFailure(Throwable caught) {
		enableView();
		view.hideLoadingPostsMessage();
		view.hideCreatePostMessage();
		view.showErrorMessage("Ooops, det blev fel...");
	}

	public native String getClientIp() /*-{
										return $wnd.ipAddress;
										}-*/;

	private String getClientOs() {
		return Window.Navigator.getPlatform();
	}

	private String getClientBrowser() {
		return Window.Navigator.getUserAgent();
	}
}
