package pg.vt.sec04;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pg.vt.sec04.aggregatorservice.AggregatorService;
import pg.vt.sec04.aggregatorservice.ProductDTO;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

public class AggregatorDemo {
    private static final Logger log = LoggerFactory.getLogger(AggregatorDemo.class);

    public static void main(String[] args) {
        var executors = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executors);

        var futures = IntStream.rangeClosed(1,50)
                .mapToObj(id->executors.submit(()->aggregator.getProductDto(id)))
                .toList();
        var list = futures.stream().map(AggregatorDemo::toProductDto).toList();
        log.info("List: {}", list);
    }
    private static ProductDTO toProductDto(Future<ProductDTO> productDTOFuture){
        try {
            return productDTOFuture.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
