package training;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class HttpParserTest {

	private String input;
	private int expectedResult;

	static String emptyRequest = "";// 0
	static String requestStartingWithSpace = " OPTIONS * HTTP/1.1";// 400
	static String notFullequestLine = "OPTIONS *";// 400
	static String notFullRequestLine2 = "OPTIONS"; //400
	static String notFullHttpVersionRequest = "OPTIONS * HTTP/.1";// 400
	static String okRequest1 = "HEAD * HTTP/1.1 \r\n" + "Host: www.tutorialspoint.com\r\n\r\n";// 200
	static String badHttpVersionRequest = "OPTIONS * HTTP/a.b";// 400
	static String requestWithoutHeaders = "GET * HTTP/1.1\r\n";// 400
	static String requestWithPostMethod = "POST * HTTP/1.0\r\n" + "Host: www.tutorialspoint.com";// 501
	static String requestWithPutMethod = "PUT * HTTP/1.1\r\nHost: www.tutorialspoint.com\r\n\r\n";// 501
	static String oldHttpVersionRequet = "PUT * HTTP/1.0\r\n" + "Host: www.tutorialspoint.com";// 400
	static String okRequest2 = "GET http://www.somehost.com/path/file.html HTTP/1.0\r\n"
			+ "From: frog@jmarshall.com\r\n" + "User-Agent: HTTPTool/1.0\r\n" + "Host: www.tutorialspoint.com"
			+ "Content-Type: application/x-www-form-urlencoded\r\n" + "Content-Length: 32\r\n" + "\r\n"
			;// 200

	static String weirgRequestMethod = "LQLQ * HTTP/1.1";// 400

	public HttpParserTest(String input, int expectedResult) {
		this.input = input;
		this.expectedResult = expectedResult;
	}

	/*@Test
	public void testGetHttpReply() {

		String expected = "407 Proxy Authentication Required";
		assertEquals(expected, HttpParser.getHttpReply(407));
	}
	*/
	@Parameterized.Parameters
	public static Collection primeNumbers() throws IOException {
		return Arrays.asList(new Object[][] { 
			{ emptyRequest, 0 }, 
			{ requestStartingWithSpace, 400 },
			{ notFullequestLine, 400 }, 
			{ notFullHttpVersionRequest, 400 },
			{ okRequest1, 200},
			{ badHttpVersionRequest, 400 }, 
			{ requestWithoutHeaders, 400 }, 
			{ requestWithPostMethod, 501 },
			{ requestWithPutMethod, 501 },
			{ oldHttpVersionRequet, 400 },
			{ notFullRequestLine2, 400},
			{ okRequest2, 200 }, 
			{ weirgRequestMethod, 400 }

		});
	}

	@Test
	public void testHttpRequestChecker() throws IOException {
		assertEquals(expectedResult, new HttpParser(new ByteArrayInputStream(input.getBytes())).parseRequest());
	}

	

}
