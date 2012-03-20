package nu.pich.vucplace.server.guestbook;

import java.util.ArrayList;
import java.util.List;

import nu.pich.vucplace.shared.guestbook.domain.Post;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

import com.google.appengine.api.datastore.Text;

public class PostConverter {

	public static Post convertToPost(PostDTO postDTO) {
		Post post = new Post();
		post.setName(postDTO.getName());
		post.setLink(postDTO.getLink());
		post.setMessage(new Text(postDTO.getMessage()));
		post.setDateTime(postDTO.getDateTime());
		post.setClientIp(postDTO.getClientIp());
		post.setClientBrowser(postDTO.getClientBrowser());
		post.setClientOs(postDTO.getClientOs());
		return post;
	}

	public static List<PostDTO> convertToPostDTOs(List<Post> posts) {
		List<PostDTO> postDTOs = new ArrayList<PostDTO>();
		for (Post post : posts) {
			postDTOs.add(convertToPostDTO(post));
		}
		return postDTOs;
	}

	public static PostDTO convertToPostDTO(Post post) {
		PostDTO postDTO = new PostDTO();
		postDTO.setKey(post.getKey());
		postDTO.setName(post.getName());
		postDTO.setLink(post.getLink());
		postDTO.setMessage(post.getMessage().getValue());
		postDTO.setDateTime(post.getDateTime());
		postDTO.setClientIp(post.getClientIp());
		postDTO.setClientBrowser(post.getClientBrowser());
		postDTO.setClientOs(post.getClientOs());
		return postDTO;
	}
}
