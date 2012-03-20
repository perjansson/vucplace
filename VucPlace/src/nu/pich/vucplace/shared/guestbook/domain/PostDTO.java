package nu.pich.vucplace.shared.guestbook.domain;

import java.io.Serializable;
import java.util.Date;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long key;
	private String name;
	private String message;
	private String link;
	private Date dateTime;
	private String clientIp;
	private String clientBrowser;
	private String clientOs;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getClientBrowser() {
		return clientBrowser;
	}

	public void setClientBrowser(String clientBrowser) {
		this.clientBrowser = clientBrowser;
	}

	public String getClientOs() {
		return clientOs;
	}

	public void setClientOs(String clientOs) {
		this.clientOs = clientOs;
	}

	public Long getKey() {
		return this.key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "PostDTO [key=" + key + ", name=" + name + ", message=" + message + ", link=" + link + ", dateTime=" + dateTime
				+ ", clientIp=" + clientIp + ", clientBrowser=" + clientBrowser + ", clientOs=" + clientOs + "]";
	}

}
