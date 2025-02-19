//package com.example.ChakriHub.generic.controller;
//
//import com.example.ChakriHub.generic.payload.request.GenericIdsDto;
//import com.example.ChakriHub.generic.payload.request.IDto;
//import com.example.ChakriHub.generic.payload.response.BaseResponseDto;
//import com.example.ChakriHub.generic.payload.response.MessageResponse;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//
//public interface IController<D extends IDto> {
//
////    PageData getAll(Boolean isActive, Pageable pageable);
//
//    <T extends BaseResponseDto>T getSingle(Long id);
//
//    ResponseEntity<List<Object>> getAll();
//
//    ResponseEntity<MessageResponse> create(D d);
//
//    ResponseEntity<MessageResponse> update(D d, Long id);
//
//    ResponseEntity<MessageResponse> updateActiveStatus(@PathVariable Long id, Boolean isActive);
//    ResponseEntity<MessageResponse> batchActiveStatus(GenericIdsDto ids);
//}