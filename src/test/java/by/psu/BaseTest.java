package by.psu;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = {
        StartDevpavMarketProject.class
})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "test")
public abstract class BaseTest {
}
