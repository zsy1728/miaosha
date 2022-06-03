
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dao.SeckillDao;
import com.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 配置Spring和Junit整合,junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring的配置文件
@ContextConfiguration({ "classpath:spring-dao.xml" })
public class SeckillDaoTest {

    // 注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() {

        long seckillId = 1000;
        Seckill seckill = seckillDao.queryById(seckillId);
        System.out.println("=====name====="+seckill.getName());
        System.out.println("=====detail====="+seckill);
    }

    @Test
    public void testQueryAll() {

        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println("==========");
            System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() {

        long seckillId = 1000;
        Date date = new Date();
        int updateCount = seckillDao.reduceNumber(seckillId, date);
        System.out.println(updateCount);
    }

    @Test
    public void test03(){
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", 1000);
        map.put("phone", 66666666);
        map.put("killTime", killTime);
        map.put("result", -10);

        seckillDao.killByProcedure(map);
    }

}
