package apidemo2.controller.callback;

import org.noear.solon.annotation.Inject;
import apidemo2.dso.db.BullOrderService;
import apidemo2.dso.db.CoProductService;

public abstract class BaseController {
    @Inject
    CoProductService coProductService;

    @Inject
    BullOrderService bullOrderService;
}
