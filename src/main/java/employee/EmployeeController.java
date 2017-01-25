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

    @Autowired
    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Employee> all() {
        return repository.findAll();
    }

    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<?> add(@RequestBody Employee input) {
        Employee result = repository.save(new Employee(input.name));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(locationFor(result));

        return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);

    }

    private URI locationFor(Employee employee) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(employee.id).toUri();
    }
}
