package nu.pich.vucplace.server.batch;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import nu.pich.vucplace.server.guestbook.GuestBookService;
import nu.pich.vucplace.server.guestbook.GuestBookServiceImpl;
import nu.pich.vucplace.shared.batch.command.BatchAction;
import nu.pich.vucplace.shared.batch.command.BatchResult;

public class BatchHandler implements ActionHandler<BatchAction, BatchResult> {

	private final BatchImporter batchImporter = new BatchImporter();
	private final GuestBookService guestBookService = new GuestBookServiceImpl();

	@Override
	public BatchResult execute(BatchAction action, ExecutionContext context) throws DispatchException {
		try {
			if (action.shouldDeleteAll()) {
				System.out.println("### BatchHandler.shouldDeleteAll");
				guestBookService.deleteAllPosts();
			}

			if (action.hasUrl()) {
				System.out.println("### BatchHandler.hasUrl");
				batchImporter.execute(action.getUrl());
			}
		} catch (Exception e) {
			throw new DispatchException("Error during batch!", e) {
				private static final long serialVersionUID = 1L;
			};
		}
		return new BatchResult();
	}

	@Override
	public Class<BatchAction> getActionType() {
		return BatchAction.class;
	}

	@Override
	public void rollback(BatchAction action, BatchResult result, ExecutionContext context) throws DispatchException {
	}

}
