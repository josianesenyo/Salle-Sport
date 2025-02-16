package projet.com.salleSport.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import projet.com.salleSport.models.Pack;
import projet.com.salleSport.repositories.PackRepository;
import projet.com.salleSport.services.PackService;

@RestController
@RequestMapping("/api/packs")
public class PackControllers {
    private final PackRepository packRepository;

    public PackControllers(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    @Autowired
    private PackService packService;
    
    @PostMapping()
    public ResponseEntity<Pack> createPack(@Valid @RequestBody Pack pack){
        Pack savedPack = packRepository.save(pack);
        return ResponseEntity.ok(savedPack);
    }

    @GetMapping()
    public ResponseEntity<List<Pack>> listPacks() {
        return ResponseEntity.ok(packService.getAllPacks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pack> getPackById(@PathVariable Long id) {
        return ResponseEntity.ok(packService.getPackById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Pack>> searchPackByOfferName(@RequestParam String offerName) {
        List<Pack> packs = packRepository.findByOfferName(offerName);
        if (packs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(packs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable Long id, @RequestBody Pack pack) {
        return ResponseEntity.ok(packService.updatePack(id, pack));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePack(@PathVariable Long id) {
        packService.deletePack(id);
        return ResponseEntity.noContent().build();
    }

}
