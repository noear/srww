package org.noear.srww.uapi.interceptor;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.noear.solon.Solon;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.srww.uapi.UapiCodes;

import java.util.ArrayList;
import java.util.List;

/**
 * 熔断拦截器
 */
public class SentryInterceptor implements Handler {
    int limit_qps = 1000;
    String limit_resource = Solon.cfg().appName();

    public SentryInterceptor() {
        initFlowRules();
    }

    public SentryInterceptor(int limitQps) {
        limit_qps = limitQps;
        initFlowRules();
    }

    @Override
    public void handle(Context context) throws Throwable {
        // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
        try (Entry entry = SphU.entry(limit_resource)) {
            // 被保护的逻辑
            return;
        } catch (BlockException ex) {
            // 处理被流控的逻辑
            throw UapiCodes.CODE_4001017;
        }
    }

    private void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = null;

        rule = new FlowRule();
        rule.setResource(limit_resource);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); //qps
        rule.setCount(limit_qps);
        rules.add(rule);

        rule = new FlowRule();
        rule.setResource(limit_resource);
        rule.setGrade(RuleConstant.FLOW_GRADE_THREAD); //并发数
        rule.setCount(limit_qps);
        rules.add(rule);

        FlowRuleManager.loadRules(rules);
    }
}
