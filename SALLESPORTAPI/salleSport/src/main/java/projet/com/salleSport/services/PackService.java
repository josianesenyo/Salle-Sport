package projet.com.salleSport.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import projet.com.salleSport.models.Pack;
import projet.com.salleSport.repositories.PackRepository;
import projet.com.salleSport.exception.ResourceNotFoundException;


import java.util.List;

@Service
public class PackService {

    @Autowired
    private PackRepository packRepository;

    public Pack CreatePack(Pack pack){
        return packRepository.save(pack);
    }

    public List<Pack> getAllPacks(){
        return packRepository.findAll();
    }

    public Pack getPackById(Long id){
        return packRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pack non trouvé avec l'ID : " + id));
    }

    public List<Pack> searchPacksByOfferName(String offerName) {
        return packRepository.findByOfferName(offerName);
    }

    public Pack updatePack(Long id, Pack pack){
        Pack existingPack = getPackById(id);
        existingPack.setOfferName(pack.getOfferName());
        existingPack.setDurationMonths(pack.getDurationMonths());
        existingPack.setMonthlyPrice(pack.getMonthlyPrice());
        return packRepository.save(existingPack);
    }

    public void deletePack(Long id){
        packRepository.deleteById(id);
    }

}
