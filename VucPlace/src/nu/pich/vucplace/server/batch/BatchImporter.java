package nu.pich.vucplace.server.batch;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import nu.pich.vucplace.server.guestbook.GuestBookService;
import nu.pich.vucplace.server.guestbook.GuestBookServiceImpl;
import nu.pich.vucplace.shared.guestbook.domain.PostDTO;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class BatchImporter {

	private Document document;

	private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private final GuestBookService service = new GuestBookServiceImpl();

	public void execute(String urlToXmlFile) throws Exception {
		try {
			System.out.println("1. Getting file");
			getFile(urlToXmlFile);
			System.out.println("2. Creating postDTOs");
			List<PostDTO> posts = createPostsFromFile();
			System.out.println("3. Saving postDTOs");
			savePosts(posts);
			System.out.println("4. Saved " + posts.size() + " post(s)");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	private void getFile(String urlToXmlFile) throws ParserConfigurationException, SAXException, IOException {
		URL resource = BatchImporter.class.getResource("/" + urlToXmlFile);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		document = db.parse(new InputSource(resource.openStream()));
		document.getDocumentElement().normalize();
	}

	private List<PostDTO> createPostsFromFile() {
		NodeList posts = document.getElementsByTagName("Guestbook");

		List<PostDTO> postDTOs = new ArrayList<PostDTO>();
		for (int s = 0; s < posts.getLength(); s++) {
			try {
				Node node = posts.item(s);
				PostDTO postDTO = parsePostFromNode(node);
				postDTOs.add(postDTO);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return postDTOs;
	}

	private PostDTO parsePostFromNode(Node node) throws ParseException {
		PostDTO postDTO = new PostDTO();

		String name = getElementFromTag((Element) node, "Name");
		String link = getElementFromTag((Element) node, "Email");
		String message = getElementFromTag((Element) node, "Post");
		Date date = parseDate(getElementFromTag((Element) node, "Date"));
		String ip = getElementFromTag((Element) node, "IP");

		postDTO.setName(name != null ? name : "");
		postDTO.setLink(link != null ? link : "");
		postDTO.setMessage(message != null ? message : "");
		postDTO.setDateTime(date);
		postDTO.setClientIp(ip != null ? ip : "");

		return postDTO;
	}

	private String getElementFromTag(Element element, String tag) {
		String value = null;

		NodeList nameList = element.getElementsByTagName(tag);
		Element nameElement = (Element) nameList.item(0);

		if (nameElement != null) {
			NodeList textNameList = nameElement.getChildNodes();
			Node item = textNameList.item(0);

			if (item != null) {
				value = item.getNodeValue().trim();
			}
		}

		return value;
	}

	private Date parseDate(String dateString) throws ParseException {
		if (dateString == null) {
			dateString = "2003-01-01 12:00:00";
		}

		return formatter.parse(dateString);
	}

	private void savePosts(List<PostDTO> posts) {
		for (PostDTO postDTO : posts) {
			service.addPost(postDTO);
		}
	}

}
