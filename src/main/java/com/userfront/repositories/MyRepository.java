package com.userfront.repositories;

import com.userfront.models.Card;
import com.userfront.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public class MyRepository implements CrudRepository<Object, Long> {


    public Card findByCode(int cardCode){
        // TODO: query the hibernate and find the card by code
    }

    void deleteByCode(int cardCode){

    }

    User findByUsername(String username){

    }

    User findByUsernameAndEmail(String username, String email){

    }

    User findByEmail(String email){

    }

    @Override
    public <S> S save(S entity) {
        return null;
    }

    @Override
    public <S> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Object> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Object> findAll() {
        return null;
    }

    @Override
    public Iterable<Object> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<?> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
