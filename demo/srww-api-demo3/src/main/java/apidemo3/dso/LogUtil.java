package apidemo3.dso;


import org.noear.solon.logging.utils.TagsMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static final Logger log_api =  LoggerFactory.getLogger("bull_log_api");
    private static final Logger log_api_party =  LoggerFactory.getLogger("bull_log_api_party");

    public static void logOutput(String tag, String tag1, String tag2, String summary, String content) {
        TagsMDC.tag0(tag).tag1(tag1).tag2(tag2);

        log_api.info(
                "{}\n\n{}",
                summary,
                content
        );

    }

    public static void logError(String tag, String tag1, String tag2, String summary, Throwable e) {
        TagsMDC.tag0(tag).tag1(tag1).tag2(tag2);

        log_api.error(
                "{}\n\n{}",
                summary,
                e
        );
    }

    public static void logError(String tag, String tag1, String tag2, String summary, String content) {

        TagsMDC.tag0(tag).tag1(tag1).tag2(tag2);

        log_api.error(
                "{}\n\n{}",
                summary,
                content
        );

    }

    public static void logPartyOutput(String tag, String tag1, String tag2, String summary, String content) {

        TagsMDC.tag0(tag).tag1(tag1).tag2(tag2);

        log_api_party.info(
                "{}\n\n{}",
                summary,
                content
        );

    }

    public static void logPartyError(String tag, String tag1, String tag2, String summary, Throwable e) {
        TagsMDC.tag0(tag).tag1(tag1).tag2(tag2);

        log_api_party.error(
                "{}\n\n{}",
                summary,
                e
        );
    }

    public static void logPartyError(String tag, String tag1, String tag2, String summary, String content) {
        TagsMDC.tag0(tag).tag1(tag1).tag2(tag2);

        log_api_party.error(
                "{}\n\n{}",
                summary,
                content
        );

    }
}
