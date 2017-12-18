package edu.hm.sweI.eam.controller;

import edu.hm.sweI.eam.entities.Tag;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Matthias Rude on 17.12.2017.
 * Email: Matze.development@gmail.com
 */

interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByTag(String tag);
}
