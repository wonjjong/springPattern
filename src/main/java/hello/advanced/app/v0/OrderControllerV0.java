package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

    private final OrderServiceV0 orderService;

    @GetMapping("/v0/request")
    //itemId를 queryParameter로 넘겼을 때 어떻게 자동으로 받아오는지
    //변수명과 똑같아야함.
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

}
