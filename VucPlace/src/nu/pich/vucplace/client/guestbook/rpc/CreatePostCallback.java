package nu.pich.vucplace.client.guestbook.rpc;

import nu.pich.vucplace.shared.guestbook.command.CreatePostResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class CreatePostCallback implements AsyncCallback<CreatePostResult> {

	private final Handler handler;

	public CreatePostCallback(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void onSuccess(CreatePostResult result) {
		handler.handleCreatePostSuccess(result);
	}

	@Override
	public void onFailure(Throwable caught) {
		handler.handleFailure(caught);
	}

	public interface Handler {
		void handleCreatePostSuccess(CreatePostResult result);

		void handleFailure(Throwable caught);
	}
}
