package nu.pich.vucplace.shared.guestbook.command;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

public class GetPostResult implements Result, Serializable {

	private static final long serialVersionUID = 1L;

	private PostDTO postDTO;

	GetPostResult() {
	}

	public GetPostResult(PostDTO postDTO) {
		this.postDTO = postDTO;
	}

	public PostDTO getPostDTO() {
		return postDTO;
	}

}
