package nu.pich.vucplace.shared.guestbook.filter;

import java.util.Iterator;
import java.util.List;

import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

public class TextContainsSubPostsFilter extends SubPostFilter {

	private static final long serialVersionUID = 1L;

	private String text;

	public TextContainsSubPostsFilter(String text, PostFilter subPostFilter) {
		super(subPostFilter);
		this.text = text;
	}

	TextContainsSubPostsFilter() {
	}

	@Override
	public List<PostDTO> filter(List<PostDTO> postDTOs) {
		List<PostDTO> filteredPostDTOs = super.filter(postDTOs);
		return filterOnTextContains(filteredPostDTOs);
	}

	private List<PostDTO> filterOnTextContains(List<PostDTO> postDTOs) {
		Iterator<PostDTO> iter = postDTOs.iterator();
		while (iter.hasNext()) {
			if (!isMatch(iter.next())) {
				iter.remove();
			}
		}
		return postDTOs;
	}

	private boolean isMatch(PostDTO postDTO) {
		return isMatchOnMessage(postDTO) || isMatchOnName(postDTO) || isMatchOnLink(postDTO);
	}

	private boolean isMatchOnLink(PostDTO postDTO) {
		return isEqualToText(postDTO.getLink().toLowerCase());
	}

	private boolean isMatchOnName(PostDTO postDTO) {
		return isEqualToText(postDTO.getName().toLowerCase());
	}

	private boolean isMatchOnMessage(PostDTO postDTO) {
		return isEqualToText(postDTO.getMessage().toLowerCase());
	}

	private boolean isEqualToText(String postText) {
		return postText != null && postText.contains(text.toLowerCase());
	}

	@Override
	public String getDescription() {
		return super.getDescription() + "\nFiltered on MessageContains. Message: " + text;
	}

}
