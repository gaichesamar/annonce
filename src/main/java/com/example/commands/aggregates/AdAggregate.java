package com.example.commands.aggregates;
import com.example.commonApi.commands.CreateAdCommand;
import com.example.commonApi.commands.DeleteAdCommand;
import com.example.commonApi.commands.ReserveAdCommand;
import com.example.commonApi.commands.UpdateAdCommand;
import com.example.commonApi.enums.AdStatus;
import com.example.commonApi.events.*;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.ApplyMore;
import org.axonframework.spring.stereotype.Aggregate;
import java.math.BigDecimal;
import java.time.LocalDate;


@Aggregate
public class AdAggregate {
    @AggregateIdentifier
    private String adId;
    private String description;
    private LocalDate dateDep;
    private LocalDate dateArr;
    private String heureDep;
    private String heureArr;
    private BigDecimal prixPlace;
    private String lieuDep;
    private int nbrPlace;
    private String lieuArr;
    private boolean animaux_de_companie;
    private boolean cigarette ;
    private boolean aller_retour;
    private AdStatus status;
    private String desc;

    public AdAggregate() {
        //Required by Axon
    }
    @CommandHandler
    public AdAggregate(CreateAdCommand createAdCommand) {
        //Required by Axon
        AggregateLifecycle.apply(new AdCreatedEvent(
                createAdCommand.getId(),
                createAdCommand.getDescription(),
                createAdCommand.getDateDep(),
                createAdCommand.getDateArr(),
                createAdCommand.getHeureDep(),
                createAdCommand.getHeureArr(),
                createAdCommand.getPrixPlace(),
                createAdCommand.getLieuDep(),
                createAdCommand.getNbrPlace(),
                createAdCommand.getLieuArr(),
                createAdCommand.isCigarette(),
                createAdCommand.isAller_retour(),
                createAdCommand.isAnimaux_de_companie(),
                createAdCommand.getDescriptionvoyage(),
                AdStatus.CREATED));
    }
    @EventSourcingHandler
    public void on(AdCreatedEvent event)
    {
        this.adId=event.getId();
        this.description=event.getDescription();
        this.dateDep=event.getDateDep();
        this.dateArr=event.getDateArr();
        this.heureDep=event.getHeureDep();
        this.heureArr=event.getHeureArr();
        this.prixPlace=event.getPrixPlace();
        this.lieuDep=event.getLieuDep();
        this.lieuArr=event.getLieuArr();
        this.nbrPlace=event.getNbrPlace();
        this.cigarette=event.isCigarette();
        this.aller_retour=event.isAller_retour();
        this.animaux_de_companie=event.isAnimaux_de_companie();
        this.status= AdStatus.CREATED;
    AggregateLifecycle.apply(new AdValidatedEvent(
            event.getId(),
            AdStatus.VALIDATED
    ));
    }
    @EventSourcingHandler
    public void on(AdValidatedEvent event){
        this.status=event.getStatus();
    }
    @CommandHandler
    public void handle(UpdateAdCommand updateAdCommand) {
        AggregateLifecycle.apply(new AdUpdatedEvent(
                updateAdCommand.getId(),
                updateAdCommand.getDescription(),
                updateAdCommand.getDateDep(),
                updateAdCommand.getDateArr(),
                updateAdCommand.getHeureDep(),
                updateAdCommand.getHeureArr(),
                updateAdCommand.getPrixPlace(),
                updateAdCommand.getLieuDep(),
                updateAdCommand.getNbrPlace(),
                updateAdCommand.getLieuArr(),
                updateAdCommand.isCigarette(),
                updateAdCommand.isAller_retour(),
                updateAdCommand.isAnimaux_de_companie(),
                AdStatus.UPDATED
        ));

}
    @EventSourcingHandler
    public void on(AdUpdatedEvent event){
        this.adId=event.getId();
        this.description=event.getDescription();
        this.dateDep=event.getDateDep();
        this.dateArr=event.getDateArr();
        this.heureDep=event.getHeureDep();
        this.heureArr=event.getHeureArr();
        this.prixPlace=event.getPrixPlace();
        this.lieuDep=event.getLieuDep();
        this.lieuArr=event.getLieuArr();
        this.nbrPlace=event.getNbrPlace();
        this.cigarette=event.isCigarette();
        this.aller_retour=event.isAller_retour();
        this.animaux_de_companie=event.isAnimaux_de_companie();
    this.status= AdStatus.UPDATED;
    AggregateLifecycle.apply(new AdValidatedEvent(
            event.getId(),
            AdStatus.VALIDATED
    ));
    }
    @CommandHandler
    public void handle(DeleteAdCommand command) {
        if (this.status == AdStatus.DELETED) {
            throw new IllegalStateException("Ad is already deleted");
        }
        AggregateLifecycle.apply(new AdDeletedEvent(command.getId()));
    }
    @EventSourcingHandler
    public void on(AdDeletedEvent event) {

        AggregateLifecycle.markDeleted();
    }
    @CommandHandler
    public void handle(ReserveAdCommand command) {
        if (this.status != AdStatus.VALIDATED) {
            throw new IllegalStateException("Ad is not validated");
        }
        if (command.getNbrPlace() > this.nbrPlace) {
            throw new IllegalStateException("Not enough seats available");
        }
        AggregateLifecycle.apply(new AdReservedEvent(
                command.getId(),
                command.getNbrPlace()
        ));
    }
    @EventSourcingHandler
    public void on(AdReservedEvent event) {
        this.status = AdStatus.RESERVED;
        this.nbrPlace -= event.getNbrPlace();
    }


}
