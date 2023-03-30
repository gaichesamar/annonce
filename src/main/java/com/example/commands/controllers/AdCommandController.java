package com.example.commands.controllers;

import com.example.commonApi.commands.CreateAdCommand;
import com.example.commonApi.commands.DeleteAdCommand;
import com.example.commonApi.commands.ReserveAdCommand;
import com.example.commonApi.commands.UpdateAdCommand;
import com.example.commonApi.dtos.UpdateAdRequestDTO;
import com.example.commonApi.dtos.createAdRequestDTO;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/commands/ad")
@CrossOrigin(origins = "*")
public class AdCommandController {
    private EventStore eventStore;
    private CommandGateway commandGateway;


    @PostMapping(path = "/create")
    public CompletableFuture<String> createAd(@RequestBody createAdRequestDTO request) {
        CompletableFuture<String> commandResponse = commandGateway.send(new CreateAdCommand(
                UUID.randomUUID().toString(),
                request.getDescription(),
                request.getDateDep(),
                request.getDateArr(),
                request.getHeureDep(),
                request.getHeureArr(),
                request.getPrixPlace(),
                request.getLieuDep(),
                request.getNbrPlace(),
                request.getLieuArr(),
                request.isCigarette(),
                request.isAller_retour(),
                request.isAnimaux_de_companie(),
                request.getDescriptionvoyage()
        ));
        return commandResponse;
    }

    @PutMapping(path = "/update/{AdId}")
    public CompletableFuture<String> updateAd(@PathVariable(name = "AdId", required = false) String id, @RequestBody UpdateAdRequestDTO request) {
        CompletableFuture<String> commandResponse = commandGateway.send(new UpdateAdCommand(
                id,
                request.getDescription(),
                request.getDateDep(),
                request.getDateArr(),
                request.getHeureDep(),
                request.getHeureArr(),
                request.getPrixPlace(),
                request.getLieuDep(),
                request.getNbrPlace(),
                request.getLieuArr(),
                request.isCigarette(),
                request.isAller_retour(),
                request.isAnimaux_de_companie()
        ));
        return commandResponse.exceptionally(ex -> {
            throw new RuntimeException("Failed to update ad data:" + ex.getMessage());
        });
    }

    @DeleteMapping(path = "/delete/{AdId}")
    public ResponseEntity<String> DeleteAd(@PathVariable String AdId) {
        try {
            commandGateway.send(new DeleteAdCommand(AdId));
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }

    @ExceptionHandler()
    public ResponseEntity<String> exceptionHandler(Exception exception) {
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR
        );
        return entity;
    }

    @GetMapping("/eventStore/{adId}")
    public java.util.stream.Stream eventStore(@PathVariable String adId) {
        return eventStore.readEvents(adId).asStream();
    }

    @PostMapping("/ads/{id}/reserve")
    public CompletableFuture<String> reserveAd(@PathVariable String id, @RequestParam int nbPlaces) {
        ReserveAdCommand reserveAdCommand = new ReserveAdCommand(id, nbPlaces);
        return commandGateway.send(reserveAdCommand);
    }
}
