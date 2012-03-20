package nu.pich.vucplace.shared.guestbook.command;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

public class DeletePostResult implements Result, Serializable {

	private static final long serialVersionUID = 1L;

	private Long postKey;

	DeletePostResult() {
	}

	public DeletePostResult(Long postKey) {
		this.postKey = postKey;
	}

	public Long getPostKey() {
		return postKey;
	}

}
