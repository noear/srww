package apidemo3.dso;

import org.noear.mlog.utils.Tags;
import org.noear.solon.cloud.CloudLogger;

public class Logger {

    private static final CloudLogger log_api =  CloudLogger.get("bull_log_api");
    private static final CloudLogger log_api_party =  CloudLogger.get("bull_log_api_party");



    public static void logOutput(String tag, String tag1, String tag2, String summary, String content) {

        log_api.info(
                Tags.tag0(tag).tag1(tag1).tag2(tag2),
                "{}\n\n{}",
                summary,
                content
        );

    }

    public static void logError(String tag, String tag1, String tag2, String summary, Throwable e) {

        log_api.error(
                Tags.tag0(tag).tag1(tag1).tag2(tag2),
                "{}\n\n{}",
                summary,
                e
        );
    }

    public static void logError(String tag, String tag1, String tag2, String summary, String content) {

        log_api.error(
                Tags.tag0(tag).tag1(tag1).tag2(tag2),
                "{}\n\n{}",
                summary,
                content
        );

    }

    public static void logPartyOutput(String tag, String tag1, String tag2, String summary, String content) {

        log_api_party.info(
                Tags.tag0(tag).tag1(tag1).tag2(tag2),
                "{}\n\n{}",
                summary,
                content
        );

    }

    public static void logPartyError(String tag, String tag1, String tag2, String summary, Throwable e) {

        log_api_party.error(
                Tags.tag0(tag).tag1(tag1).tag2(tag2),
                "{}\n\n{}",
                summary,
                e
        );
    }

    public static void logPartyError(String tag, String tag1, String tag2, String summary, String content) {

        log_api_party.error(
                Tags.tag0(tag).tag1(tag1).tag2(tag2),
                "{}\n\n{}",
                summary,
                content
        );

    }
}
