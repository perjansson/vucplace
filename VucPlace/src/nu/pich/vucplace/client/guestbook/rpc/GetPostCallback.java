package nu.pich.vucplace.client.guestbook.rpc;

import nu.pich.vucplace.shared.guestbook.command.GetPostResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class GetPostCallback implements AsyncCallback<GetPostResult> {

	private final Handler handler;

	public GetPostCallback(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void onSuccess(GetPostResult result) {
		handler.handleGetPostSuccess(result);
	}

	@Override
	public void onFailure(Throwable caught) {
		handler.handleFailure(caught);
	}

	public interface Handler {
		void handleGetPostSuccess(GetPostResult result);

		void handleFailure(Throwable caught);
	}
}
