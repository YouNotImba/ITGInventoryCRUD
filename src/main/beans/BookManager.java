package main.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import main.dbutil.ConnectionManager;

@ManagedBean
@RequestScoped
public class BookManager {

	private int id;
	private String title;
	private String author;
	private String searchString;
	private List<Book> searchResult;

	public BookManager() {
	}

	public BookManager(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public List<Book> getSearchResult() {
		return searchResult;
	}

	public void setSearchResult(List<Book> searchResult) {
		this.searchResult = searchResult;
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<Book> getAllBooks() {
		List<Book> result = new ArrayList<>();
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			result = session.createQuery("from Book").list();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		return result;
	}

	public String search() {
		if (searchString.isEmpty())
			return "index";
		List<Book> result = new ArrayList<>();
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			result = session.createQuery("from Book").list();
			session.getTransaction().commit();
			searchResult = new ArrayList<>();
			for (Book book : result) {
				if (book.getTitle().equalsIgnoreCase(searchString) || book.getAuthor().equalsIgnoreCase(searchString)) {
					searchResult.add(book);
				}
			}
			if (searchResult.size() > 0) {
				return "search_result";
			} else
				return "error";
		} finally {
			factory.close();
		}
		
	}

	public void edit() {
		int id;
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		System.out.println("edit before parse");
		id = Integer.parseInt(request.getParameter("id"));
		System.out.println("edit after parse");
		SessionFactory factory = ConnectionManager.getOurInstance().getFactory();
		try {
			Session session = factory.openSession();
			session.beginTransaction();
			List<Book> books = new ArrayList<>();
			books = session.createQuery("form Book a where a.id = " + id).list();
			Book temp = books.get(0);
			this.setId(temp.getId());
			this.setTitle(temp.getTitle());
			this.setAuthor(temp.getAuthor());
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Exception in edit");
		} finally {
			factory.close();
		}
	}

}
