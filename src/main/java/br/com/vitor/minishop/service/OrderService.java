package br.com.vitor.minishop.service;

import br.com.vitor.minishop.domain.dto.OrderResponse;
import br.com.vitor.minishop.domain.dto.OrderResponseWithItens;
import br.com.vitor.minishop.domain.dto.PaginatedSearchRequest;
import br.com.vitor.minishop.domain.dto.ResponseBase;
import br.com.vitor.minishop.domain.entity.Customer;
import br.com.vitor.minishop.domain.entity.CustomerOrder;
import br.com.vitor.minishop.repository.CustomerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerOrderRepository customerOrderRepository;

    public ResponseBase<Page<OrderResponse>> search(PaginatedSearchRequest searchRequest) {
        PageRequest pageRequest = PageRequest.of(searchRequest.getPage(), searchRequest.getSize());
        Page<CustomerOrder> customerOrderPage = customerOrderRepository.findAll(pageRequest);
        Page<OrderResponse> orderResponsePage = customerOrderPage
                .map(order -> {
                    int itemsQuantity = order.getOrderItems().size();
                    String customerName = customerName(order.getCustomer());
                    return new OrderResponse(order, customerName, itemsQuantity);
                });

        return new ResponseBase<>(orderResponsePage);
    }

    public ResponseBase<OrderResponseWithItens> searchById(Integer idCustomerOrder) {
        Optional<CustomerOrder> customerOrderOptional = customerOrderRepository.findById(idCustomerOrder);

        CustomerOrder customerOrder = customerOrderOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));

        OrderResponseWithItens orderResponseWithItens = new OrderResponseWithItens((customerOrder));

        return new ResponseBase<>(orderResponseWithItens);
    }

    private String customerName(Customer customer) {
        return customer.getFirstName() + " " + customer.getLastName();
    }
}
