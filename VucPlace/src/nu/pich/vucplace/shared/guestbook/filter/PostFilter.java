package nu.pich.vucplace.shared.guestbook.filter;

import java.io.Serializable;
import java.util.List;

import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

public interface PostFilter extends Serializable {

	List<PostDTO> filter(List<PostDTO> postDTOs);

	String getDescription();

	int getFromPost();

	int getToPost();

}
