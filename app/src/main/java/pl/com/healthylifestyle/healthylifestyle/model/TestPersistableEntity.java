package pl.com.healthylifestyle.healthylifestyle.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by antosikj on 29/11/15.
 */

@Table(name="testpersistence")
public class TestPersistableEntity extends Model {

    public String name;
    public Date creationDate;

    // Make sure to have a default constructor for every ActiveAndroid model
    public TestPersistableEntity(){
        super();
    }

    public TestPersistableEntity(String name, Date creationDate) {
        super();

        this.name = name;
        this.creationDate = creationDate;
    }
}
