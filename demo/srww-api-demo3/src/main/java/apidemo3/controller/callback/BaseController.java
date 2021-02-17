package apidemo3.controller.callback;

import org.noear.solon.annotation.Inject;
import apidemo3.dso.db.BullOrderService;
import apidemo3.dso.db.CoProductService;

public abstract class BaseController {
    @Inject
    CoProductService coProductService;

    @Inject
    BullOrderService bullOrderService;
}
