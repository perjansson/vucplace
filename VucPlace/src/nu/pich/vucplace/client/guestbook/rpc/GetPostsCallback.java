package nu.pich.vucplace.client.guestbook.rpc;

import nu.pich.vucplace.shared.guestbook.command.GetPostsResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class GetPostsCallback implements AsyncCallback<GetPostsResult> {

	private final Handler handler;

	public GetPostsCallback(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void onSuccess(GetPostsResult result) {
		handler.handleGetPostsSuccess(result);
	}

	@Override
	public void onFailure(Throwable caught) {
		handler.handleFailure(caught);
	}

	public interface Handler {
		void handleGetPostsSuccess(GetPostsResult result);

		void handleFailure(Throwable caught);
	}
}