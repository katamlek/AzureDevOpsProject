package com.katamlek.training.skillomatic.helper;

public interface EntityDtoConverter<E extends BaseEntity, D extends BaseDto> {

    D convert(E entity);

    E convert(D d);

}
