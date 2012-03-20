package nu.pich.vucplace.shared.guestbook.filter;

import java.util.List;

import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

public abstract class SubPostFilter implements PostFilter {

	private static final long serialVersionUID = 1L;

	protected PostFilter subPostsFilter;

	public SubPostFilter(PostFilter subPostFilter) {
		this.subPostsFilter = subPostFilter;
	}

	SubPostFilter() {
	}

	@Override
	public List<PostDTO> filter(List<PostDTO> postDTOs) {
		return subPostsFilter.filter(postDTOs);
	}

	@Override
	public String getDescription() {
		return subPostsFilter.getDescription();
	}

	@Override
	public int getFromPost() {
		return subPostsFilter.getFromPost();
	}

	@Override
	public int getToPost() {
		return subPostsFilter.getToPost();
	}

}
