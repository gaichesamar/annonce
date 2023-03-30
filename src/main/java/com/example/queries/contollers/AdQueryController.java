package com.example.queries.contollers;

import com.example.commonApi.query.GetAdsBy;
import com.example.commonApi.query.GetAllAdsQuery;
import com.example.commonApi.query.GetAdByIdQuery;
import com.example.queries.entities.Ad;
import com.example.queries.services.AdServiceHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping(path = "/query/ads")
public class AdQueryController {
    private QueryGateway queryGateway;
    private AdServiceHandler adServicer;

    @GetMapping("/GetAllAds")
    public List<Ad> AdList() {
        List<Ad> reponse=    queryGateway.query(new GetAllAdsQuery(), ResponseTypes.multipleInstancesOf(Ad.class)).join();
        return reponse;
    }
    @GetMapping("/GetAdById/{id}")
    public Ad getAd(@PathVariable String id){
        return  queryGateway.query(new GetAdByIdQuery(id),ResponseTypes.instanceOf(Ad.class)).join();
    }
    @GetMapping("/GetAdsBy/{lieuDep}/{lieuArr}/{nbrPlace}/{dateDep}")
    public List<Ad> AdListSearch(@PathVariable String lieuDep,@PathVariable String lieuArr, @PathVariable int nbrPlace, @PathVariable String dateDep ) {
        List<Ad> reponse=queryGateway.query(new GetAdsBy(lieuDep,lieuArr,nbrPlace,dateDep), ResponseTypes.multipleInstancesOf(Ad.class)).join();
        return reponse;
    }
    @GetMapping("/user/{description}")
    public List<Ad> getAdsByUserId(@PathVariable String description) {
        return  adServicer.getAdsByUserId(description);
    }
}
