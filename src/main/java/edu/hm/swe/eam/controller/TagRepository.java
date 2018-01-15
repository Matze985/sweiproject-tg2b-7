package edu.hm.swe.eam.controller;

import edu.hm.swe.eam.entities.Tag;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Matthias Rude on 17.12.2017.
 * Email: Matze.development@gmail.com
 */

interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByTag(String tag);
}
