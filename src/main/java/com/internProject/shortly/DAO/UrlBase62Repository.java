package com.internProject.shortly.DAO;

import com.internProject.shortly.entity.UrlBase62;
import org.springframework.data.repository.CrudRepository;

public interface UrlBase62Repository extends CrudRepository<UrlBase62, Long> {

    UrlBase62 getBySequenceId(long sequenceId);
}

