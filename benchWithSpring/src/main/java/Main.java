import model.entities.person.Person;
import model.entities.person.PersonDAO;
import model.entities.person.PersonDAOImplementation;
import org.hibernate.mapping.Map;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        final var applocationContext = new AnnotationConfigApplicationContext();

        final PersonDAO personDAO = applocationContext.getBean("personDAO", PersonDAO.class);

    }
}
