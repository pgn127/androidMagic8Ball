package uow.edu.au.magic8ball;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Pamneedle on 8/31/15.
 */
public class QuestionResponseModel implements Serializable {

    private String question;
    private String response;
    private String imageURL;
    private String username;
    private static final long serialVersionUID = 0L;


    public QuestionResponseModel(String question, String response, String imageURL, String username) {
        this.question = question;
        this.response = response;
        this.imageURL = imageURL;
        this.username = username;
    }

    public String getQuestion(){
        return this.question;
    }

    public String getResponse(){
        return this.response;
    }

    public String getUsername() { return this.username; }

    public String getImage() { return this.imageURL; }




//    private void writeObject(java.io.ObjectOutputStream out)
//            throws IOException {
//        // write 'this' to 'out'...
//    }
//
//    private void readObject(java.io.ObjectInputStream in)
//            throws IOException, ClassNotFoundException {
//        // populate the fields of 'this' from the data in 'in'...
//    }
}


