package base.ecommerce.service.services;

import base.ecommerce.payload.entity_dto.OrderDTO;

public interface OrderService {
    OrderDTO placeOrder(String email, Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage);
}
