package nu.pich.vucplace.shared.guestbook.command;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;
import nu.pich.vucplace.shared.guestbook.filter.PostFilter;

public class GetPostsAction implements Action<GetPostsResult>, Serializable {

	private static final long serialVersionUID = 1L;

	private PostFilter postsFilter;

	GetPostsAction() {
	}

	public GetPostsAction(PostFilter postsFilter) {
		this.postsFilter = postsFilter;
	}

	public PostFilter getPostsFilter() {
		return postsFilter;
	}

}
