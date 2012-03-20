package nu.pich.vucplace.shared.guestbook.command;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class GetPostAction implements Action<GetPostResult>, Serializable {

	private static final long serialVersionUID = 1L;

	private Long postKey;
	private String passKey;

	GetPostAction() {
	}

	public GetPostAction(Long postKey, String passKey) {
		this.postKey = postKey;
		this.passKey = passKey;
	}

	public Long getPostKey() {
		return postKey;
	}

	public String getPassKey() {
		return passKey;
	}

}
