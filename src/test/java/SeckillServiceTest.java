import com.entity.Exposer;
import com.entity.Seckill;
import com.entity.SeckillExecution;
import com.exception.RepeatKillException;
import com.exception.SeckillCloseException;
import com.exception.SeckillException;
import com.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring的配置文件
@ContextConfiguration({"classpath:spring-dao.xml",
        "classpath:spring-service.xml"})
@Transactional
public class SeckillServiceTest {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired @Qualifier("seckillServiceImpl")
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list=seckillService.getSeckillList();
        System.out.println("===================================================");
        System.out.println(list);
        System.out.println("---------------------------------------------------");
    }

    @Test
    public void testGetById() throws Exception {
        long seckillId=1000;

        Seckill seckill=seckillService.getById(seckillId);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long seckillId=1000;
        Exposer exposer=seckillService.exportSeckillUrl(seckillId);
        System.out.println("---------------------------------------------------");
        logger.info("exposer={}", exposer);
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long seckillId=1000;
        long userPhone=100000450L;
        String md5="70b9564762568e9ff29a4a949f8f6de4";
        System.out.println("---------------------------------------------------");
        try {
            SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
            logger.info("result={}", execution);
        }catch (RepeatKillException | SeckillCloseException e)
        {
            logger.error(e.getMessage());
        }
        System.out.println("===================================================");
    }

    @Test
    public void testSeckillLogic() {
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            System.out.println("开了");
            long user = 1003333779L;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution execution = seckillService.executeSeckill(seckillId,user,md5);
            }catch (SeckillException e){
                System.out.println("error__________");
            }
        }else {
            System.out.println("未开启");
        }
    }


    @Test
    public void test02() {
        long sid = 1000;
        long phone = 184750004;
        Exposer exposer = seckillService.exportSeckillUrl(sid);
        if(exposer.isExposed()){
            String md5 = exposer.getMd5();
            SeckillExecution execution = seckillService.executeSeckillProcedure(sid,phone,md5);
            logger.info(execution.getStateInfo());
        }
    }


}
