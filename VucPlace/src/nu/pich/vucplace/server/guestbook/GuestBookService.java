package nu.pich.vucplace.server.guestbook;

import nu.pich.vucplace.shared.guestbook.domain.PostDTO;
import nu.pich.vucplace.shared.guestbook.domain.PostDTOCollection;
import nu.pich.vucplace.shared.guestbook.filter.PostFilter;

public interface GuestBookService {

	PostDTOCollection getPosts(PostFilter postsFilter);

	void addPost(PostDTO post);

	boolean deletePost(Long postKey);

	PostDTO getPost(Long postKey);

	void deleteAllPosts();

}
