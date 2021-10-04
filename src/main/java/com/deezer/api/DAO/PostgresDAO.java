package com.deezer.api.DAO;

import java.math.BigInteger;
import java.util.ArrayList;

public interface PostgresDAO<T> {
    public abstract void insert(T adr);
    public abstract T getById(BigInteger id);
    public abstract void update(T adr);
    public abstract void delete(T adr);
    public abstract ArrayList<T> getAll();
    public abstract void deleteAll();
}
