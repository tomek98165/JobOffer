package com.joboffers.domain.offer;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.StreamSupport;

public class OfferRepositoryTestImpl implements OfferRepository {





    Map<String,Offer> offers = new ConcurrentHashMap<>();

    @Override
    public boolean existsOfferByUrl(String url) {

        return offers.values()
                .stream()
                .filter(offer -> offer.url().equals(url))
                .count() == 1;
    }

    @Override
    public Optional<Offer> findById(String id) {
        return Optional.ofNullable(offers.get(id));
    }

    @Override
    public <S extends Offer> S save(S entity) {
        if(existsOfferByUrl(entity.url()))
            throw new DuplicateKeyException("Offer offerUrl: " + entity.url() + " exist");
        UUID id = UUID.randomUUID();
        Offer newOffer = Offer.builder()
                .url(entity.url())
                .salary(entity.salary())
                .company(entity.company())
                .position(entity.position())
                .id(id.toString())
                .build();

        offers.put(newOffer.id(), newOffer);
        return (S) newOffer;
    }

    @Override
    public List<Offer> findAll() {
        return offers.values().stream().toList();
    }

    @Override
    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {

        return StreamSupport.stream(entities.spliterator(), false)
                .filter(offer -> !existsOfferByUrl(offer.url()))
                .map(this::save)
                .toList();
//        List<Offer> newOffers = entities.stream()
//                .filter(offer -> !existsOfferByUrl(offer.offerUrl()))
//                .toList();
//        return newOffers.stream()
//                .map(this::save)
//                .toList();
    }

    @Override
    public <S extends Offer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends Offer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Offer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Offer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Offer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Offer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Offer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

//    @Override
//    public <S extends Offer> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }

//    @Override
//    public Optional<Offer> findById(String s) {
//        return Optional.empty();
//    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

//    @Override
//    public List<Offer> findAll() {
//        return null;
//    }

    @Override
    public List<Offer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Offer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Offer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Offer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Offer> findAll(Pageable pageable) {
        return null;
    }
}
