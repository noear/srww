package org.noear.srww.uadmin.widget;


import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.noear.bcf.BcfClient;
import org.noear.bcf.BcfUtil;
import org.noear.bcf.models.BcfGroupModel;
import org.noear.bcf.models.BcfResourceModel;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.srww.uadmin.dso.Session;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Component("view:leftmenu")
public class LeftmenuTag implements TemplateDirectiveModel {
    @Override
    public void execute(Environment env, Map map, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        try{
            build(env);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void build(Environment env) throws Exception {

        Context context = Context.current();
        //当前视图path //此处改过，noear，20180831
        String cPath = context.path();
        StringBuffer sb = new StringBuffer();

        List<BcfGroupModel> plist = BcfClient.getAllPacks();
        int packID = 0;
        for (BcfGroupModel p : plist) {
            if (cPath.indexOf(p.uri_path) == 0) { //::en_name 改为 uri_path
                packID = p.pgid;
                break;
            }
        }

        sb.append("<menu>");

        sb.append("<div onclick=\"$('main').toggleClass('smlmenu');if(window.onMenuHide){window.onMenuHide();}\"><i class='fa fa-bars'></i></div>");

        sb.append("<items>");

        forPack(packID, sb, cPath);

        sb.append("</items>");

        sb.append("</menu>");

        env.getOut().write(sb.toString());

    }

    private void forPack(int packID, StringBuffer sb, String cPath) throws SQLException
    {
        List<BcfResourceModel> list = BcfClient.getUserResourcesByPack(Session.current().getPUID(), packID);

        for(BcfResourceModel res :list){
            buildItem(sb,res,cPath);
        }
    }

    private void buildItem(StringBuffer sb,BcfResourceModel res,String cPath) {
        if("$".equals(res.cn_name)){
            sb.append("<br/><br/>");
            return;
        }

        //此处改过，noear，201811(uadmin)
        String newUrl = BcfUtil.buildBcfUnipath(res);

        //此处改过，noear，20180831
        if(cPath.indexOf(res.uri_path)>=0)
        {
            sb.append("<a class='sel' href='" + newUrl + "'>");
            sb.append(res.cn_name);
            sb.append("</a>");
        }
        else
        {
            sb.append("<a href='" + newUrl + "'>");
            sb.append(res.cn_name);
            sb.append("</a>");
        }
    }
}
