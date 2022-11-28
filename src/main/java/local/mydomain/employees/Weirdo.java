package local.mydomain.employees;

import java.time.LocalDate;

public record Weirdo(String lastname, String firstName, LocalDate dob) implements Apple {
    public Weirdo(String lastName, String firstName) {
        this(lastName,firstName,LocalDate.of(1,1,1));
    }

    public String sayHello() {
        return "Hello World";
    }
}

