package Repository;

import com.AnimalShelter.Model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDonationRepository extends JpaRepository<Donation, Integer> {

}
