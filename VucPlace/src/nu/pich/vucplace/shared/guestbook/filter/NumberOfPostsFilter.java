package nu.pich.vucplace.shared.guestbook.filter;

import java.util.ArrayList;
import java.util.List;

import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

public class NumberOfPostsFilter implements PostFilter {

	private static final long serialVersionUID = 1L;

	private int fromPost;
	private int toPost;

	public NumberOfPostsFilter(int fromPost, int toPost) {
		this.fromPost = fromPost;
		this.toPost = toPost;
	}

	NumberOfPostsFilter() {
	}

	@Override
	public List<PostDTO> filter(List<PostDTO> postDTOs) {
		return filterOnNumberOfPosts(postDTOs);
	}

	private List<PostDTO> filterOnNumberOfPosts(List<PostDTO> postDTOs) {
		List<PostDTO> filteredPosts = new ArrayList<PostDTO>();
		int size = postDTOs.size();

		int from = fromPost;
		int to = toPost;

		if (from < size) {
			if (to >= size) {
				to = size - 1;
			}
			for (int i = from; i <= to; i++) {
				filteredPosts.add(postDTOs.get(i));
			}
		}
		return filteredPosts;
	}

	@Override
	public String getDescription() {
		return "\nFiltered on NumberOfPosts. From: " + fromPost + " To: " + toPost;
	}

	@Override
	public int getFromPost() {
		return fromPost;
	}

	@Override
	public int getToPost() {
		return toPost;
	}

}
