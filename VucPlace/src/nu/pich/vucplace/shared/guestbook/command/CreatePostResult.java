package nu.pich.vucplace.shared.guestbook.command;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

public class CreatePostResult implements Result, Serializable {

	private static final long serialVersionUID = 1L;

	private GetPostsResult getPostsResult;

	CreatePostResult() {
	}

	public CreatePostResult(GetPostsResult getPostsResult) {
		this.getPostsResult = getPostsResult;
	}

	public GetPostsResult getPostsResult() {
		return getPostsResult;
	}

}
