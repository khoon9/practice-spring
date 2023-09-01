public class Main {
    public static void main(String[] args) {
        String url = "www.naver.com";

        // 단지 new Base64Encoder()/new UrlEncoder() 만을 변경하여 원하는 기능을 사용할 수 있게 되었다.
        // Encoder encoder = new Encoder(new Base64Encoder());

        Encoder encoder = new Encoder(new UrlEncoder());
        String result = encoder.encode(url);
        System.out.println(result);
    }
}