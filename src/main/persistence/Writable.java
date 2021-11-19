package persistence;

import org.json.JSONObject;

//Allows use of method toJson() in Book and Booklist classes
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
