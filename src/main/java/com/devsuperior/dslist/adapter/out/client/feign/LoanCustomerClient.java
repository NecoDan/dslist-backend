package com.devsuperior.dslist.adapter.out.client.feign;

import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanInputDTO;
import com.devsuperior.dslist.adapter.out.dto.external.CustomerLoanOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "loan-customer-client", url = "${loan.customer.service.url}")
public interface LoanCustomerClient {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    CustomerLoanOutputDTO checkCustomerLoanEligibility(@RequestBody CustomerLoanInputDTO request);
}
