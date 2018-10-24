
import org.apache.commons.codec.digest.DigestUtils;

public class Test {

	public static void main(String[] args) {
		String sha1 = DigestUtils.sha1Hex("123456");
		System.out.println(sha1);
		
		System.out.println(3500/0.8);
	}
}
