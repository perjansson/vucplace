package nu.pich.vucplace.server.guestbook;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import nu.pich.vucplace.server.authentication.AuthenticationService;
import nu.pich.vucplace.server.authentication.AuthenticationServiceImpl;
import nu.pich.vucplace.shared.guestbook.command.DeletePostAction;
import nu.pich.vucplace.shared.guestbook.command.DeletePostResult;

public class DeletePostHandler implements ActionHandler<DeletePostAction, DeletePostResult> {

	private final AuthenticationService authenticationService = new AuthenticationServiceImpl();
	private final GuestBookService guestBookService = new GuestBookServiceImpl();

	@Override
	public DeletePostResult execute(DeletePostAction action, ExecutionContext context) throws DispatchException {
		boolean verified = authenticationService.verifyPassKey(action.getPassKey());
		if (verified) {
			guestBookService.deletePost(action.getPostKey());
		}
		return new DeletePostResult(action.getPostKey());
	}

	@Override
	public Class<DeletePostAction> getActionType() {
		return DeletePostAction.class;
	}

	@Override
	public void rollback(DeletePostAction action, DeletePostResult result, ExecutionContext context) throws DispatchException {
	}

}
