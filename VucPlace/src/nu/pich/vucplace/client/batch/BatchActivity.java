package nu.pich.vucplace.client.batch;

import net.customware.gwt.dispatch.client.DefaultExceptionHandler;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.client.standard.StandardDispatchAsync;
import nu.pich.vucplace.client.ActionFactory;
import nu.pich.vucplace.client.ClientFactory;
import nu.pich.vucplace.client.batch.rpc.BatchCallback;
import nu.pich.vucplace.shared.batch.command.BatchAction;
import nu.pich.vucplace.shared.batch.command.BatchResult;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class BatchActivity extends AbstractActivity implements BatchCallback.Handler {

	private final DispatchAsync dispatchAsync = new StandardDispatchAsync(new DefaultExceptionHandler());

	private final BatchPlace place;

	public BatchActivity(BatchPlace place, ClientFactory clientFactory) {
		this.place = place;
	}

	@Override
	public void start(AcceptsOneWidget containerWidget, EventBus eventBus) {
		String url = place.getUrl();
		boolean deleteAll = false;
		if (place.shouldDeleteAll() && userHasConfirmed()) {
			deleteAll = true;
		}
		BatchAction action = ActionFactory.createBatch(url, deleteAll);
		AsyncCallback<BatchResult> callback = new BatchCallback(this);
		dispatchAsync.execute(action, callback);
	}

	private boolean userHasConfirmed() {
		return Window.confirm("Are you sure you want to delete all posts?");
	}

}
