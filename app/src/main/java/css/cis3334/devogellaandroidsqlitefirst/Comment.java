package css.cis3334.devogellaandroidsqlitefirst;

/**
 * This class defines a comment object and includes getters, setters, and a toString method.
 *
 * @author Modified by ibishop on 3/31/2017.
 */
public class Comment {
    private long id;
    private String comment;

    /*
    * This method returns the id of the comment
    *
    * @return Returns the id as a long datatype
     */
    public long getId() {
        return id;
    }

    /*
    * This method sets the id of the comment to the parameter
    *
    * @param long id This is the id that the comment will be set to
     */
    public void setId(long id) {
        this.id = id;
    }

    /*
    * This method returns the comment as a string
    *
    * @return Returns the comment as a string
     */
    public String getComment() {
        return comment;
    }

    /*
    * This method sets the comment to the String parameter
    *
    * @param String comment This is the string value the comment will be set to
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return comment;
    }
}