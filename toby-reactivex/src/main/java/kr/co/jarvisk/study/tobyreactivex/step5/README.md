 

## 스레드 컨텍스트 스위칭
- 스레드의 컨텍스트 스위칭은 CPU 비용이 많이 드는편이다.
- Blocking(Blocking IO등)이가 일어나면 상태에 들어가면 스레드는 즉시 컨텍스트 스위칭이 일어나고, Blocking 작업이 끝나면 스위칭이 다시 일어난다.
  - 즉, 한번 Blocking상태로 들어가면 컨텍스트 스위칭이 2번 일어난다.
- 스레드가 코어 갯수를 넘어가면 컨텍스트 스위칭이 많이 일어날것이다.
  - 스레드가 많다고 빨라지는것은 아님

## 외부의  여러 API를 호출 할 경우
외부의 여러  API를 호출하면서 스레드가 블럭되면서, 스레드가 놀고 있는 현상이 되고, CPU는 놀고 있는데, 스레드가 꽉차서 다음 요청들이 큐에 쌓여, Latency가 증가됨.
이런 겨우 Non-Blocking IO를 사용하여 API를 호출하여 해결.

## Async Spring
- ListenableFuture : 이 타입으로 Controller에서 리턴을 하면 알아서 비동기 작어이 끝난 후 처리해줌.
  - Callback으로 데이터를 처리 할수 있음. 이때 DeferredResult를 같이 사용하면 좋음.


## AsyncRetTemplate
- RestTemplate의 Async 버전.
- 사실 Async이지 Non-blocking은 아니다. IO작업은 Blocking이기 때문에 내부에서 Thread를 만들어 스레드는 늘어나게 된다.  
- Non-Blocking으로 사용하고 싶다면 생성자에 new Netty4ClientHttpClientRequestFactory(new NioEventLoopGroup(1)) 로 하면 Non-Blocking이 됨.

