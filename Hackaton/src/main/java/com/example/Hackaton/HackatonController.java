package com.example.Hackaton;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/hackatons")
public class HackatonController {

    private final HackatonService hackatonService;
    private final SponsorService sponsorService;


    @Autowired
    public HackatonController(HackatonService hackatonService, SponsorService sponsorService) {
        this.hackatonService = hackatonService;
        this.sponsorService = sponsorService;
    }

    @GetMapping
    public List<Hackaton> getAllHackatons() {
        return hackatonService.getAllHackatons();
    }

    @GetMapping("/{id}")
    public Hackaton getHackatonById(@PathVariable int id) {
        return hackatonService.getHackatonById(id);
    }

    @PostMapping
    public Hackaton createHackaton(@RequestBody Hackaton hackaton) {
        return hackatonService.createHackaton(hackaton);
    }

    @PutMapping("/{id}")
    public Hackaton updateHackaton(@PathVariable int id, @RequestBody Hackaton hackaton) {
        return hackatonService.updateHackaton(id, hackaton);
    }

    @DeleteMapping("/{id}")
    public void deleteHackaton(@PathVariable int id) {
        hackatonService.deleteHackaton(id);
    }


    // Add a method to add a sponsor to a hackaton
    @PostMapping("/{hackatonId}/add-sponsor/{sponsorId}")
    public Hackaton addSponsorToHackaton(@PathVariable Integer hackatonId, @PathVariable Integer sponsorId) {
        return hackatonService.addSponsorToHackaton(hackatonId, sponsorId);
    }


    // Add a method to remove a sponsor from a hackaton
    @DeleteMapping("/{hackatonId}/removeSponsor/{sponsorId}")
    public Hackaton removeSponsorFromHackaton(
            @PathVariable int hackatonId,
            @PathVariable int sponsorId
    ) {
        return hackatonService.removeSponsorFromHackaton(hackatonId, sponsorId);
    }


    // This method will return the sponsors for a specific hackaton
    @GetMapping("/{hackatonId}/sponsors")
    public Set<Sponsor> getSponsorsForHackaton(@PathVariable int hackatonId) {
        return hackatonService.getSponsorsForHackaton(hackatonId);
    }





    @PostMapping("/{hackatonId}/create-sponsor")
    public Sponsor createSponsorForHackaton(@PathVariable Integer hackatonId, @RequestBody Sponsor sponsor) {
        Hackaton hackaton = hackatonService.getHackatonById(hackatonId);

        if (hackaton != null) {
            // Set the Hackaton for the sponsor
            sponsor.getHackatons().add(hackaton);

            // Save the sponsor
            return sponsorService.createSponsor(sponsor);
        } else {
            // Handle the case where the Hackaton with the specified ID does not exist.
            return null; // You can return an error response or handle it as needed.
        }
    }


    @GetMapping("/{id}/with-sponsors")
    public Hackaton getHackatonWithSponsors(@PathVariable int id) {
        return hackatonService.getHackatonWithSponsors(id);
    }



    @PutMapping("/{sponsorId}")
    public Sponsor updateSponsor(@PathVariable int sponsorId, @RequestBody Sponsor sponsor) {
        return sponsorService.updateSponsor(sponsorId, sponsor);
    }

    @DeleteMapping("/{sponsorId}")
    public void deleteSponsor(@PathVariable int sponsorId) {
        sponsorService.deleteSponsor(sponsorId);
    }


    @GetMapping("/allsponsor")
    public List<Sponsor> getAllSponsors() {
        return sponsorService.getAllSponsors();
    }
    @PostMapping("/addsponsor")
    public Sponsor createSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.createSponsor(sponsor);
    }

}



