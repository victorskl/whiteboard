package whiteboard;

import static org.junit.Assert.*;

import org.junit.Test;
import whiteboard.domain.model.Role;
import whiteboard.domain.model.Staff;

public class AppTest {

    @Test
    public void message() throws Exception {
        assertEquals("true", "true");
    }

    @Test
    public void testUserRole(){
        Role role = new Role();

        //role.setName("USER");
        //role.setName(DefaultRoles.valueOf(DefaultRoles.ADMIN));

        Staff staff = new Staff();
        staff.getRoles().add(role);
    }
}