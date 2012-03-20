package nu.pich.vucplace.server.guestbook;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import nu.pich.vucplace.server.PersistenceManagerFactoryHelper;
import nu.pich.vucplace.shared.guestbook.domain.Post;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;
import nu.pich.vucplace.shared.guestbook.domain.PostDTOCollection;
import nu.pich.vucplace.shared.guestbook.filter.PostFilter;

public class GuestBookServiceImpl implements GuestBookService {

	@Override
	public void addPost(PostDTO postDTO) {
		PersistenceManager pm = getPersistenceManager();
		try {
			Post post = PostConverter.convertToPost(postDTO);
			pm.makePersistent(post);
		} catch (Exception e) {
			System.err.println("Error adding post: " + postDTO);
		} finally {
			pm.close();
		}
	}

	@Override
	public boolean deletePost(Long postKey) {
		PersistenceManager pm = getPersistenceManager();
		Post postToDelete = pm.getObjectById(Post.class, postKey);
		pm.deletePersistent(postToDelete);
		return true;
	}

	@Override
	public PostDTO getPost(Long postKey) {
		PersistenceManager pm = getPersistenceManager();
		Post post = pm.getObjectById(Post.class, postKey);
		return PostConverter.convertToPostDTO(post);
	}

	@Override
	public PostDTOCollection getPosts(PostFilter postsFilter) {
		List<PostDTO> allPostDTOs = getAllPosts();
		List<PostDTO> filterPosts = postsFilter.filter(allPostDTOs);
		System.out.println("############## POSTS FILTER ##############" + postsFilter.getDescription());
		return new PostDTOCollection(filterPosts, postsFilter.getFromPost(), postsFilter.getToPost(), allPostDTOs.size());
	}

	private List<PostDTO> getAllPosts() {
		List<PostDTO> allPostDTOs = new ArrayList<PostDTO>();

		PersistenceManager pm = getPersistenceManager();
		try {
			Query query = pm.newQuery("select from " + Post.class.getName() + " order by dateTime desc");
			@SuppressWarnings("unchecked")
			List<Post> allPosts = (List<Post>) query.execute();
			allPostDTOs = PostConverter.convertToPostDTOs(allPosts);

		} finally {
			pm.close();
		}

		return allPostDTOs;
	}

	private PersistenceManager getPersistenceManager() {
		return PersistenceManagerFactoryHelper.getFactory().getPersistenceManager();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void deleteAllPosts() {
		PersistenceManager pm = getPersistenceManager();
		try {
			Query query = pm.newQuery("select from " + Post.class.getName());
			List<Post> allPosts = (List<Post>) query.execute();
			System.out.println("### Found " + allPosts.size() + " before delete");
			for (Post post : allPosts) {
				deletePost(post.getKey());
			}

			query = pm.newQuery("select from " + Post.class.getName());
			allPosts = (List<Post>) query.execute();
			System.out.println("### Found " + allPosts.size() + " after delete");

		} finally {
			pm.close();
		}
	}
}