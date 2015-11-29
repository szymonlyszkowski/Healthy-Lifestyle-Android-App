package pl.com.healthylifestyle.healthylifestyle;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.activeandroid.Model;
import com.activeandroid.query.Select;

import java.util.Date;
import java.util.List;

import pl.com.healthylifestyle.healthylifestyle.model.TestPersistableEntity;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}