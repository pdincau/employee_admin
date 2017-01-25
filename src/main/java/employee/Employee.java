package employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long id;

    public String name;
    public String surname;
    public String title;
    public String email;
    public String salary;

    public Employee() {}

    public Employee(String name, String surname, String title, String email, String salary) {
        this.name = name;
        this.surname = surname;
        this.title = title;
        this.email = email;
        this.salary = salary;
    }

}
