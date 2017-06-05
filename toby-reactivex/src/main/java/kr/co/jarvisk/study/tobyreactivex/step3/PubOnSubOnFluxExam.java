package kr.co.jarvisk.study.tobyreactivex.step3;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
public class PubOnSubOnFluxExam {

    public static void main(String[] args) {
        Flux.range(1, 10)
                .publishOn(Schedulers.newSingle("pubOn"))
                .log()
                .subscribeOn(Schedulers.newSingle("subOn"))
                .subscribe(integer -> log.info(String.valueOf(integer)));

        log.info("exit");
    }

}
