import com.ehualu.redis.JedisInterface;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by wangbaocai on 17-06-26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-core.xml"})
public class RedisTest {
    @Autowired
    private JedisInterface redisUtils;

    @Test
    public void testRedis() throws Exception{
        redisUtils.set("test","test");
        System.out.println(redisUtils.get("test"));
    }

}
