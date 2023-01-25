package org.based;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {

    List<BuddyInfo> findByName(String lastName);
    List<BuddyInfo> findByPhone(String phone);

    BuddyInfo findById(long id);
}