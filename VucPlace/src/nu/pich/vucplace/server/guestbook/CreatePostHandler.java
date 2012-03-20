package nu.pich.vucplace.server.guestbook;

import net.customware.gwt.dispatch.server.ActionHandler;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.shared.DispatchException;
import nu.pich.vucplace.shared.guestbook.command.CreatePostAction;
import nu.pich.vucplace.shared.guestbook.command.CreatePostResult;
import nu.pich.vucplace.shared.guestbook.command.GetPostsResult;

public class CreatePostHandler implements ActionHandler<CreatePostAction, CreatePostResult> {

	private final GuestBookService guestBookService = new GuestBookServiceImpl();

	@Override
	public CreatePostResult execute(CreatePostAction action, ExecutionContext context) throws DispatchException {
		guestBookService.addPost(action.getPost());
		GetPostsResult getPostsResult = context.execute(action.getGetPostsAction());
		return new CreatePostResult(getPostsResult);
	}

	@Override
	public Class<CreatePostAction> getActionType() {
		return CreatePostAction.class;
	}

	@Override
	public void rollback(CreatePostAction action, CreatePostResult result, ExecutionContext context) throws DispatchException {
	}

}
