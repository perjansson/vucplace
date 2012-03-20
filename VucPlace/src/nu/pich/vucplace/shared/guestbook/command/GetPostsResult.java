package nu.pich.vucplace.shared.guestbook.command;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;
import nu.pich.vucplace.shared.guestbook.domain.PostDTOCollection;

public class GetPostsResult implements Result, Serializable {

	private static final long serialVersionUID = 1L;

	private PostDTOCollection postDTOCollection;

	GetPostsResult() {
	}

	public GetPostsResult(PostDTOCollection postDTOCollection) {
		this.postDTOCollection = postDTOCollection;
	}

	public List<PostDTO> getPosts() {
		return postDTOCollection.asList();
	}

	public int getFrom() {
		return postDTOCollection.getFromPost();
	}

	public int getTo() {
		return postDTOCollection.getToPost();
	}

	public int getNoofPosts() {
		return postDTOCollection.getNoofPosts();
	}
}
