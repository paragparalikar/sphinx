package com.sphinx.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {

	private final RequestRepository accessRequestRepository;
	
	public Page<Request> findAll(@NonNull Specification<Request> spec, @NonNull Pageable pageable) {
		return accessRequestRepository.findAll(spec, pageable);
	}
	
	public Request save(@NonNull Request request) {
		return accessRequestRepository.save(request); 
	}
	
	public Request findById(@NonNull Long id) {
		return accessRequestRepository.findById(id).orElse(null);
	}
	
	public void cancelById(@NonNull Long id) {
		final Request accessRequest = findById(id);
		if(null != accessRequest) {
			accessRequest.setStatus(RequestStatus.CANCELLED);
			accessRequestRepository.save(accessRequest);
		}
	}
}
