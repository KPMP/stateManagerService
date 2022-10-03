package org.kpmp.dataManager;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataManagerRepository extends CrudRepository<DluPackageInventory, Integer> {

   DluPackageInventory findByDluPackageId(String dluPackageId);

}
