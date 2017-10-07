package akj1.cmu.edu.model;

import java.util.List;

import akj1.cmu.edu.model.QuoteModel;

public class AuthorModel {
    
    private Long id;
    private String name;
    private List<QuoteModel> quotes;
	protected AuthorModel() {}
    public AuthorModel(String name, long authorID) {
        this.name = name;
        this.id = authorID;
    }

    @Override
    public String toString() {
        return String.format("AuthorModel[id=%d, name='%s']", id, this.name);
    }
    
    // Getters and Setters defined below
    public Long getId () {
        return this.id;
    }

	public List<QuoteModel> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<QuoteModel> quotes) {
		this.quotes = quotes;
	}
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}