package com.paul.startclass.repository;

import com.paul.startclass.models.BIblock;
import org.springframework.data.repository.CrudRepository;

public interface BlockRepository extends CrudRepository<BIblock, Integer> {
}
