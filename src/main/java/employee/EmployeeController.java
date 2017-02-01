package employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final EmployeeRepository repository;
    private GoogleHttpClient client;

    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
        this.client = new GoogleHttpClient();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Employee> all() {
        return repository.findAll();
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Employee input) {
        Employee employee = repository.save(input);
        notifyCreationOf(employee);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(locationFor(employee));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

    }

    private void notifyCreationOf(Employee employee) {
        String json = "{\"name\": \"" + employee.name + "\", \"email\": \"" + employee.email+ "\"}";
        client.post(System.getenv("URL"), json);
    }

    private URI locationFor(Employee employee) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(employee.id).toUri();
    }
}
