package akj1.cmu.edu.model;


import akj1.cmu.edu.model.AuthorModel;

public class QuoteModel {

    private Long id;
    private String text;
    private String source;
    private AuthorModel author;
    private Long authorID;
    private String authName;
 
    public QuoteModel()
    {}
    
    public QuoteModel(String text, String source, Long authorID) {
        this.text = text;
        this.source = source;
        this.authorID = authorID;
    }

    @Override
    public String toString() {
    	return String.format("QuoteModel[id=%d,text='%s',by='%s',id=,source='%s']",this.id,this.text,this.authName, this.source);
    }

    // Getters and Setters defined below
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AuthorModel getAuthor() {
    	return this.author;
    }
    
    public void setAuthor(AuthorModel author) {
    	this.author = author;
    }
    
    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }
    
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getAuthorName() {
        return authName;
    }

    public void setAuthorName(String authName) {
        this.authName = authName;
    }

    public Long getId() {
        return id;
    }
    
    
}

