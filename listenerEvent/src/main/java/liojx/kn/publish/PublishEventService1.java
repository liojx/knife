package liojx.kn.publish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * 发送视频处理信息
 */
@Service
@Slf4j
public class PublishEventService1 implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
	    System.out.println("设置广播...");
        this.publisher = applicationEventPublisher;
    }

    public <T extends ApplicationEvent> void publishEvent(T event) {
        try {
            publisher.publishEvent(event);
        } catch (Exception e) {
            log.error("err", e);
        }
    }
}
