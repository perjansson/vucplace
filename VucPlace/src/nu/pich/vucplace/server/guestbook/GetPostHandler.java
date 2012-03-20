package nu.pich.vucplace.server.guestbook;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import nu.pich.vucplace.server.authentication.AuthenticationService;
import nu.pich.vucplace.server.authentication.AuthenticationServiceImpl;
import nu.pich.vucplace.shared.guestbook.command.GetPostAction;
import nu.pich.vucplace.shared.guestbook.command.GetPostResult;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

public class GetPostHandler implements ActionHandler<GetPostAction, GetPostResult> {

	private final AuthenticationService authenticationService = new AuthenticationServiceImpl();
	private final GuestBookService guestBookService = new GuestBookServiceImpl();

	@Override
	public GetPostResult execute(GetPostAction action, ExecutionContext context) throws DispatchException {
		boolean verified = authenticationService.verifyPassKey(action.getPassKey());
		PostDTO postDTO = null;
		if (verified) {
			postDTO = guestBookService.getPost(action.getPostKey());
		}
		return new GetPostResult(postDTO);
	}

	@Override
	public Class<GetPostAction> getActionType() {
		return GetPostAction.class;
	}

	@Override
	public void rollback(GetPostAction action, GetPostResult result, ExecutionContext context) throws DispatchException {
	}

}
