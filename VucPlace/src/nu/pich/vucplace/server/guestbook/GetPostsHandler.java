package nu.pich.vucplace.server.guestbook;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import nu.pich.vucplace.shared.guestbook.command.GetPostsAction;
import nu.pich.vucplace.shared.guestbook.command.GetPostsResult;
import nu.pich.vucplace.shared.guestbook.domain.PostDTOCollection;

public class GetPostsHandler implements ActionHandler<GetPostsAction, GetPostsResult> {

	private final GuestBookService guestBookService = new GuestBookServiceImpl();

	@Override
	public GetPostsResult execute(GetPostsAction action, ExecutionContext context) throws DispatchException {
		PostDTOCollection postDTOCollection = guestBookService.getPosts(action.getPostsFilter());
		return new GetPostsResult(postDTOCollection);
	}

	@Override
	public Class<GetPostsAction> getActionType() {
		return GetPostsAction.class;
	}

	@Override
	public void rollback(GetPostsAction action, GetPostsResult result, ExecutionContext context) throws DispatchException {
	}

}
