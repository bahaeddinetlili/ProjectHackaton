package com.example.Hackaton;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SponsorService {
    private final SponsorRepository sponsorRepository;

    @Autowired
    public SponsorService(SponsorRepository sponsorRepository) {
        this.sponsorRepository = sponsorRepository;
    }


    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public Sponsor getSponsorById(Integer id) {
        Optional<Sponsor> optionalSponsor = sponsorRepository.findById(id);
        return optionalSponsor.orElse(null);
    }

    public Sponsor createSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    public Sponsor updateSponsor(Integer id, Sponsor sponsor) {
        if (sponsorRepository.existsById(id)) {
            sponsor.setIdSponsor(id);
            return sponsorRepository.save(sponsor);
        }
        return null;
    }

    public void deleteSponsor(Integer id) {
        sponsorRepository.deleteById(id);
    }


    public List<Sponsor> getSponsor() {
        return sponsorRepository.findAll();
    }




}
