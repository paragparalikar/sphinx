package com.sphinx.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccessRequestService {

	private final AccessRequestRepository accessRequestRepository;
	
	public Page<AccessRequest> findAll(@NonNull Specification<AccessRequest> spec, @NonNull Pageable pageable) {
		return accessRequestRepository.findAll(spec, pageable);
	}
	
	public AccessRequest save(@NonNull AccessRequest request) {
		return accessRequestRepository.save(request); 
	}
	
	public AccessRequest findById(@NonNull Long id) {
		return accessRequestRepository.findById(id).orElse(null);
	}
	
	public void cancelById(@NonNull Long id) {
		final AccessRequest accessRequest = findById(id);
		if(null != accessRequest) {
			accessRequest.setStatus(AccessRequestStatus.CANCELLED);
			accessRequestRepository.save(accessRequest);
		}
	}
}
