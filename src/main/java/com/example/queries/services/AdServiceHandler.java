package com.example.queries.services;
import com.example.commonApi.events.AdCreatedEvent;
import com.example.commonApi.events.AdDeletedEvent;
import com.example.commonApi.events.AdUpdatedEvent;
import com.example.commonApi.events.AdValidatedEvent;
import com.example.commonApi.query.GetAllAdsQuery;
import com.example.commonApi.query.GetAdByIdQuery;
import com.example.queries.entities.Ad;
import com.example.queries.repositories.AdRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Transactional
@Service
public class AdServiceHandler {
    private AdRepository adRepository;

    @EventHandler
    public void on(AdCreatedEvent event){
        Ad ad = new Ad();
        ad.setId(event.getId());
        ad.setDescription(event.getDescription());
        ad.setDateDep(event.getDateDep());
        ad.setDateArr(event.getDateArr());
        ad.setHeureDep(event.getHeureDep());
        ad.setHeureArr(event.getHeureArr());
        ad.setPrixPlace(event.getPrixPlace());
        ad.setLieuDep(event.getLieuDep());
        ad.setLieuArr(event.getLieuArr());
        ad.setNbrPlace(event.getNbrPlace());
        ad.setCigarette(event.isCigarette());
        ad.setAller_retour(event.isAller_retour());
        ad.setAnimaux_de_companie(event.isAnimaux_de_companie());
        ad.setStatus(event.getAdStatus());

        adRepository.save(ad);
    }
    @EventHandler
    public void on(AdValidatedEvent event){
        Ad ad = adRepository.getById(event.getId());
        ad.setStatus(event.getStatus());
        adRepository.save(ad);
    }
 @EventHandler
    public void on(AdUpdatedEvent event){
        Ad ad = adRepository.getById(event.getId());
        ad.setDescription(event.getDescription());
        ad.setDateDep(event.getDateDep());
        ad.setDateArr(event.getDateArr());
        ad.setHeureDep(event.getHeureDep());
        ad.setHeureArr(event.getHeureArr());
        ad.setPrixPlace(event.getPrixPlace());
        ad.setLieuDep(event.getLieuDep());
        ad.setLieuArr(event.getLieuArr());
        ad.setNbrPlace(event.getNbrPlace());
        ad.setCigarette(event.isCigarette());
        ad.setAller_retour(event.isAller_retour());
        ad.setAnimaux_de_companie(event.isAnimaux_de_companie());
        ad.setStatus(event.getAdStatus());
        adRepository.save(ad);
    }
    @EventHandler
    public void on(AdDeletedEvent event) {
        try {
            adRepository.deleteById(event.getId());
        } catch (EmptyResultDataAccessException ex) {
            throw new EntityNotFoundException("Ad not found with id: " + event.getId());
        }}
    @QueryHandler
    public List<Ad> on (GetAllAdsQuery query) {
        return adRepository.findAll();
    }
    @QueryHandler
    public Ad on (GetAdByIdQuery query) {
        return adRepository.findById(query.getId()).get();
    }
  public List<Ad> getAdsByUserId(String description) {
        return adRepository.findByDescription(description);
    }



}
