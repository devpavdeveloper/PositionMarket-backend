package by.psu;

import com.jayway.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseRestApiTest {


        @BeforeClass
        public static void setup() {
            String port = System.getProperty("server.port");
            if (port == null) {
                RestAssured.port = 8008;
            }
            else{
                RestAssured.port = Integer.valueOf(port);
            }

            String baseHost = System.getProperty("server.host");
            if(baseHost==null){
                baseHost = "http://localhost";
            }
            RestAssured.baseURI = baseHost;

        }


}
