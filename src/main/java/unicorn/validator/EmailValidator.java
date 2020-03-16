package unicorn.validator;

import java.util.regex.Pattern;

public class EmailValidator {

    public boolean validate(String email){
        return email.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }
}
