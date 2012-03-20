package nu.pich.vucplace.shared.guestbook.domain;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long key;
	@Persistent
	private String name;
	@Persistent
	private Text message;
	@Persistent
	private String link;
	@Persistent
	private Date dateTime;
	@Persistent
	private String clientIp;
	@Persistent
	private String clientBrowser;
	@Persistent
	private String clientOs;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Text getMessage() {
		return message;
	}

	public void setMessage(Text message) {
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

}
