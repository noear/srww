package org.noear.srww.base.interceptor;

import org.noear.solon.Solon;
import org.noear.solon.cloud.CloudClient;
import org.noear.solon.cloud.CloudJobHandler;
import org.noear.solon.cloud.CloudJobInterceptor;
import org.noear.solon.cloud.model.Job;
import org.noear.solon.logging.utils.TagsMDC;
import org.noear.water.utils.Timecount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 任务拦截器
 *
 * @author noear
 */
public class BaseJobInterceptor implements CloudJobInterceptor {
    static Logger log = LoggerFactory.getLogger(BaseJobInterceptor.class);

    @Override
    public void doIntercept(Job job, CloudJobHandler handler) throws Throwable {
        TagsMDC.tag0("job");
        TagsMDC.tag1(job.getName());

        Timecount timecount = new Timecount().start();
        long timespan = 0;

        try {
            handler.handle(job.getContext());
            timespan = timecount.stop().milliseconds();

            log.info("Job execution succeeded @{}ms", timespan);
        } catch (Throwable e) {
            timespan = timecount.stop().milliseconds();

            log.error("Job execution error @{}ms: {}", timespan, e);
            throw e;
        } finally {
            if (timespan > 0) {
                CloudClient.metric().addMeter(Solon.cfg().appName(), "job", job.getName(), timespan);
            }
        }
    }
}
