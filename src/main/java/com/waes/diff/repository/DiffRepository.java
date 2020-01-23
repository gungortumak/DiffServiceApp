package com.waes.diff.repository;

import com.waes.diff.domain.Diff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Data Repo to retrieve diffs
 */
@RepositoryRestResource(exported = false)
public interface DiffRepository extends CrudRepository<Diff,Integer> {
}