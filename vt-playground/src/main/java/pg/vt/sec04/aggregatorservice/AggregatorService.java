package pg.vt.sec04.aggregatorservice;

import pg.vt.sec04.executorservice.Client;

import java.util.concurrent.ExecutorService;

public class AggregatorService {
    private final ExecutorService executorService;

    public AggregatorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ProductDTO getProductDto(int id) throws Exception {
        var product = executorService.submit(()-> Client.getProduct(id));
        var rating = executorService.submit(()-> Client.getRating(id));
        return new ProductDTO(id, product.get(), rating.get());
    }
}
