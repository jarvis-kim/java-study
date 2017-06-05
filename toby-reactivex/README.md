subscribeOn
 - 데이터를 생성하는 쪽이 느린경우.
 - Publisher가 느린경우.(IO작업 등), 예측할수 없는 경우
 - 데이터를 생성하는 곳부터 스레드로 처리하여 데이터 생성. 이후 Subscriber는 같은 스레드에서 처리됨.
 
publishOn
 - 데이터를 받는 쪽에서 별도의 스레드로 처리.
 - 퍼블리셔는 빠르지만, Consumer(Subscriber)가 느린경우 사용.

publishOn + subscribeOn
 - 데이터를 생성하는 부분을 다른 스레드로, 데이터를 소비하는 스레드를 다른 스레드로 한다.
 