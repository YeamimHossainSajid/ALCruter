//package com.example.ChakriHub.generic.service;
//
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.CollectionUtils;
//
//import java.lang.reflect.Field;
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.ConcurrentMap;
//import java.util.concurrent.atomic.AtomicReference;
//import java.util.function.Function;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public abstract class AbstractService<E extends BaseEntity, D extends IDto, S extends SDto> implements IService<E, D, S> {
//
//    protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);
//    static final Pattern CAPITAL_LETTER_PATTERN = Pattern.compile("([A-Z])");
//    protected final AbstractRepository<E> repository;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public E create(D d) {
//        checkStringSize(d);
//        validateClientData(d, null);
//        return saveItem(convertToEntity(d));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public E update(D d, Long id) {
//        E e = findById(id);
//        checkStringSize(d);
//        validateClientData(d, e);
//        E e1 = updateEntity(d, e);
//        if (Objects.equals(e.getUpdatedTime(), e1.getUpdatedTime())) {
//            e1.setUpdatedTime(LocalDateTime.now());
//        }
//        return saveItem(e1);
//    }
//
//    @Override
//    public <T extends BaseResponseDto> T getSingle(Long id) {
//        E entity = findById(id);
//        return convertToResponseDto(entity);
//    }
//
//    @Override
//    public E findById(Long id) {
//        return repository.findById(id).orElseThrow(() -> RmsServerException.notFound(ErrorId.NOT_FOUND));
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public E updateActiveStatus(Long id, Boolean isActive) {
//        E e = findById(id);
//        if (e.getIsActive() == isActive) {
//            throw RmsServerException.badRequest(ErrorId.ONLY_TOGGLE_VALUE_ACCEPTED);
//        }
//
//        e.setIsActive(isActive);
//        return saveItem(e);
//    }
//
//    @Override
//    public E saveItem(E entity) {
//        try {
//            return repository.save(entity);
//        } catch (Exception e) {
//            LOGGER.error("Save failed for entity {}", entity.getClass().getSimpleName());
//            LOGGER.error("Error message: {}", e.getMessage());
//            throw RmsServerException.dataSaveException(Helper.createDynamicCode(ErrorId.DATA_NOT_SAVED_DYNAMIC, entity.getClass().getSimpleName()));
//        }
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public List<E> saveItemList(List<E> entityList) {
//        try {
//            if (CollectionUtils.isEmpty(entityList)) {
//                return entityList;
//            }
//            return repository.saveAll(entityList);
//        } catch (Exception e) {
//            String entityName = entityList.get(0).getClass().getSimpleName();
//            LOGGER.error("Save failed for entity {}", entityName);
//            LOGGER.error("Error message: {}", e.getMessage());
//            throw RmsServerException.dataSaveException(Helper.createDynamicCode(ErrorId.DATA_NOT_SAVED_DYNAMIC,
//                    entityName));
//        }
//    }
//
//    @Override
//    public List<E> findAllByIds(Collection<Long> ids) {
//        return repository.findAllByIdIn(ids);
//    }
//
//    protected abstract <T extends BaseResponseDto> T convertToResponseDto(E e);
//
//    protected abstract E convertToEntity(D d);
//
//    protected abstract E updateEntity(D d, E entity);
//
//    @Override
//    public ResponseEntity<List<Object>> getAll() {
//       return new ResponseEntity<>(repository.findAllByIsActiveTrue().stream().map(this::convertToResponseDto).toList(), HttpStatus.OK);
//    }
//
//    protected PageData convertToPageData(Page<E> pagedData, Pageable pageable) {
//        AtomicReference<Long> sl = new AtomicReference<>(1L);
//        List<Object> models = pagedData.getContent().stream().map(e -> {
//                    BaseResponseDto baseResponseDto = convertToResponseDto(e);
//                    return baseResponseDto.setSl(sl.getAndSet(sl.get()+1));
//                })
//                .collect(Collectors.toList());
//
//        return PageData.builder()
//                .model(models)
//                .totalPages(pagedData.getTotalPages())
//                .totalElements(pagedData.getTotalElements())
//                .currentPage(pageable.getPageNumber() + 1)
//                .build();
//    }
//
//    @Override
//    public PageData search(S searchDto, Pageable pageable) {
//        Specification<E> amrSpecification = buildSpecification(searchDto);
//        if (Objects.isNull(amrSpecification)) {
//            amrSpecification = getNewSpecification(searchDto);
//        }
//        Page<E> pagedData = repository.findAll(amrSpecification, pageable);
//        return convertToPageData(pagedData, pageable);
//    }
//
//    @Override
//    @Transactional
//    public List<E> batchActiveStatus(GenericIdsDto ids) {
//        List<E> allByIdIn = repository.findAllByIdIn(ids.getIds());
//        if (CollectionUtils.isEmpty(allByIdIn)) {
//            throw RmsServerException.badRequest(Helper.createDynamicCode(ErrorId.NOT_FOUND_DYNAMIC,
//                    "Given Ids "));
//        }
//        try {
//            allByIdIn.forEach(e -> e.setIsActive(false));
//            return repository.saveAll(allByIdIn);
//        } catch (Exception e) {
//            throw RmsServerException.internalServerException(ErrorId.FAILED_TO_CHANGE_BATCH_ACTIVE_STATUS);
//        }
//    }
//
//    abstract protected Specification<E> buildSpecification(S searchDto);
//
//    private Specification<E> getNewSpecification(S searchDto) {
//        return new CustomSpecification<E>()
//                .active(Objects.nonNull(searchDto.getIsActive()) ? searchDto.getIsActive() :
//                        ApplicationConstant.ACTIVE_TRUE, ApplicationConstant.IS_ACTIVE_FIELD);
//    }
//
//
//    protected void checkStringSize(D dto) {
//        Field[] declaredFields = dto.getClass().getDeclaredFields();
//        StringBuilder errorFieldNames = new StringBuilder();
//
//        for (Field field : declaredFields) {
//            if (field.getType().isAssignableFrom(String.class)) {
//                field.setAccessible(true);
//                try {
//                    String s = (String) field.get(dto);
//                    if (Objects.nonNull(s) && s.length() > 255) {
//                        errorFieldNames.append(spaceAndCapitalizeFirstLetter(field.getName())).append(" ");
//                    }
//                } catch (IllegalAccessException e) {
//                    throw RmsServerException.internalServerException(
//                            Helper.createDynamicCode(ErrorId.ILLEGAL_ACCESS, e.getMessage()));
//                }
//            }
//        }
//
//        if (errorFieldNames.length() > 0) {
//            throw RmsServerException.badRequest(
//                    Helper.createDynamicCode(ErrorId.STRING_EXCEED_LIMIT, errorFieldNames.toString()));
//        }
//    }
//
//
//    private String spaceAndCapitalizeFirstLetter(String text) {
//        if (text == null || text.isEmpty()) {
//            return text;
//        }
//
//        StringBuilder result = new StringBuilder();
//        result.append(text.charAt(0));
//
//        for (int i = 1; i < text.length(); i++) {
//            char currentChar = text.charAt(i);
//
//            if (Character.isUpperCase(currentChar)) {
//                result.append(' ').append(currentChar);
//            } else {
//                result.append(currentChar);
//            }
//        }
//
//        return result.toString();
//    }
//
//    private ConcurrentMap<Long, User> getUserMap(Collection<Long> ids) {
//        return Helper.getAllUserByIds(ids)
//                .stream().collect(Collectors.toConcurrentMap(User::getId, Function.identity(), (a, b) -> a));
//    }
//}