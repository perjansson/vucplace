package nu.pich.vucplace.client.batch.rpc;

import nu.pich.vucplace.shared.batch.command.BatchResult;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class BatchCallback implements AsyncCallback<BatchResult> {

	@SuppressWarnings("unused")
	private final Handler handler;

	public BatchCallback(Handler handler) {
		this.handler = handler;
	}

	@Override
	public void onSuccess(BatchResult result) {
	}

	@Override
	public void onFailure(Throwable caught) {
	}

	public interface Handler {

	}
}
