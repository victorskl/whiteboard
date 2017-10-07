package whiteboard;

import org.junit.Test;
import whiteboard.ui.model.DefaultRoles;

import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void message() throws Exception {
        assertEquals("true", "true");
    }

    @Test
    public void testUserRole(){
        System.out.println(DefaultRoles.ROLE_LECTURER.toString());
    }
}