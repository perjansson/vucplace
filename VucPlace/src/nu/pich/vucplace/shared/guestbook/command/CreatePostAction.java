package nu.pich.vucplace.shared.guestbook.command;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

public class CreatePostAction implements Action<CreatePostResult>, Serializable {

	private static final long serialVersionUID = 1L;

	private PostDTO post;
	private GetPostsAction getPostsAction;

	CreatePostAction() {
	}

	public CreatePostAction(PostDTO post, GetPostsAction getPostsAction) {
		this.post = post;
		this.getPostsAction = getPostsAction;
	}

	public PostDTO getPost() {
		return post;
	}

	public GetPostsAction getGetPostsAction() {
		return getPostsAction;
	}

}
