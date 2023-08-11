public class Encoder {
    // 의존성 주입에 의해 외부에서 받아들인 의존성을 담을 그릇을 생성했다.
    private IEncoder iEncoder;
    // 외부에서 주입된 의존성을 그릇에 담았다.
    public Encoder(IEncoder iEncoder){
        this.iEncoder = iEncoder;
    }
    // 해당 의존성이 공유한 인터페이스의 메소드를 호출한다.
    public String encode(String message){
        return iEncoder.encode(message);
    }
}
