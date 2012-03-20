package nu.pich.vucplace.shared.guestbook.domain;

import java.io.Serializable;
import java.util.List;

public class PostDTOCollection implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<PostDTO> postDTOs;
	private int fromPost;
	private int toPost;
	private int noofPosts;

	PostDTOCollection() {
	}

	public PostDTOCollection(List<PostDTO> postDTOs, int fromPost, int toPost, int noofPosts) {
		this.postDTOs = postDTOs;
		this.fromPost = fromPost;
		this.toPost = toPost;
		this.noofPosts = noofPosts;
	}

	public List<PostDTO> asList() {
		return postDTOs;
	}

	public int getFromPost() {
		return fromPost;
	}

	public int getToPost() {
		return toPost;
	}

	public int getNoofPosts() {
		return noofPosts;
	}

}
