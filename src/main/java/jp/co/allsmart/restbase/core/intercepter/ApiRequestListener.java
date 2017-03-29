package jp.co.allsmart.restbase.core.intercepter;

import java.util.UUID;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

@Component
public class ApiRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        // リクエストID生成(UUID v4)
        UUID reqId = UUID.randomUUID();

        // ロガーにリクエストID設定
        ThreadContext.put("reqid", reqId.toString());

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        // ログ設定削除
        ThreadContext.clearMap();
    }

}
