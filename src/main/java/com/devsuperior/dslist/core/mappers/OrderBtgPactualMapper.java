package com.devsuperior.dslist.core.mappers;

import com.devsuperior.dslist.adapter.out.dto.external.btg.OrderBtgPactualDTO;
import com.devsuperior.dslist.core.usecase.btg_challenge.input.OrderBtgPactualInput;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderBtgPactualMapper {

    OrderBtgPactualDTO toOrderBtgPactualDTO(OrderBtgPactualInput input);
}
