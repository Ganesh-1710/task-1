import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/servers")
public class ServerController {

    @Autowired
    private ServerRepository serverRepository;

    @GetMapping
    public ResponseEntity<List<Server>> getAllServers() {
        return new ResponseEntity<>(serverRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Server> getServer(@PathVariable String id) {
        Server server = serverRepository.findById(id).orElse(null);
        if (server == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(server, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Server> createServer(@RequestBody Server server) {
        serverRepository.save(server);
        return new ResponseEntity<>(server, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Server> updateServer(@PathVariable String id, @RequestBody Server server) {
        Server updatedServer = serverRepository.save(server);
        return new ResponseEntity<>(updatedServer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServer(@PathVariable String id) {
        serverRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<List<Server>> findServersByName(@PathVariable String name) {
        List<Server> servers = serverRepository.findByNameContaining(name);
        if (servers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(servers, HttpStatus.OK);
        }
    }
}
