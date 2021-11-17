package hello.advanced.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식
 *
 * Context 는 Strategy 인터페이스에만 의존한다는 점이다. 덕분에 Strategy 의
 * 구현체를 변경하거나 새로 만들어도 Context 코드에는 영향을 주지 않는다.
 * --> 알고리즘을 사용하는 클라이언트와 독립적으로 알고리즘을 변경할 수 있다.
 */
@Slf4j
public class ContextV1 {

    private Strategy strategy;

    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call(); //위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);

    }
}
