package com.example.Hackaton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HackatonService {
    private final HackatonRepository hackatonRepository;

    private final SponsorService sponsorService;

    private  final SponsorRepository sponsorRepository;
    @Autowired
    public HackatonService(HackatonRepository hackatonRepository, SponsorService sponsorService, SponsorRepository sponsorRepository) {
        this.hackatonRepository = hackatonRepository;
        this.sponsorService = sponsorService;
        this.sponsorRepository = sponsorRepository;
    }

    public List<Hackaton> getAllHackatons() {
        return hackatonRepository.findAll();
    }

    public Hackaton getHackatonById(Integer id) {
        Optional<Hackaton> optionalHackaton = hackatonRepository.findById(id);
        return optionalHackaton.orElse(null);
    }

    public Hackaton createHackaton(Hackaton hackaton) {
        return hackatonRepository.save(hackaton);
    }

    public Hackaton updateHackaton(Integer id, Hackaton hackaton) {
        if (hackatonRepository.existsById(id)) {
            hackaton.setIdHackaton(id);
            return hackatonRepository.save(hackaton);
        }
        return null;
    }

    public void deleteHackaton(Integer id) {
        hackatonRepository.deleteById(id);
    }

    public Hackaton addSponsorToHackaton(Integer hackatonId, Sponsor sponsor) {
        Optional<Hackaton> optionalHackaton = hackatonRepository.findById(hackatonId);
        if (optionalHackaton.isPresent()) {
            Hackaton hackaton = optionalHackaton.get();
            Set<Sponsor> sponsors = hackaton.getSponsors();
            sponsors.add(sponsor);
            hackaton.setSponsors(sponsors);
            return hackatonRepository.save(hackaton);
        }
        return null;
    }




    public Hackaton addSponsorToHackaton(Integer hackatonId, Integer sponsorId) {
        Optional<Hackaton> optionalHackaton = hackatonRepository.findById(hackatonId);
        if (optionalHackaton.isPresent()) {
            Hackaton hackaton = optionalHackaton.get();
            Set<Sponsor> sponsors = hackaton.getSponsors();

            // Check if the sponsors set is null, and if so, create a new HashSet
            if (sponsors == null) {
                sponsors = new HashSet<>();
            }

            // Create a new Sponsor entity and add it to the sponsors set
            Sponsor sponsor = sponsorService.getSponsorById(sponsorId);
            if (sponsor != null) {
                sponsors.add(sponsor);

                // Update the Hackaton entity with the new sponsors set
                hackaton.setSponsors(sponsors);

                // Save the updated Hackaton to the repository
                return hackatonRepository.save(hackaton);
            }
        }
        return null;
    }


    public Hackaton removeSponsorFromHackaton(Integer hackatonId, Integer sponsorId) {
        Optional<Hackaton> optionalHackaton = hackatonRepository.findById(hackatonId);
        Optional<Sponsor> optionalSponsor = sponsorRepository.findById(sponsorId);

        if (optionalHackaton.isPresent() && optionalSponsor.isPresent()) {
            Hackaton hackaton = optionalHackaton.get();
            Sponsor sponsor = optionalSponsor.get();

            hackaton.removeSponsor(sponsor);
            return hackatonRepository.save(hackaton);
        }

        return null; // Handle the case where either the Hackaton or Sponsor is not found
    }

    public Set<Sponsor> getSponsorsForHackaton(Integer hackatonId) {
        Optional<Hackaton> optionalHackaton = hackatonRepository.findById(hackatonId);

        if (optionalHackaton.isPresent()) {
            Hackaton hackaton = optionalHackaton.get();
            return hackaton.getSponsors();
        }
        return null; // Handle not found scenarios
    }

    public Hackaton getHackatonWithSponsors(Integer hackatonId) {
        Optional<Hackaton> optionalHackaton = hackatonRepository.findHackatonWithSponsors(hackatonId);
        return optionalHackaton.orElse(null);
    }


}
