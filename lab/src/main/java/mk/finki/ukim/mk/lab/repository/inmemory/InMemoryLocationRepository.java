package mk.finki.ukim.mk.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.bootsrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryLocationRepository {
    public List<Location> findAll(){
        return DataHolder.locations;
    }
    public Optional<Location> findById(Long id){
        return DataHolder.locations.stream().filter(location -> location.getId().equals(id)).findFirst();
    }
}
