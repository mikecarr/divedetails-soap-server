package net.mikecarr.divedetailssoapserver;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DiveDetailsRepository extends CrudRepository<DiveDetail, Long>{
	
	//List<DiveDetail> findById(Integer id);

}
