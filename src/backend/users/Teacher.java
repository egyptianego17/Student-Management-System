package backend.users;

import backend.constants.Positions;
public class Teacher extends Admin {
    private boolean approved=false;
    public Teacher(String first_name, String last_name, String email, String password, Positions position, boolean csv) throws Exception {
        super(first_name, last_name, email, password, position, csv);
    }
}
