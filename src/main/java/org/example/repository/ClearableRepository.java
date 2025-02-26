package org.example.repository;

public interface ClearableRepository<T> extends Repository<T> {
    void clear();
}

