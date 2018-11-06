package net.mikecarr.divedetailssoapserver;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import net.mikecarr.common.utils.date.DateUtils;
import net.mikecarr.dives.DiveDetails;
import net.mikecarr.dives.GetDiveDetailsRequest;
import net.mikecarr.dives.GetDiveDetailsResponse;
import net.mikecarr.dives.ObjectFactory;

@Endpoint
public class DiveDetailsEndpoint {

	@Autowired
	DiveDetailsRepository repository;

	@PayloadRoot(namespace = "http://mikecarr.net/dives", localPart = "GetDiveDetailsRequest")
	@ResponsePayload
	public GetDiveDetailsResponse processCourseDetailsRequest(@RequestPayload GetDiveDetailsRequest request) {
		GetDiveDetailsResponse response = new GetDiveDetailsResponse();

		Optional<DiveDetail> dbDiveDetails = repository.findById((long) request.getId());
		DiveDetail diveDetail = null;
		
		DiveDetails dive = new ObjectFactory().createDiveDetails();
		
		dbDiveDetails.ifPresent(existingDiveDetails -> {
		    String location = existingDiveDetails.getLocation();
		    Date startTime = existingDiveDetails.getStartTime();
		    Date endTime = existingDiveDetails.getEndTime();
		    Integer depth = existingDiveDetails.getDepth();
		    
		    dive.setLocation(location);
		    dive.setDepth(BigInteger.valueOf(depth));
		    dive.setStartTime(DateUtils.convertDateToXmlGregorian(startTime));
		    dive.setEndTime(DateUtils.convertDateToXmlGregorian(endTime));
		});
		
		
		
		
		response.setDiveDetails(dive);

		return response;
	}
}
