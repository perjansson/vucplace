package nu.pich.vucplace.client.guestbook.rpc;

import nu.pich.vucplace.shared.guestbook.command.DeletePostResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class DeletePostCallback implements AsyncCallback<DeletePostResult> {

	private final Handler handler;

	public DeletePostCallback(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void onSuccess(DeletePostResult result) {
		handler.handleDeletePostSuccess(result);
	}

	@Override
	public void onFailure(Throwable caught) {
		handler.handleFailure(caught);
	}

	public interface Handler {
		void handleDeletePostSuccess(DeletePostResult result);

		void handleFailure(Throwable caught);
	}
}
