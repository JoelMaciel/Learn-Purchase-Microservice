package com.joel.purchase.api.consumers;

import com.joel.purchase.domain.dtos.PurchaseEventDTO;
import com.joel.purchase.domain.dtos.PurchaseRequestDTO;
import com.joel.purchase.domain.services.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PurchaseConsumer {

    private final PurchaseService purchaseService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${learn.broker.queue.purchaseCommandQueue.name}", durable = "true"),
            exchange = @Exchange(value = "${learn.broker.exchange.purchaseCommandExchange}",
                    type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${learn.broker.key.purchaseCommandKey}"
    ))
    public void listenPurchaseEvent(@Payload PurchaseEventDTO purchaseEventDTO) {
        purchaseService.save(toPurchaseRequestDTO(purchaseEventDTO));
    }

    private PurchaseRequestDTO toPurchaseRequestDTO(PurchaseEventDTO purchaseEventDTO) {
        return PurchaseRequestDTO.builder()
                .courseId(purchaseEventDTO.getCourseId())
                .userId(purchaseEventDTO.getUserId())
                .title(purchaseEventDTO.getTitle())
                .price(purchaseEventDTO.getPrice())
                .build();
    }
}
