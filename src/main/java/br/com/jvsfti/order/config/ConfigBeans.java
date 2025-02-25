package br.com.jvsfti.order.config;

import br.com.jvsfti.order.adapter.outbound.repository.GetOrderAdapter;
import br.com.jvsfti.order.adapter.outbound.repository.SaveOrderAdapter;
import br.com.jvsfti.order.adapter.outbound.repository.SaveOrderItemsAdapter;
import br.com.jvsfti.order.domain.ports.inbound.ProcessOrderUserCasePort;
import br.com.jvsfti.order.domain.ports.inbound.SearchOrderUseCasePort;
import br.com.jvsfti.order.domain.usecase.ProcessOrderUseCase;
import br.com.jvsfti.order.domain.usecase.SearchOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeans {

    @Bean
    public ProcessOrderUserCasePort createOrderUserCasePort(GetOrderAdapter getOrderAdapter, SaveOrderAdapter saveOrderAdapter, SaveOrderItemsAdapter saveOrderItemsAdapter) {
        return new ProcessOrderUseCase(getOrderAdapter, saveOrderAdapter, saveOrderItemsAdapter);
    }

    @Bean
    public SearchOrderUseCasePort searchOrderUseCasePort(GetOrderAdapter getOrderAdapter){
        return new SearchOrderUseCase(getOrderAdapter);
    }

}
